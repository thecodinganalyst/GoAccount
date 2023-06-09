package com.hevlar.accounts.infrastructure;

import com.hevlar.accounts.infrastructure.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaAccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE (a.openingDate >= :startDate AND a.openingDate <= :endDate) OR a.openingDate IS NULL")
    List<Account> listAccounts(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM Account a WHERE (a.openingDate >= :startDate AND a.openingDate <= :endDate) ")
    List<Account> listBalanceSheetAccounts(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
