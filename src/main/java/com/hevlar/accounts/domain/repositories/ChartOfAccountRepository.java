package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;

import java.time.LocalDate;
import java.util.List;

public interface ChartOfAccountRepository<T extends IAccount, U extends IBalanceSheetAccount> {
    IAccount add(IAccount account);

    IBalanceSheetAccount add(IBalanceSheetAccount account);

    Integer remove(String accountId);
    IAccount edit(IAccount account);
    IBalanceSheetAccount edit(IBalanceSheetAccount account);
    IAccount get(String accountId);
    List<T> list(LocalDate startDate, LocalDate endDate);

    List<U> listBalanceSheetAccounts(LocalDate startDate, LocalDate endDate);
}
