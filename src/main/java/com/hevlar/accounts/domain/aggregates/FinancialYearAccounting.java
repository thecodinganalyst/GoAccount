package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.BalanceSheet;
import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.exceptions.*;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.entities.journal.IJournal;
import com.hevlar.accounts.domain.exceptions.AccountHasJournalBeforeDateException;
import com.hevlar.accounts.domain.exceptions.AccountReferencedInLedgerException;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FinancialYearAccounting {
    private final FinancialYear financialYear;
    private final ChartOfAccounts chartOfAccounts;
    private final GeneralLedger generalLedger;

    public FinancialYearAccounting(FinancialYear financialYear, ChartOfAccountRepository<IAccount, IBalanceSheetAccount> chartOfAccountRepository, GeneralLedgerRepository generalLedgerRepository) {
        this.financialYear = financialYear;
        this.chartOfAccounts = ChartOfAccounts.createInstance(chartOfAccountRepository);
        this.generalLedger = GeneralLedger.createInstance(generalLedgerRepository);
    }

    public Map<AccountGroup, List<IAccount>> listAccounts(){
        return this.chartOfAccounts.listAccounts(financialYear.getStartDate(), financialYear.getEndDate());
    }

    public IAccount getAccount(String accountId){
        return this.chartOfAccounts.getAccount(accountId);
    }

    public IAccount addAccount(IAccount account) {
        return this.chartOfAccounts.addAccount(account);
    }

    public IBalanceSheetAccount addAccount(IBalanceSheetAccount account) throws AccountOpeningDateIsBeforeFyStartDateException, AccountOpeningDateIsAfterFyEndDateException {
        if(account.getOpeningDate().isBefore(financialYear.getStartDate())) throw new AccountOpeningDateIsBeforeFyStartDateException();
        if(account.getOpeningDate().isAfter(financialYear.getEndDate())) throw new AccountOpeningDateIsAfterFyEndDateException();
        return this.chartOfAccounts.addAccount(account);
    }

    public Integer removeAccount(String accountId) throws AccountReferencedInLedgerException {
        if(this.generalLedger.hasJournalOfAccountId(accountId)) throw new AccountReferencedInLedgerException();
        return this.chartOfAccounts.removeAccount(accountId);
    }

    public void validateEditAccount(IAccount original, IAccount updated) throws Exception {
        if(original == null) throw new Exception("Account id " + updated.getAccountId() + " not found");

        if(!original.getAccountGroup().equals(updated.getAccountGroup())){
            if(this.generalLedger.hasJournalOfAccountId(updated.getAccountId())) throw new AccountReferencedInLedgerException();
        }
    }

    public void validateEditBalanceSheetAccount(IBalanceSheetAccount original, IBalanceSheetAccount updated) throws Exception {
        if(!original.getOpeningDate().isEqual(updated.getOpeningDate())){
            if(generalLedger.hasJournalOfAccountBeforeOnDate(updated.getAccountId(), updated.getOpeningDate())){
                throw new AccountHasJournalBeforeDateException();
            }
        }
        if(!original.getCurrency().equals(updated.getCurrency())){
            if(this.generalLedger.hasJournalOfAccountId(updated.getAccountId())) throw new AccountReferencedInLedgerException();
        }

        if(updated.getOpeningDate().isBefore(financialYear.getStartDate())) throw new AccountOpeningDateIsBeforeFyStartDateException();
        if(updated.getOpeningDate().isAfter(financialYear.getEndDate())) throw new AccountOpeningDateIsAfterFyEndDateException();
    }

    public IBalanceSheetAccount editAccount(IBalanceSheetAccount updated) throws Exception {
        IBalanceSheetAccount original = (IBalanceSheetAccount) this.chartOfAccounts.getAccount(updated.getAccountId());
        validateEditAccount(original, updated);
        validateEditBalanceSheetAccount(original, updated);
        return (IBalanceSheetAccount) this.chartOfAccounts.editAccount(updated);
    }

    public IAccount editAccount(IAccount updated) throws Exception {
        IAccount original = this.chartOfAccounts.getAccount(updated.getAccountId());
        validateEditAccount(original, updated);
        return this.chartOfAccounts.editAccount(updated);
    }

    public IJournal addJournal(IJournal journal) throws JournalTxDateIsBeforeFyStartDateException, JournalTxDateIsAfterFyEndDateException, JournalPostedDateIsBeforeFyStartDateException, JournalPostedDateIsAfterFyEndDateException {
        if(journal.getTxDate().isBefore(financialYear.getStartDate())) throw new JournalTxDateIsBeforeFyStartDateException();
        if(journal.getTxDate().isAfter(financialYear.getEndDate())) throw new JournalTxDateIsAfterFyEndDateException();
        if(journal.getPostedDate().isBefore(financialYear.getStartDate())) throw new JournalPostedDateIsBeforeFyStartDateException();
        if(journal.getPostedDate().isAfter(financialYear.getEndDate())) throw new JournalPostedDateIsAfterFyEndDateException();
        return this.generalLedger.addJournal(journal);
    }

    public Integer removeJournal(Long journalId){
        return this.generalLedger.removeJournal(journalId);
    }

    public BalanceSheet generateBalanceSheet(LocalDate balanceDate, String currency){
        List<IBalanceSheetAccount> accountList = this.chartOfAccounts.listBalanceSheetAccounts(financialYear.getStartDate(), balanceDate);
        List<String> accountIdList = accountList.stream().map(IBalanceSheetAccount::getAccountId).toList();
        return new BalanceSheet(
                balanceDate,
                currency,
                accountList,
                this.generalLedger.listLedgers(financialYear.getStartDate(), balanceDate, accountIdList));
    }

}
