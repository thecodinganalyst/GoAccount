package com.hevlar.accounts.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class Ledger {
    @Getter
    Long ledgerId;
    @Getter
    String accountId;
    @Getter
    Long journalId;
    @Getter
    LocalDate txDate;
    @Getter
    LocalDate postedDate;
    @Getter
    String description;
    @Getter
    BigDecimal amount;
}
