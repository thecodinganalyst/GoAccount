package com.hevlar.accounts.domain.services;

import com.hevlar.accounts.domain.entities.FinancialYear;
import com.hevlar.accounts.domain.repositories.FinancialYearRepository;

import java.util.List;

public class DoubleEntryAccountingDomainService {
    private final FinancialYearRepository financialYearRepository;
    public DoubleEntryAccountingDomainService(FinancialYearRepository financialYearRepository){
        this.financialYearRepository = financialYearRepository;
    }

    public FinancialYear addFinancialYear(FinancialYear financialYear){
        return this.financialYearRepository.addFinancialYear(financialYear);
    }

    public Integer removeFinancialYear(String label){
        return this.financialYearRepository.removeFinancialYear(label);
    }

    public List<FinancialYear> list(){
        return this.financialYearRepository.listFinancialYears();
    }

    public FinancialYear get(String label){
        return this.financialYearRepository.getFinancialYear(label);
    }

}
