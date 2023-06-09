package com.hevlar.accounts.infrastructure.entities;

import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Account implements IAccount, IBalanceSheetAccount {

    @Id
    @NotNull
    @NotEmpty
    String accountId;

    @NotNull
    @NotEmpty
    String accountName;

    @NotNull
    AccountGroup accountGroup;

    LocalDate openingDate;

    String currency;

    BigDecimal openingBalance;

    public Account(String accountId, String accountName, AccountGroup accountGroup){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountGroup = accountGroup;
        this.openingDate = null;
        this.currency = null;
        this.openingBalance = null;
    }

    public Account(String accountId, String accountName, AccountGroup accountGroup, LocalDate openingDate, String currency, BigDecimal openingBalance){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountGroup = accountGroup;
        this.openingDate = openingDate;
        this.currency = currency;
        this.openingBalance = openingBalance;
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

    @Override
    public LocalDate getOpeningDate() {
        return this.openingDate;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public BigDecimal getOpeningBalance() {
        return this.openingBalance;
    }
}
