package com.hevlar.accounts.domain.entities.account;

import com.hevlar.accounts.domain.valueobjects.AccountGroup;

public interface IAccount {

    String getAccountId();
    String getAccountName();
    AccountGroup getAccountGroup();
}
