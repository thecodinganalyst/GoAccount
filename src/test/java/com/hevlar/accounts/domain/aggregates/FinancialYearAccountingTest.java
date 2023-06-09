package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class FinancialYearAccountingTest {

    LocalDate fyStart = LocalDate.of(2023, 1, 1);
    LocalDate fyEnd = LocalDate.of(2023, 12, 31);
    FinancialYear fy2023;
    FinancialYearAccounting accounting;

    @Mock
    ChartOfAccountRepository<IAccount, IBalanceSheetAccount> chartOfAccountsRepo;
    @Mock
    GeneralLedgerRepository generalLedgerRepo;

    @BeforeEach
    void setup(){
        fy2023 = new FinancialYear("2023", fyStart, fyEnd);
        accounting = new FinancialYearAccounting(fy2023, chartOfAccountsRepo, generalLedgerRepo);
    }

}
