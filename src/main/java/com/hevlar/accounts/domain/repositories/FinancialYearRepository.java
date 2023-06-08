package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.FinancialYear;

import java.util.List;

public interface FinancialYearRepository {
    FinancialYear addFinancialYear(FinancialYear financialYear);
    Integer removeFinancialYear(String label);
    List<FinancialYear> listFinancialYears();
    FinancialYear getFinancialYear(String label);
}
