package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.BalanceSheet;
import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.entities.account.Account;
import com.hevlar.accounts.domain.entities.account.BalanceSheetAccount;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.journal.Journal;
import com.hevlar.accounts.domain.exceptions.*;
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

    public FinancialYearAccounting(FinancialYear financialYear, ChartOfAccountRepository chartOfAccountRepository, GeneralLedgerRepository generalLedgerRepository){
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

    public IAccount addAccount(IAccount account) throws AccountOpeningDateIsBeforeFyStartDateException, AccountOpeningDateIsAfterFyEndDateException {
        if(account instanceof BalanceSheetAccount bsAccount){
            if(bsAccount.getOpeningDate().isBefore(financialYear.getStartDate())) throw new AccountOpeningDateIsBeforeFyStartDateException();
            if(bsAccount.getOpeningDate().isAfter(financialYear.getEndDate())) throw new AccountOpeningDateIsAfterFyEndDateException();
        }
        return this.chartOfAccounts.addAccount(account);
    }

    public Integer removeAccount(String accountId) throws AccountReferencedInLedgerException {
        if(this.generalLedger.hasJournalOfAccountId(accountId)) throw new AccountReferencedInLedgerException();
        return this.chartOfAccounts.removeAccount(accountId);
    }

    public IAccount editAccount(IAccount updated) throws Exception {
        IAccount original = this.chartOfAccounts.getAccount(updated.getAccountId());

        if(original == null) throw new Exception("Account id " + updated.getAccountId() + " not found");

        if(!original.getAccountGroup().equals(updated.getAccountGroup())){
            if(this.generalLedger.hasJournalOfAccountId(updated.getAccountId())) throw new AccountReferencedInLedgerException();
        }

        boolean isBalanceSheetAccount = original instanceof BalanceSheetAccount && updated instanceof BalanceSheetAccount;

        if(isBalanceSheetAccount){
            BalanceSheetAccount originalAccount = (BalanceSheetAccount) original;
            BalanceSheetAccount updatedAccount = (BalanceSheetAccount) updated;

            if(!originalAccount.getOpeningDate().isEqual(updatedAccount.getOpeningDate())){
                if(generalLedger.hasJournalOfAccountBeforeOnDate(updatedAccount.getAccountId(), updatedAccount.getOpeningDate())){
                    throw new AccountHasJournalBeforeDateException();
                }
            }
            if(!originalAccount.getCurrency().equals(updatedAccount.getCurrency())){
                if(this.generalLedger.hasJournalOfAccountId(updated.getAccountId())) throw new AccountReferencedInLedgerException();
            }

            if(updatedAccount.getOpeningDate().isBefore(financialYear.getStartDate())) throw new AccountOpeningDateIsBeforeFyStartDateException();
            if(updatedAccount.getOpeningDate().isAfter(financialYear.getEndDate())) throw new AccountOpeningDateIsAfterFyEndDateException();
        }

        return this.chartOfAccounts.editAccount(updated);
    }

    public Journal addJournal(Journal journal) throws JournalTxDateIsBeforeFyStartDateException, JournalTxDateIsAfterFyEndDateException, JournalPostedDateIsBeforeFyStartDateException, JournalPostedDateIsAfterFyEndDateException {
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
        List<BalanceSheetAccount> accountList = this.chartOfAccounts.listBalanceSheetAccounts(financialYear.getStartDate(), balanceDate);
        List<String> accountIdList = accountList.stream().map(Account::getAccountId).toList();
        return new BalanceSheet(
                balanceDate,
                currency,
                accountList,
                this.generalLedger.listLedgers(financialYear.getStartDate(), balanceDate, accountIdList));
    }

}
