package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.account.*;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ChartOfAccounts {
    private final ChartOfAccountRepository<IAccount, IBalanceSheetAccount> chartOfAccountRepository;

    private ChartOfAccounts(ChartOfAccountRepository<IAccount, IBalanceSheetAccount> chartOfAccountRepository){
        this.chartOfAccountRepository = chartOfAccountRepository;
    }
    static ChartOfAccounts createInstance(ChartOfAccountRepository<IAccount, IBalanceSheetAccount> chartOfAccountRepository){
        return new ChartOfAccounts(chartOfAccountRepository);
    }
    public IAccount addAccount(IAccount account){
        return this.chartOfAccountRepository.add(account);
    }

    public IBalanceSheetAccount addAccount(IBalanceSheetAccount account){
        return this.chartOfAccountRepository.add(account);
    }

    public Integer removeAccount(String accountId) {
        return this.chartOfAccountRepository.remove(accountId);
    }

    public IAccount editAccount(IAccount account){
        return this.chartOfAccountRepository.edit(account);
    }

    public IBalanceSheetAccount editAccount(IBalanceSheetAccount account){
        return this.chartOfAccountRepository.edit(account);
    }

    public IAccount getAccount(String accountId){
        return this.chartOfAccountRepository.get(accountId);
    }

    public Map<AccountGroup, List<IAccount>> listAccounts(LocalDate startDate, LocalDate endDate){
        return this.chartOfAccountRepository.list(startDate, endDate).stream().collect(Collectors.groupingBy(IAccount::getAccountGroup));
    }

    public List<IBalanceSheetAccount> listBalanceSheetAccounts(LocalDate startDate, LocalDate endDate){
        return this.chartOfAccountRepository.listBalanceSheetAccounts(startDate, endDate);
    }

}
