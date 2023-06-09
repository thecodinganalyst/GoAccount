package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.entities.journal.IJournal;
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

    public FinancialYearAccounting(FinancialYearRepository financialYearRepository, ChartOfAccountRepository<IAccount> chartOfAccountRepository, GeneralLedgerRepository generalLedgerRepository){
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

    public IBalanceSheetAccount addAccount(IBalanceSheetAccount account) {
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

    public IJournal addJournal(IJournal journal){
        return this.generalLedger.addJournal(journal);
    }

    public Integer removeJournal(Long journalId){
        return this.generalLedger.removeJournal(journalId);
    }

}
