package com.hcl.jtl.stocktradeservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.jtl.stocktradeservice.entity.StockTradeDetails;
import com.hcl.jtl.stocktradeservice.exception.ObjectAlreadyExistsException;
import com.hcl.jtl.stocktradeservice.exception.ObjectNotExistsException;
import com.hcl.jtl.stocktradeservice.repository.StockTradeDetailsRepository;
import com.hcl.jtl.stocktradeservice.service.StockTradeService;
import com.hcl.jtl.stocktradeservice.util.Constants;

import jakarta.transaction.Transactional;

@Service
public class StockTradeServiceImpl implements StockTradeService {
	
	Logger logger = LoggerFactory.getLogger(StockTradeServiceImpl.class);
	
	@Autowired
	private StockTradeDetailsRepository stockTradeDetailsRepository;
	
	@Override
	public List<StockTradeDetails> getStockTradDetails() {
		return stockTradeDetailsRepository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteStockTradeByStockCode(int stockCode) throws ObjectNotExistsException{
		Optional<StockTradeDetails> stockTradeDetails = stockTradeDetailsRepository.findStockTradeDetailsByStockCode(stockCode);
		if (stockTradeDetails.isPresent()) {
			stockTradeDetailsRepository.deleteStockTradeDetailsByStockCode(stockCode);
			logger.info(stockCode+" stockCode is deleted");
		}else {
			logger.error(stockCode+" "+Constants.RECORD_NOT_EXISTS);
			throw new ObjectNotExistsException(Constants.RECORD_NOT_EXISTS);
		}
	}
	
	@Override
	public String deleteAllStockTrade() {
		stockTradeDetailsRepository.deleteAll();
		logger.info("All StockTradeDetails records are deleted successfully.....");
		return "All StockTradeDetails records are deleted successfully.....";
	}
	
	@Override
	public StockTradeDetails createStockTradDetails(StockTradeDetails stockTradeDetails) throws ObjectAlreadyExistsException {
		Optional<StockTradeDetails> optStock = stockTradeDetailsRepository.findStockTradeDetailsByStockCode(stockTradeDetails.getStockCode());
		if (!optStock.isPresent()) {
			return stockTradeDetailsRepository.save(stockTradeDetails);
		}else {
			logger.error(stockTradeDetails.getStockCode()+" "+Constants.RECORD_ALREADY_EXISTS);
			throw new ObjectAlreadyExistsException(Constants.RECORD_ALREADY_EXISTS);
		}
	}

	@Override
	public StockTradeDetails findStockTradeDetailsByStockCode(int stockCode) throws ObjectNotExistsException {
		Optional<StockTradeDetails> stockTradeDetails = stockTradeDetailsRepository.findStockTradeDetailsByStockCode(stockCode);
		if (stockTradeDetails.isPresent()) {
			logger.info(stockTradeDetails.get().toString());
			return stockTradeDetails.get();
		}else {
			logger.error(stockCode+" "+Constants.RECORD_NOT_EXISTS);
			throw new ObjectNotExistsException(Constants.RECORD_NOT_EXISTS);
		}
	}
	
}