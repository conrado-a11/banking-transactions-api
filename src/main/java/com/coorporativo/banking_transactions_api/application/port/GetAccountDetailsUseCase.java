package com.coorporativo.banking_transactions_api.application.port;

import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;

public interface GetAccountDetailsUseCase {

    AccountDetailsDto execute(String accountId);
}
