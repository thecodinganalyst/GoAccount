package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.repositories.ChartOfAccountRepository;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class FinancialYearAccountingTest {

    LocalDate fyStart = LocalDate.of(2023, 1, 1);
    LocalDate fyEnd = LocalDate.of(2023, 12, 31);
    FinancialYear fy2023;
    FinancialYearAccounting accounting;
    ChartOfAccountRepository chartOfAccountsRepo;
    GeneralLedgerRepository generalLedgerRepo;

    @BeforeEach
    void setup(){
        fy2023 = new FinancialYear("2023", fyStart, fyEnd);
        chartOfAccountsRepo = Mockito.mock(ChartOfAccountRepository.class);
        generalLedgerRepo = Mockito.mock(GeneralLedgerRepository.class);
        accounting = new FinancialYearAccounting(fy2023, chartOfAccountsRepo, generalLedgerRepo);
    }

}