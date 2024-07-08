package com.net.banking.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.banking.assembler.AccountAssembler;
import com.net.banking.dto.AccountDto;
import com.net.banking.entity.Account;
import com.net.banking.repository.AccountRepo;


@Service
public class AccountService {

	private AccountRepo accRepo;
	
	public AccountService(AccountRepo accRepo) {
		super();
		this.accRepo = accRepo;
	}

	public AccountDto create_account(AccountDto accDto) {
		Account acc = AccountAssembler.mapToAcc(accDto);
		Account savedAcc = accRepo.save(acc);
		return AccountAssembler.mapToDto(savedAcc);
	}

	
	public AccountDto getACCountById(Long id) {
		Account acc= accRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Acount does not exist"));
		return AccountAssembler.mapToDto(acc);
				
	}

	
	public AccountDto deposit(long id, double amount) {
		Account acc= accRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Acount does not exist"));
		double total = acc.getBalance() + amount;
		acc.setBalance(total);
		Account savedAcc = accRepo.save(acc);
		return AccountAssembler.mapToDto(savedAcc);
	}

	
	public AccountDto withdraw(long id, double amount) {
		Account acc= accRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Acount does not exist"));
		if(acc.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		double total = acc.getBalance() - amount;
		acc.setBalance(total);
		Account savedAcc = accRepo.save(acc);
		return AccountAssembler.mapToDto(savedAcc);
	}

	
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accRepo.findAll();
		return accounts.stream().map((acc) -> AccountAssembler.mapToDto(acc))
			.collect(Collectors.toList());
		
	}

	
	public void deleteAcc(Long id) {
		Account acc= accRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Acount does not exist"));
		 accRepo.deleteById(id);
		
	}


}
