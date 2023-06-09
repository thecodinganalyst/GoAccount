package com.hevlar.accounts.domain.exceptions;

public class JournalTxDateIsBeforeFyStartDateException extends Exception{
    @Override
    public String getMessage() {
        return "Journal transaction date is before financial year start date";
    }
}
