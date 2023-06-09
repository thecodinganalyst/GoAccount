package com.hevlar.accounts.domain.entities;

import com.hevlar.accounts.domain.entities.account.*;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BalanceSheet {
    LocalDate balanceDate;
    String currency;
    Map<AccountGroup, List<AccountLedger>> data;

    public BalanceSheet(
            LocalDate balanceDate,
            String currency,
            List<IBalanceSheetAccount> accountList,
            List<Ledger> ledgerList){
        this.balanceDate = balanceDate;
        this.currency = currency;
        Map<AccountGroup, List<IBalanceSheetAccount>> accountMap = accountList.stream()
                .collect(Collectors.groupingBy(IBalanceSheetAccount::getAccountGroup));
        Map<String, List<Ledger>> ledgerMap = ledgerList.stream()
                .collect(Collectors.groupingBy(Ledger::getAccountId));

        for(AccountGroup accountGroup: accountMap.keySet()){
            List<IBalanceSheetAccount> accounts = accountMap.get(accountGroup);
            List<AccountLedger> accountLedgerList = accounts.stream().map(account -> new AccountLedger(account, ledgerMap.get(account.getAccountId()))).toList();
            data.put(accountGroup, accountLedgerList);
        }

    }

    public static class AccountLedger {
        IBalanceSheetAccount account;
        List<Ledger> ledgerList;

        public AccountLedger(IBalanceSheetAccount account, List<Ledger> ledgerList){
            this.account = account;
            this.ledgerList = ledgerList;
        }
    }

}
