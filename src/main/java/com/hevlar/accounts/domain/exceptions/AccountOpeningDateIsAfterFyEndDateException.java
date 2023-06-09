package com.hevlar.accounts.domain.exceptions;

public class AccountOpeningDateIsAfterFyEndDateException extends Exception{
    @Override
    public String getMessage() {
        return "Account opening date is after financial year end date";
    }
}
