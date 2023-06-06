package com.hevlar.accounts.domain.entities;

import com.hevlar.accounts.domain.entities.account.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class BalanceSheet {
    LocalDate balanceDate;
    String currency;
    HashMap<BalanceSheetAccount, BigDecimal> fixedAssets;
    HashMap<BalanceSheetAccount, BigDecimal> currentAssets;
    HashMap<BalanceSheetAccount, BigDecimal> currentLiabilities;
    HashMap<BalanceSheetAccount, BigDecimal> longTermLiabilities;
    HashMap<BalanceSheetAccount, BigDecimal> equities;
}
