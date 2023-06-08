package com.hevlar.accounts.domain.exceptions;

public class AccountOpeningDateIsBeforeFyStartDateException extends Exception {
    @Override
    public String getMessage() {
        return "Account opening date is before financial year start date";
    }
}
