package com.coorporativo.banking_transactions_api.application.port;

import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.CreateAccountCommand;

public interface CreateAccountUseCase {

    AccountDetailsDto execute (CreateAccountCommand command);


}
