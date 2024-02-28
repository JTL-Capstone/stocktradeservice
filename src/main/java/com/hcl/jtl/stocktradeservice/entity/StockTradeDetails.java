package com.hcl.jtl.stocktradeservice.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "stock_trade_details")
public class StockTradeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int stockCode;
	private String stockName;
	private float dayOpen;
	private float dayHigh;
	private float dayLow;
	private float tradedPrice;
	private float previousClose;
	private Date tradedDateTime;	
}