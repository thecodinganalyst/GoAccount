package com.hevlar.accounts.domain.exceptions;

public class JournalDebitCreditDifferentCurrencyException extends Exception{
    @Override
    public String getMessage() {
        return "Currencies of debit account and credit account are different";
    }
}
