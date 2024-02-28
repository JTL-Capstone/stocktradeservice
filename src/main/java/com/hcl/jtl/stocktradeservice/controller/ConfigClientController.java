package com.hcl.jtl.stocktradeservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Value("${spring.datasource.url}")
    private String exampleProperty;

    @GetMapping("/get-config")
    public String getConfig() {
        return "Configuration property value: " + exampleProperty;
    }
}
