package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.Ledger;
import com.hevlar.accounts.domain.entities.journal.Journal;
import com.hevlar.accounts.domain.entities.journal.IJournal;

import java.time.LocalDate;
import java.util.List;

public interface GeneralLedgerRepository {
    IJournal addJournal(IJournal journal);
    Integer removeJournal(Long journalId);
    IJournal editJournal(IJournal journal);
    List<IJournal> listJournals();
    Long getNextJournalId();
    boolean hasJournalOfAccountId(String accountId);
    boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date);
    List<Ledger> listLedgers(LocalDate startDate, LocalDate endDate, List<String> accountIdList);
}
