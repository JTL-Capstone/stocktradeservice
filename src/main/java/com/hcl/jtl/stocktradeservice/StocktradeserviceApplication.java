package com.hcl.jtl.stocktradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StocktradeserviceApplication {

	public static void main(String args[]) {
		SpringApplication.run(StocktradeserviceApplication.class, args);
	}

}