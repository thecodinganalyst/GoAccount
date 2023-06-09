package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.journal.IJournal;

import java.time.LocalDate;
import java.util.List;

public interface GeneralLedgerRepository {
    IJournal add(IJournal journal);
    Integer remove(Long journalId);
    IJournal edit(IJournal journal);
    List<IJournal> list();
    Long getNextJournalId();
    boolean hasJournalOfAccountId(String accountId);
    boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date);
}
