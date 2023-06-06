package com.hevlar.accounts.domain.entities;

import java.time.LocalDate;

public class FinancialYear {
    String label;
    LocalDate startDate;
    LocalDate endDate;

    public FinancialYear(String label, LocalDate startDate, LocalDate endDate){
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
