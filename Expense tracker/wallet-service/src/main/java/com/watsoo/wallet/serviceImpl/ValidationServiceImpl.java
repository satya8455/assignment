package com.watsoo.wallet.serviceImpl;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.wallet.dto.IncomeTransactionDto;
import com.watsoo.wallet.dto.LoginRequest;
import com.watsoo.wallet.dto.RegistrationDto;
import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.dto.WalletDto;
import com.watsoo.wallet.dto.WalletTypeDto;
import com.watsoo.wallet.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

	// Login Validation
	@Override
	public Response<Object> checkForLoginPayload(LoginRequest loginRequest) {
		if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide the email.", null);
		} else if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide the password.", null);
		}
		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

	@Override
	public Response<Object> checkForUserRegistrationPayload(RegistrationDto dto) {
		if (dto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide data for registration.", null);
		}

		// Create
		if (dto.getId() == null) {

			if (dto.getName() == null || dto.getName().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide name.", null);
			} else if (!checkFullName(dto.getName())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid name.", null);
			} else if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide password.", null);
			} else if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide email.", null);
			} else if (!checkMail(dto.getEmail())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid email.", null);
			} else if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide phone number.", null);
			} else if (!checkPhoneNo(dto.getPhoneNumber())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid phone number.", null);
			} else if (dto.getRole() == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide ROLE[USER/ADMIN].", null);
			}

		} else {
			// Update
			if ((dto.getName() == null || dto.getName().isEmpty())
					&& (dto.getEmail() == null || dto.getEmail().isEmpty())
					&& (dto.getPhoneNumber() == null || dto.getPhoneNumber().isEmpty())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide at least one field for update.",
						null);
			}
		}

		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

	// Name Validation
	@Override
	public boolean checkFullName(String name) {
		return Pattern.matches("^[a-zA-Z](\\.|[a-zA-Z]+)(\\s[a-zA-Z]+|\\s[a-zA-Z]\\.){0,10}$", name);
	}

	// Email Validation
	@Override
	public boolean checkMail(String mail) {
		return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", mail);
	}

	@Override
	public boolean checkPhoneNo(String phoneNo) {
		return Pattern.matches("^(\\+91)?\\d{10}$", phoneNo);
	}

	@Override
	public Response<?> checkForWalletPayLoad(WalletDto walletDto) {
		if (walletDto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet should not be null.", null);
		} else if (walletDto.getName() == null || walletDto.getName().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Give name of wallet.", null);
		} else if (walletDto.getWalletTypeDto().getId() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Give wallet-type id .", null);
		} else if (walletDto.getBalance() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet balance should not be null.", null);
		} else if (walletDto.getBalance() <= 0) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Balance should be greater than zero .", null);
		}
		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

	@Override
	public Response<?> checkForIncomePayload(IncomeTransactionDto incomeTransactionDto) {
		if (incomeTransactionDto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Transaction should not be null.", null);
		}

		if (incomeTransactionDto.getId() == null) {
			if (incomeTransactionDto.getAmount() == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Amount should not be null.", null);
			} else if (incomeTransactionDto.getAmount() <= 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Amount should be greater than zero.", null);
			}

			if (incomeTransactionDto.getWalletDto() == null || incomeTransactionDto.getWalletDto().getId() == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet ID is required.", null);
			}

			if (incomeTransactionDto.getSource() == null || incomeTransactionDto.getSource().trim().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Source is required.", null);
			}
		} else {
			if ((incomeTransactionDto.getAmount() == null || incomeTransactionDto.getAmount() <= 0)
					&& (incomeTransactionDto.getSource() == null
							|| incomeTransactionDto.getSource().trim().isEmpty())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide at least one field for update.",
						null);
			}
		}

		return new Response<>(HttpStatus.OK.value(), "OK", null);
	}

	@Override
	public Response<?> checkForWalletDeductPayLoad(WalletDto walletDto) {
		if (walletDto == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet should not be null.", null);
		} else if (walletDto.getId() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Id is required.", null);
		} else if (walletDto.getBalance() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Wallet balance should not be null.", null);
		} else if (walletDto.getBalance() <= 0) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Balance should be greater than zero .", null);
		} else if (walletDto.getUserDto() == null || walletDto.getUserDto().getId() == null) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "User id is required .", null);
		}
		return new Response<>(HttpStatus.OK.value(), "OK", null);

	}

}
