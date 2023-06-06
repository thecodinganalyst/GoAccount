package com.hevlar.accounts.domain.exceptions;

public class JournalAmountNotBalanceException extends Exception{
    @Override
    public String getMessage() {
        return "Journal amount is not balanced";
    }
}
