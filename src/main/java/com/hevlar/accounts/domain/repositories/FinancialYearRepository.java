package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.FinancialYear;

import java.time.LocalDate;
import java.util.List;

public interface FinancialYearRepository {
    FinancialYear add(FinancialYear financialYear);
    Integer remove(String label);
    List<FinancialYear> list();
    FinancialYear get(String label);
}
