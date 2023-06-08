package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.account.BalanceSheetAccount;
import com.hevlar.accounts.domain.entities.account.IAccount;

import java.time.LocalDate;
import java.util.List;

public interface ChartOfAccountRepository {
    IAccount addAccount(IAccount account);
    Integer removeAccount(String accountId);
    IAccount editAccount(IAccount account);
    IAccount getAccount(String accountId);
    List<IAccount> listAccounts(LocalDate startDate, LocalDate endDate);
    List<BalanceSheetAccount> listBalanceSheetAccounts(LocalDate startDate, LocalDate endDate);
}
