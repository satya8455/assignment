package com.watsoo.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.dto.WalletDto;
import com.watsoo.wallet.dto.WalletTypeDto;
import com.watsoo.wallet.service.ValidationService;
import com.watsoo.wallet.service.WalletService;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private ValidationService validationService;
	
	

	@PostMapping("/create/wallet")
	public ResponseEntity<?> createWallet(@RequestBody WalletDto walletDto) {
		Response<?> validationResponse = validationService.checkForWalletPayLoad(walletDto);
		if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
			Response<?> response = walletService.createWallet(walletDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
		}
		return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	}

	@GetMapping("/get/wallet/{id}")
	public ResponseEntity<?> getWalletById(@PathVariable Long id) {
		Response<?> response = walletService.getWalletById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/get/all/wallet/{userId}")
	public ResponseEntity<?> getAllWallet(@PathVariable Long userId) {
		Response<?> response = walletService.getAllWallet(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("get/wallet-type/{userId}")
	public ResponseEntity<?> getWalletTypeById(@PathVariable Long userId) {
		Response<?> response = walletService.getAllWalletType(userId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	
	@PostMapping("/create/wallet-type")
	public ResponseEntity<?> createWalletType(@RequestBody WalletTypeDto walletTypeDto) {
			Response<?> response = walletService.createWalletType(walletTypeDto);
			return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}
	@PostMapping("deduct/from/wallet")
	 public ResponseEntity<?> deductFromWallet(@RequestBody WalletDto walletDto) {
	  Response<?> validationResponse = validationService.checkForWalletDeductPayLoad(walletDto);
	  if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
	   Response<?> response = walletService.deductFromWallet(walletDto);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	  }
	  return new ResponseEntity<>(validationResponse, HttpStatus.valueOf(validationResponse.getResponseCode()));
	 }
}
