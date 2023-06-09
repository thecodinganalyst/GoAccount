package com.hevlar.accounts.infrastructure;

import com.hevlar.accounts.infrastructure.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account, String> {
}
