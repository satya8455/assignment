package com.watsoo.wallet.service;

import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.dto.WalletDto;
import com.watsoo.wallet.dto.WalletTypeDto;

public interface WalletService {

	Response<?> createWallet(WalletDto walletDto);

	Response<?> createWalletType(WalletTypeDto walletTypeDto);

	Response<?> getAllWalletType(Long userId);
	public Response<?> getAllWallet(Long userId);

	Response<?> getWalletById(Long id);

	Response<?> deductFromWallet(WalletDto walletDto);

}
