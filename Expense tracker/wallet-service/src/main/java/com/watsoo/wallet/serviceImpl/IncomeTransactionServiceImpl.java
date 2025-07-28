package com.watsoo.wallet.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.wallet.dto.IncomeTransactionDto;
import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.entity.IncomeTransaction;
import com.watsoo.wallet.entity.User;
import com.watsoo.wallet.entity.Wallet;
import com.watsoo.wallet.enums.Role;
import com.watsoo.wallet.repository.IncometransactionRepository;
import com.watsoo.wallet.repository.UserRepository;
import com.watsoo.wallet.repository.WalletRepository;
import com.watsoo.wallet.security.CustomizedUserDetailsService;
import com.watsoo.wallet.service.IncomeTransactionService;

@Service
public class IncomeTransactionServiceImpl implements IncomeTransactionService {

	@Autowired
	private CustomizedUserDetailsService customizedUserDetailsService;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private IncometransactionRepository incometransactionRepository;

	@Autowired
	private UserRepository userRepository;
	@Override
	public Response<?> createTransaction(IncomeTransactionDto incomeTransactionDto) {
		try {
			if (incomeTransactionDto.getId() == null) {
				Optional<Wallet> walletOptional = walletRepository
						.findById(incomeTransactionDto.getWalletDto().getId());
				if(walletOptional.isEmpty()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(),"wallet id is not present",null);
				}
				Wallet wallet = walletOptional.get();
				if (walletOptional.isPresent()) {
					IncomeTransaction incomeTransaction = new IncomeTransaction();
					incomeTransaction.setAmount(incomeTransactionDto.getAmount());
					incomeTransaction.setDate(new Date());
					incomeTransaction.setSource(incomeTransactionDto.getSource());
					incomeTransaction.setRemarks(incomeTransactionDto.getRemarks());
					incomeTransaction.setWallet(wallet);
					incometransactionRepository.save(incomeTransaction);
					// update wallet
					wallet.setBalance(wallet.getBalance() + incomeTransactionDto.getAmount());
					walletRepository.save(wallet);
					return new Response<>(HttpStatus.OK.value(), "Transaction created", null);
				}
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "wallet not found", null);
			}

			Optional<IncomeTransaction> existingTransaction = incometransactionRepository
					.findById(incomeTransactionDto.getId());
			if (!existingTransaction.isPresent()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "No transaction found to update", null);
			}
			IncomeTransaction incomeTransaction = existingTransaction.get();
			Double oldAmount = incomeTransaction.getAmount();
			Double newAmount = incomeTransactionDto.getAmount();

			if (newAmount != null && !newAmount.equals(oldAmount)) {

				Wallet wallet = incomeTransaction.getWallet();

				wallet.setBalance(wallet.getBalance() + (newAmount - oldAmount));
				walletRepository.save(wallet);
				incomeTransaction.setAmount(newAmount);
			}

			if (incomeTransactionDto.getRemarks() != null) {
				incomeTransaction.setRemarks(incomeTransactionDto.getRemarks());
			}
			if (incomeTransactionDto.getSource() != null) {
				incomeTransaction.setSource(incomeTransactionDto.getSource());
			}

			incometransactionRepository.save(incomeTransaction);

			return new Response<>(HttpStatus.OK.value(), "Transaction updated", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "something went wrong", null);
		}
	}

	public Response<?> getAllTransactions(Long userId,Long walletId, String startDate, String endDate) {
		try {
			Optional<User> userOpt = userRepository.findById(userId);
			 if (userOpt.isEmpty()) {
				 return new Response<>(HttpStatus.BAD_REQUEST.value(),"user not found",null);
			    }
			  List<IncomeTransaction> transactions;
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			  LocalDate startdate = null;
			  LocalDate enddate = null;

			  if (startDate != null && endDate != null) {
			      startdate = LocalDate.parse(startDate.trim(), formatter);
			      enddate = LocalDate.parse(endDate.trim(), formatter);
			  } else if (startDate != null || endDate != null) {
			      return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide both startDate and endDate", null);
			  }

			  if (walletId != null) {
			        Optional<Wallet> walletOpt = walletRepository.findById(walletId);
			        if (walletOpt.isEmpty()) {
					 return new Response<>(HttpStatus.BAD_REQUEST.value(),"Wallet not found",null);
			        }
			        Wallet wallet = walletOpt.get();
			        if (!wallet.getUser().getId().equals(userId)) {
			   			 return new Response<>(HttpStatus.BAD_REQUEST.value(),"You have no permission to access this wallet",null);
			        }
			        
			        if (startdate != null) {
			            transactions = incometransactionRepository.findByWalletAndDateRange(walletId, startdate, enddate);
			        } else {
			            transactions = incometransactionRepository.findByWallet_Id(walletId);
			        }
			  }
			  else {
				  
				  if (startDate != null) {
			            transactions = incometransactionRepository.findByUserIdAndDateRange(userId, startdate, enddate);
			        } else {
			            transactions = incometransactionRepository.findByUserId(userId);
			        }
			    }
			  List<IncomeTransactionDto> dtos = transactions.stream()
			            .map(IncomeTransaction::convertToDto)
			            .collect(Collectors.toList());

			 return new Response<>(HttpStatus.OK.value(),"Transactions fetched", dtos);
		} catch (Exception e) {
			e.printStackTrace();
			 return new Response<>(HttpStatus.BAD_REQUEST.value(),"Something went wrong", null);
		}
	}

	
	
	
	}

	
	

