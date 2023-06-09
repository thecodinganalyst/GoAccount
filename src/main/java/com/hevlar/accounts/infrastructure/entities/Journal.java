package com.hevlar.accounts.infrastructure.entities;

import com.hevlar.accounts.domain.entities.account.IAccount;
import com.hevlar.accounts.domain.entities.account.IBalanceSheetAccount;
import com.hevlar.accounts.domain.entities.journal.IJournal;
import com.hevlar.accounts.domain.entities.journal.IJournalEntry;
import com.hevlar.accounts.domain.exceptions.JournalAmountNotBalanceException;
import com.hevlar.accounts.domain.exceptions.JournalDebitCreditDifferentCurrencyException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Journal implements IJournal {
    Long journalId;
    LocalDate txDate;
    LocalDate postedDate;
    String description;
    List<IJournalEntry> debitEntries;
    List<IJournalEntry> creditEntries;

    public static class JournalEntry implements IJournalEntry{
        IAccount account;
        BigDecimal amount;

        public JournalEntry(IAccount account, BigDecimal amount){
            this.account = account;
            this.amount = amount;
        }

        @Override
        public IAccount getAccount() {
            return null;
        }

        @Override
        public BigDecimal getAmount() {
            return null;
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

    public Journal(LocalDate txDate, String description, IBalanceSheetAccount debitAccount, IBalanceSheetAccount creditAccount, BigDecimal amount) throws JournalDebitCreditDifferentCurrencyException {
        if(!debitAccount.getCurrency().equals(creditAccount.getCurrency())){
            throw new JournalDebitCreditDifferentCurrencyException();
        }
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, String description, IAccount debitAccount, IAccount creditAccount, BigDecimal amount) {
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IAccount debitAccount, IAccount creditAccount, BigDecimal amount) throws JournalDebitCreditDifferentCurrencyException {
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IBalanceSheetAccount debitAccount, IBalanceSheetAccount creditAccount, BigDecimal amount) throws JournalDebitCreditDifferentCurrencyException {
        if(!debitAccount.getCurrency().equals(creditAccount.getCurrency())){
            throw new JournalDebitCreditDifferentCurrencyException();
        }
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, amount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, amount));
    }

    public Journal(LocalDate txDate, String description, IAccount debitAccount, BigDecimal debitAmount, IAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    public Journal(LocalDate txDate, String description, IBalanceSheetAccount debitAccount, BigDecimal debitAmount, IBalanceSheetAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        if(!debitAccount.getCurrency().equals(creditAccount.getCurrency()) && !Objects.equals(debitAmount, creditAmount)){
            throw new JournalAmountNotBalanceException();
        }
        this.txDate = txDate;
        this.postedDate = txDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IAccount debitAccount, BigDecimal debitAmount, IAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    public Journal(LocalDate txDate, LocalDate postedDate, String description, IBalanceSheetAccount debitAccount, BigDecimal debitAmount, IBalanceSheetAccount creditAccount, BigDecimal creditAmount) throws JournalAmountNotBalanceException {
        if(!debitAccount.getCurrency().equals(creditAccount.getCurrency()) && !Objects.equals(debitAmount, creditAmount)){
            throw new JournalAmountNotBalanceException();
        }
        this.txDate = txDate;
        this.postedDate = postedDate;
        this.description = description;
        this.debitEntries = List.of(new JournalEntry(debitAccount, debitAmount));
        this.creditEntries = List.of(new JournalEntry(creditAccount, creditAmount));
    }

    @Override
    public LocalDate getTxDate(){
        return this.txDate;
    }

    @Override
    public LocalDate getPostedDate(){
        return this.postedDate;
    }

    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public List<IJournalEntry> getDebitEntries(){
        return this.debitEntries;
    }

    @Override
    public List<IJournalEntry> getCreditEntries(){
        return this.creditEntries;
    }
}
