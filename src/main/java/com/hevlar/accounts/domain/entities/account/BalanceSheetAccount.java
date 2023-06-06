package com.hevlar.accounts.domain.entities.account;

import com.hevlar.accounts.domain.valueobjects.AccountGroup;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BalanceSheetAccount extends Account implements IBalanceSheetAccount{

    @NotNull
    LocalDate openingDate;

    @NotNull
    String currency;

    @NotNull
    BigDecimal openingBalance;

    public BalanceSheetAccount(String accountId, String accountName, AccountGroup accountGroup, LocalDate openingDate, String currency, BigDecimal openingBalance){
        super(accountId, accountName, accountGroup);
        this.openingDate = openingDate;
        this.currency = currency;
        this.openingBalance = openingBalance;
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
