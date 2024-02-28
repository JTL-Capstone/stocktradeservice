package com.hcl.jtl.stocktradeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.jtl.stocktradeservice.entity.StockTradeDetails;
import com.hcl.jtl.stocktradeservice.exception.ObjectAlreadyExistsException;
import com.hcl.jtl.stocktradeservice.exception.ObjectNotExistsException;
import com.hcl.jtl.stocktradeservice.service.StockTradeService;

@RestController
@RequestMapping("/stocks")
public class StockTradeDetailsController {

	Logger logger = LoggerFactory.getLogger(StockTradeDetailsController.class);

	@Autowired
	private StockTradeService stockTradeService;

	@GetMapping("/welcome")
	public ResponseEntity<String> sayWelcome() {
		logger.info("Entering sayWelcome()");
		return new ResponseEntity<>("Welcome to Microservice Capstone Project for Stock Service", HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<StockTradeDetails>> getStockDetails() {
		try {
			logger.info("GetStockDetails Response --> "+stockTradeService.getStockTradDetails().toString());
			return new ResponseEntity<>(stockTradeService.getStockTradDetails(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in fetching all stocks");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{stockCode}")
	public ResponseEntity<StockTradeDetails> getStockTradeDetailsByStockCode(@PathVariable int stockCode) {
		try {
			logger.info("getStockTradeDetailsByStockCode()");
			return new ResponseEntity<>(stockTradeService.findStockTradeDetailsByStockCode(stockCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in fetching stocks for "+ stockCode);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<StockTradeDetails> createStockDetails(@RequestBody StockTradeDetails stockTradeDetails)
			throws ObjectAlreadyExistsException {
		logger.info("Entering createStockDetails()");
		return new ResponseEntity<>(stockTradeService.createStockTradDetails(stockTradeDetails), HttpStatus.OK);
	}

	@DeleteMapping("/{stockCode}")
	public ResponseEntity<String> deletePortPolioByStockCode(@PathVariable int stockCode)
			throws ObjectNotExistsException {
		stockTradeService.deleteStockTradeByStockCode(stockCode);
		logger.info(stockCode + " is deleted successffully...!");
		return new ResponseEntity<>(stockCode + " is deleted successffully...!", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteAllPortPolio() {
		logger.info("Deleted  all stock details ....!");
		return new ResponseEntity<>(stockTradeService.deleteAllStockTrade(), HttpStatus.OK);
	}

}