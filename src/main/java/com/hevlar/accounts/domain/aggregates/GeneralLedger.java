package com.hevlar.accounts.domain.aggregates;

import com.hevlar.accounts.domain.entities.Ledger;
import com.hevlar.accounts.domain.entities.journal.Journal;
import com.hevlar.accounts.domain.entities.journal.IJournal;
import com.hevlar.accounts.domain.repositories.GeneralLedgerRepository;

import java.time.LocalDate;
import java.util.List;

class GeneralLedger {
    private final GeneralLedgerRepository generalLedgerRepository;

    private GeneralLedger(GeneralLedgerRepository generalLedgerRepository){
        this.generalLedgerRepository = generalLedgerRepository;
    }

    static GeneralLedger createInstance(GeneralLedgerRepository generalLedgerRepository){
        return new GeneralLedger(generalLedgerRepository);
    }

    public Journal addJournal(IJournal journal){
        return this.generalLedgerRepository.addJournal(journal);
    }

    public Integer removeJournal(Long journalId){
        return this.generalLedgerRepository.removeJournal(journalId);
    }

    public boolean hasJournalOfAccountId(String accountId){
        return this.generalLedgerRepository.hasJournalOfAccountId(accountId);
    }

    public boolean hasJournalOfAccountBeforeOnDate(String accountId, LocalDate date){
        return this.generalLedgerRepository.hasJournalOfAccountBeforeOnDate(accountId, date);
    }

    List<Ledger> listLedgers(LocalDate startDate, LocalDate endDate, List<String> accountIdList){
        return this.generalLedgerRepository.listLedgers(startDate, endDate, accountIdList);
    }

}
