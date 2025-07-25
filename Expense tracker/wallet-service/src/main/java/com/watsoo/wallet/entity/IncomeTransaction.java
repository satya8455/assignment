package com.watsoo.wallet.entity;

import java.util.Date;

import com.watsoo.wallet.dto.IncomeTransactionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "income_transactions")
public class IncomeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "source")
    private String source;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public IncomeTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncomeTransaction(Long id, Double amount, Date date, String source, String remarks, Wallet wallet) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.source = source;
		this.remarks = remarks;
		this.wallet = wallet;
	}

	
	public IncomeTransactionDto convertToDto() {
		return new IncomeTransactionDto(id,
										this.amount!=null?this.amount:null,
										this.date!=null?this.date:null,
										this.source!=null?this.source:null,
										this.remarks!=null?this.remarks:null,
										this.wallet!=null?this.wallet.convertToDto():null);
	}
	
   
}

