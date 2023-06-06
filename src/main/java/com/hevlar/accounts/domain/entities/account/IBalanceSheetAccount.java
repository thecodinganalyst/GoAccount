package com.hevlar.accounts.domain.entities.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IBalanceSheetAccount extends IAccount {
    LocalDate getOpeningDate();
    String getCurrency();
    BigDecimal getOpeningBalance();
}
