package com.hevlar.accounts.domain.exceptions;

public class AccountHasJournalBeforeDateException extends Exception{
    @Override
    public String getMessage() {
        return "Account has journal before the opening date";
    }
}
