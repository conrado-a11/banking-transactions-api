package com.coorporativo.banking_transactions_api.health;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class HealthController {

    @GetExchange("/")
    public String  HealthCheck(){
        return "ok";
    }
}
