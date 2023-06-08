package com.hevlar.accounts.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
public class FinancialYear {
    @Getter
    String label;
    @Getter
    LocalDate startDate;
    @Getter
    LocalDate endDate;
}
