package com.hevlar.accounts.domain.valueobjects;

import com.hevlar.accounts.domain.valueobjects.EntryType;

import static com.hevlar.accounts.domain.valueobjects.EntryType.Credit;
import static com.hevlar.accounts.domain.valueobjects.EntryType.Debit;

public enum AccountGroup {
    FixedAsset(Debit),
    CurrentAsset(Debit),
    CurrentLiability(Credit),
    LongTermLiability(Credit),
    Revenue(Credit),
    Expense(Debit),
    Gain(Credit),
    Loss(Debit),
    Equity(Credit);
    public final EntryType entryType;

    AccountGroup(EntryType entryType){
        this.entryType = entryType;
    }

}
