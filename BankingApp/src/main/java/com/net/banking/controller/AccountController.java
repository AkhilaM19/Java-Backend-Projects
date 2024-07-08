package com.net.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.banking.dto.AccountDto;
import com.net.banking.service.AccountService;

@RestController
@RequestMapping("/banking/accounts")
public class AccountController {
	
	
	private AccountService accService;

	public AccountController(AccountService accService) {
		super();
		this.accService = accService;
	}
	//add account REST api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto dto){
		return new ResponseEntity<>(accService.create_account(dto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
	 AccountDto dto =accService.getACCountById(id);
	 return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto dto = accService.deposit(id,amount);
		return ResponseEntity.ok(dto);
	}
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto dto = accService.withdraw(id, amount);
		return ResponseEntity.ok(dto);
	}
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAcc(@PathVariable Long id){
		accService.deleteAcc(id);
		return ResponseEntity.ok("Account deleted");
	}

}
