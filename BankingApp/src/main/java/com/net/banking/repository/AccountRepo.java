package com.net.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.net.banking.entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long> {

}
