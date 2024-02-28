package com.hcl.jtl.stocktradeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.jtl.stocktradeservice.entity.StockTradeDetails;

@Repository
public interface StockTradeDetailsRepository extends JpaRepository<StockTradeDetails, Integer>{
	
	void deleteStockTradeDetailsByStockCode(int stockCode);
	Optional<StockTradeDetails> findStockTradeDetailsByStockCode(int stockCode);
	
}