package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.entities.account.BalanceSheetAccount;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.journal.Journal;
import com.hevlar.accounts.domain.exceptions.AccountHasJournalBeforeDateException;
import com.hevlar.accounts.domain.exceptions.AccountReferencedInLedgerException;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.repositories.FinancialYearRepository;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;

import java.util.List;
import java.util.Map;

public class FinancialYearAccounting {
    private final FinancialYearRepository financialYearRepository;
    private final ChartOfAccounts chartOfAccounts;
    private final GeneralLedger generalLedger;

    public FinancialYearAccounting(FinancialYearRepository financialYearRepository, ChartOfAccountRepository chartOfAccountRepository, GeneralLedgerRepository generalLedgerRepository){
        this.financialYearRepository = financialYearRepository;
        this.chartOfAccounts = ChartOfAccounts.createInstance(chartOfAccountRepository);
        this.generalLedger = GeneralLedger.createInstance(generalLedgerRepository);
    }

    public FinancialYear addFinancialYear(FinancialYear financialYear){
        return this.financialYearRepository.add(financialYear);
    }

    public Integer removeFinancialYear(String label){
        return this.financialYearRepository.remove(label);
    }

    public List<FinancialYear> list(){
        return this.financialYearRepository.list();
    }

    public FinancialYear get(String label){
        return this.financialYearRepository.get(label);
    }

    public Map<AccountGroup, List<IAccount>> listAccounts(){
        return this.chartOfAccounts.listAccounts();
    }

    public IAccount getAccount(String accountId){
        return this.chartOfAccounts.getAccount(accountId);
    }

    public IAccount addAccount(IAccount account) {
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
        }

        return this.chartOfAccounts.editAccount(updated);
    }

    public Journal addJournal(Journal journal){
        return this.generalLedger.addJournal(journal);
    }

    public Integer removeJournal(Long journalId){
        return this.generalLedger.removeJournal(journalId);
    }

}
