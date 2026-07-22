package com.coorporativo.banking_transactions_api.config;


import com.coorporativo.banking_transactions_api.application.service.CreateAccountService;
import com.coorporativo.banking_transactions_api.application.service.DepositMoneyService;
import com.coorporativo.banking_transactions_api.application.service.GetAccountDetailsService;
import com.coorporativo.banking_transactions_api.application.service.WithdrawMoneyService;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateAccountService createAccountService(AccountRepository accountRepository){
        return new CreateAccountService(accountRepository);
    }
    @Bean
    public DepositMoneyService depositMoneyService(AccountRepository accountRepository){
        return new DepositMoneyService(accountRepository);
    }
    @Bean
    public WithdrawMoneyService withdrawMoneyService(AccountRepository accountRepository){
        return new WithdrawMoneyService(accountRepository);
    }
    @Bean
    public GetAccountDetailsService getAccountDetailsService(AccountRepository accountRepository){
        return new GetAccountDetailsService(accountRepository);
    }

}
