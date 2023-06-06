package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.journal.Journal;

import java.time.LocalDate;
import java.util.List;

public interface GeneralLedgerRepository {
    Journal add(Journal journal);
    Integer remove(Long journalId);
    Journal edit(Journal journal);
    List<Journal> list();
    Long getNextJournalId();
    boolean hasJournalOfAccountId(String accountId);
    boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date);
}
