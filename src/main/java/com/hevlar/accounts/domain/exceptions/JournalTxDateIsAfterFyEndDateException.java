package com.hevlar.accounts.domain.exceptions;

public class JournalTxDateIsAfterFyEndDateException extends Exception{
    @Override
    public String getMessage() {
        return "Journal transaction date is after financial year end date";
    }
}
