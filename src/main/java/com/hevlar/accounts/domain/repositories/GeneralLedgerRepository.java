package com.hevlar.accounts.domain.repositories;

import com.hevlar.accounts.domain.entities.Ledger;
import com.hevlar.accounts.domain.entities.journal.Journal;

import java.time.LocalDate;
import java.util.List;

public interface GeneralLedgerRepository {
    Journal addJournal(Journal journal);
    Integer removeJournal(Long journalId);
    Journal editJournal(Journal journal);
    List<Journal> listJournals();
    Long getNextJournalId();
    boolean hasJournalOfAccountId(String accountId);
    boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date);
    List<Ledger> listLedgers(LocalDate startDate, LocalDate endDate, List<String> accountIdList);
}
