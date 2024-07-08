package com.net.banking.assembler;

import com.net.banking.dto.AccountDto;
import com.net.banking.entity.Account;

public class AccountAssembler {

	public static Account mapToAcc (AccountDto dto) {
		Account acc = new Account(
				dto.getId(),
				dto.getAccHolderName(),
				dto.getBalance()
				);
		return acc;
	}
	
	public static AccountDto mapToDto (Account acc) {
		AccountDto dto = new AccountDto(
				acc.getId(),
				acc.getAccHolderName(),
				acc.getBalance()
				);
		
		return dto;
		
	}
}
