package com.hevlar.accounts.domain.entities.journal;

import com.hevlar.accounts.domain.entities.account.IAccount;

import java.math.BigDecimal;

public interface IJournalEntry {
    IAccount getAccount();
    BigDecimal getAmount();
}
