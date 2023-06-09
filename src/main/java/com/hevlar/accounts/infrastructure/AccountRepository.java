package com.hevlar.accounts.infrastructure;

import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.infrastructure.entities.Account;

import java.util.List;

public class AccountRepository implements ChartOfAccountRepository<Account> {

    JpaAccountRepository jpaAccountRepository;

    public AccountRepository(JpaAccountRepository jpaAccountRepository){
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public IAccount add(IAccount account) {
        Account concreteAccount = new Account(account.getAccountId(), account.getAccountName(), account.getAccountGroup());
        return this.jpaAccountRepository.save(concreteAccount);
    }

    @Override
    public IBalanceSheetAccount add(IBalanceSheetAccount account) {
        Account concreteAccount = new Account(
                account.getAccountId(),
                account.getAccountName(),
                account.getAccountGroup(),
                account.getOpeningDate(),
                account.getCurrency(),
                account.getOpeningBalance());
        return this.jpaAccountRepository.save(concreteAccount);
    }

    @Override
    public Integer remove(String accountId) {
        if(this.jpaAccountRepository.findById(accountId).isEmpty()) return 0;
        this.jpaAccountRepository.deleteById(accountId);
        return 1;
    }

    @Override
    public IAccount edit(IAccount account) {
        Account concreteAccount = new Account(account.getAccountId(), account.getAccountName(), account.getAccountGroup());
        return this.jpaAccountRepository.save(concreteAccount);
    }

    @Override
    public IBalanceSheetAccount edit(IBalanceSheetAccount account) {
        Account concreteAccount = new Account(
                account.getAccountId(),
                account.getAccountName(),
                account.getAccountGroup(),
                account.getOpeningDate(),
                account.getCurrency(),
                account.getOpeningBalance());
        return this.jpaAccountRepository.save(concreteAccount);
    }

    @Override
    public IAccount get(String accountId) {
        return this.jpaAccountRepository.findById(accountId).orElseThrow();
    }

    @Override
    public List<Account> list() {
        return this.jpaAccountRepository.findAll();
    }
}
