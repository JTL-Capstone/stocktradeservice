package com.hcl.jtl.stocktradeservice.service;

import java.util.List;

import com.hcl.jtl.stocktradeservice.entity.StockTradeDetails;
import com.hcl.jtl.stocktradeservice.exception.ObjectAlreadyExistsException;
import com.hcl.jtl.stocktradeservice.exception.ObjectNotExistsException;

public interface StockTradeService {
	
	List<StockTradeDetails> getStockTradDetails();
	
	StockTradeDetails createStockTradDetails(StockTradeDetails stockTradeDetails) throws ObjectAlreadyExistsException;
	
	void deleteStockTradeByStockCode(int stockCode) throws ObjectNotExistsException;
	
	String deleteAllStockTrade();
	
	StockTradeDetails findStockTradeDetailsByStockCode(int stockCode) throws ObjectNotExistsException;
	
}