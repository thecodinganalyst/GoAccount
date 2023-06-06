package com.hevlar.accounts.domain.entities.journal;

import com.hevlar.accounts.domain.entities.account.BalanceSheetAccount;
import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.exceptions.JournalAmountNotBalanceException;
import com.hevlar.accounts.domain.exceptions.JournalDebitCreditDifferentCurrencyException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Journal {
    Long journalId;
    LocalDate txDate;
    LocalDate postedDate;
    String description;
    List<JournalEntry> debitEntries;
    List<JournalEntry> creditEntries;

    public static class JournalEntry {
        IAccount account;
        BigDecimal amount;

        public JournalEntry(IAccount account, BigDecimal amount){
            this.account = account;
            this.amount = amount;
        }
    }

    public Journal(Long journalId, Journal journal){
        this.journalId = journalId;
        this.txDate = journal.txDate;
        this.postedDate = journal.postedDate;
        this.description = journal.description;
        this.debitEntries = journal.debitEntries;
        this.creditEntries = journal.creditEntries;
    }

    public Journal(LocalDate txDate, String description, IAccount debitAccount, IAccount creditAccount, BigDecimal amount) throws JournalDebitCreditDifferentCurrencyException {
        if(debitAccount instanceof BalanceSheetAccount debit && creditAccount instanceof BalanceSheetAccount credit){
            if(!debit.getCurrency().equals(credit.getCurrency())){
                throw new JournalDebitCreditDifferentCurrencyException();
            }
        }
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IAccount debitAccount, IAccount creditAccount, BigDecimal amount) throws JournalDebitCreditDifferentCurrencyException {
        if(debitAccount instanceof BalanceSheetAccount debit && creditAccount instanceof BalanceSheetAccount credit){
            if(!debit.getCurrency().equals(credit.getCurrency())){
                throw new JournalDebitCreditDifferentCurrencyException();
            }
        }
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, String description, IAccount debitAccount, BigDecimal debitAmount, IAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        if(debitAccount instanceof BalanceSheetAccount debit && creditAccount instanceof BalanceSheetAccount credit){
            if(!debit.getCurrency().equals(credit.getCurrency()) && !Objects.equals(debitAmount, creditAmount)){
                throw new JournalAmountNotBalanceException();
            }
        }
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IAccount debitAccount, BigDecimal debitAmount, IAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        if(debitAccount instanceof BalanceSheetAccount debit && creditAccount instanceof BalanceSheetAccount credit){
            if(!debit.getCurrency().equals(credit.getCurrency()) && !Objects.equals(debitAmount, creditAmount)){
                throw new JournalAmountNotBalanceException();
            }
        }
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    public LocalDate getTxDate(){
        return this.txDate;
    }

    public LocalDate getPostedDate(){
        return this.postedDate;
    }

    public String getDescription(){
        return this.description;
    }

    public List<JournalEntry> getDebitEntries(){
        return this.debitEntries;
    }

    public List<JournalEntry> getCreditEntries(){
        return this.creditEntries;
    }
}
