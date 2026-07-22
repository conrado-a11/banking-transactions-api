package com.coorporativo.banking_transactions_api.application.port;

import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.WithdrawMoneyCommand;

public interface WithdrawMoneyUseCase {
    AccountDetailsDto execute (WithdrawMoneyCommand command);
}
