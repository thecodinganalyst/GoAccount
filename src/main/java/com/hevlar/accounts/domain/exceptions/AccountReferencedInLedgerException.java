package com.hevlar.accounts.domain.exceptions;

public class AccountReferencedInLedgerException extends Exception{
    @Override
    public String getMessage() {
        return "Account has been referenced in the general ledger and cannot be removed";
    }
}
