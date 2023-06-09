package com.hevlar.accounts.domain.entities;

import com.hevlar.accounts.domain.entities.account.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class BalanceSheet {
    LocalDate balanceDate;
    String currency;
    HashMap<IBalanceSheetAccount, BigDecimal> fixedAssets;
    HashMap<IBalanceSheetAccount, BigDecimal> currentAssets;
    HashMap<IBalanceSheetAccount, BigDecimal> currentLiabilities;
    HashMap<IBalanceSheetAccount, BigDecimal> longTermLiabilities;
    HashMap<IBalanceSheetAccount, BigDecimal> equities;
}
