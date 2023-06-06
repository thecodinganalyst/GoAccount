package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.account.IAccount;

import java.util.List;

public interface ChartOfAccountRepository {
    IAccount add(IAccount account);
    Integer remove(String accountId);
    IAccount edit(IAccount account);
    IAccount get(String accountId);
    List<IAccount> list();
}
