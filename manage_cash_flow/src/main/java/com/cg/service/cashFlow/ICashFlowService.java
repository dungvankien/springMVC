package com.cg.service.cashFlow;

import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ICashFlowService extends IGeneralService<CashFlow> {
    List<CashFlowDTO> findAllByDeletedIsFalse();
    void softDelete(@Param("cashFlowId") Long cashFlowId);
    List<CashFlowDTO> choiceMethod(@Param("methodId") Long methodId);

    BigDecimal getSumChoiceMoney(@Param("methodId") Long methodId);

    Date getDateStart();
    Date getDateEnd();

    List<CashFlowDTO> getAllCashFlowBetweenDate(@Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}
