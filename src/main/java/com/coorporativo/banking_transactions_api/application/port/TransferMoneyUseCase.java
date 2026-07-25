package com.coorporativo.banking_transactions_api.application.port;

import com.coorporativo.banking_transactions_api.application.dto.TransferMoneyCommand;

public interface TransferMoneyUseCase {

    void execute(TransferMoneyCommand command);
}
