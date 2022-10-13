package com.bank.service.transfer;

import com.bank.model.Transfer;
import com.bank.service.IGeneralService;

import java.math.BigDecimal;

public interface ITransferService extends IGeneralService<Transfer> {
    BigDecimal sumFeesAmount();
}
