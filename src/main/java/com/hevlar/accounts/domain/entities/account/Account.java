package com.hevlar.accounts.domain.entities.account;

import com.hevlar.accounts.domain.valueobjects.AccountGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Account implements IAccount{

    @NotNull
    @NotEmpty
    String accountId;

    @NotNull
    @NotEmpty
    String accountName;

    @NotNull
    AccountGroup accountGroup;

    public Account(String accountId, String accountName, AccountGroup accountGroup){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountGroup = accountGroup;
    }

    @Override
    public String getAccountId() {
        return this.accountId;
    }

    @Override
    public String getAccountName() {
        return this.accountName;
    }

    @Override
    public AccountGroup getAccountGroup() {
        return this.accountGroup;
    }
}
