package com.watsoo.wallet.dto;

public class WalletDto {
	private Long id;

	private String name;

	private Double balance;

	private UserDto userDto;

	private WalletTypeDto walletTypeDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public WalletTypeDto getWalletTypeDto() {
		return walletTypeDto;
	}

	public void setWalletTypeDto(WalletTypeDto walletTypeDto) {
		this.walletTypeDto = walletTypeDto;
	}

	public WalletDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WalletDto(Long id, String name, Double balance, UserDto userDto, WalletTypeDto walletTypeDto) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.userDto = userDto;
		this.walletTypeDto = walletTypeDto;
	}

}
