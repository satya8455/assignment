package com.watsoo.wallet.entity;

import com.watsoo.wallet.dto.WalletDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private Double balance ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wallet(Long id, String name, Double balance, User user, WalletType walletType) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.user = user;
		this.walletType = walletType;
	}

	public WalletDto convertToDto() {
		return new WalletDto(this.id!=null?this.id:null,
							this.name!=null?this.name:null,
							this.balance!=null?this.balance:null,
							this.user!=null?this.user.convertToDto():null,
							this.walletType!=null?this.walletType.convertToDto():null);
	}

    
}

