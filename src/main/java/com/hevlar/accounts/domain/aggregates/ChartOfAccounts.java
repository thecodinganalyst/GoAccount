package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.account.*;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.valueobjects.AccountGroup;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ChartOfAccounts {
    private final ChartOfAccountRepository<IAccount> chartOfAccountRepository;

    private ChartOfAccounts(ChartOfAccountRepository<IAccount> chartOfAccountRepository){
        this.chartOfAccountRepository = chartOfAccountRepository;
    }
    static ChartOfAccounts createInstance(ChartOfAccountRepository<IAccount> chartOfAccountRepository){
        return new ChartOfAccounts(chartOfAccountRepository);
    }
    public IAccount addAccount(IAccount account){
        return this.chartOfAccountRepository.addAccount(account);
    }

    public IBalanceSheetAccount addAccount(IBalanceSheetAccount account){
        return this.chartOfAccountRepository.add(account);
    }

    public Integer removeAccount(String accountId) {
        return this.chartOfAccountRepository.removeAccount(accountId);
    }

    public IAccount editAccount(IAccount account){
        return this.chartOfAccountRepository.editAccount(account);
    }

    public IBalanceSheetAccount editAccount(IBalanceSheetAccount account){
        return this.chartOfAccountRepository.edit(account);
    }

    public IAccount getAccount(String accountId){
        return this.chartOfAccountRepository.getAccount(accountId);
    }

    public Map<AccountGroup, List<IAccount>> listAccounts(LocalDate startDate, LocalDate endDate){
        return this.chartOfAccountRepository.listAccounts(startDate, endDate).stream().collect(Collectors.groupingBy(IAccount::getAccountGroup));
    }

    public List<BalanceSheetAccount> listBalanceSheetAccounts(LocalDate startDate, LocalDate endDate){
        return this.chartOfAccountRepository.listBalanceSheetAccounts(startDate, endDate);
    }

}
