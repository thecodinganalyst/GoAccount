package com.hevlar.accounts.domain.exceptions;

public class JournalPostedDateIsBeforeFyStartDateException extends Exception{
    @Override
    public String getMessage() {
        return "Journal posted date is before financial year start date";
    }
}
