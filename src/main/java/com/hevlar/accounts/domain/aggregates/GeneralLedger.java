package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.journal.IJournal;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;

import java.time.LocalDate;

class GeneralLedger {
    private final GeneralLedgerRepository generalLedgerRepository;

    private GeneralLedger(GeneralLedgerRepository generalLedgerRepository){
        this.generalLedgerRepository = generalLedgerRepository;
    }

    static GeneralLedger createInstance(GeneralLedgerRepository generalLedgerRepository){
        return new GeneralLedger(generalLedgerRepository);
    }

    public IJournal addJournal(IJournal journal){
        return this.generalLedgerRepository.add(journal);
    }

    public Integer removeJournal(Long journalId){
        return this.generalLedgerRepository.remove(journalId);
    }

    public boolean hasJournalOfAccountId(String accountId){
        return this.generalLedgerRepository.hasJournalOfAccountId(accountId);
    }

    public boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date){
        return this.generalLedgerRepository.hasJournalOfAccountBeforeOnDate(accountId, date);
    }
}
