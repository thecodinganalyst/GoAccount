package com.hevlar.accounts.domain.entities.journal;

import java.time.LocalDate;
import java.util.List;

public interface IJournal {
    LocalDate getTxDate();

    LocalDate getPostedDate();

    String getDescription();

    List<IJournalEntry> getDebitEntries();

    List<IJournalEntry> getCreditEntries();
}
