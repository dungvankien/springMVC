package com.cg.service.cashFlow;

import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ICashFlowService extends IGeneralService<CashFlow> {
    List<CashFlowDTO> findAllByDeletedIsFalse();
    void softDelete(@Param("cashFlowId") Long cashFlowId);
    List<CashFlowDTO> choiceMethod(@Param("methodId") Long methodId);

    BigDecimal getSumChoiceMoney(@Param("methodId") Long methodId);
}
