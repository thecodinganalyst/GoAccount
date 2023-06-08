package com.hevlar.accounts.domain.exceptions;

public class JournalPostedDateIsAfterFyEndDateException extends Exception{
    @Override
    public String getMessage() {
        return "Journal posted date is after financial year end date";
    }
}
