package com.cg.service.cashFlow;

import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import com.cg.repository.CashFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CashFlowServiceImpl implements ICashFlowService {

    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Override
    public List<CashFlow> findAll() {
        return null;
    }

    @Override
    public CashFlow getById(Long id) {
        return null;
    }

    @Override
    public Optional<CashFlow> findById(Long id) {
        return cashFlowRepository.findById(id);
    }

    @Override
    public CashFlow save(CashFlow cashFlow) {
        return cashFlowRepository.save(cashFlow);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<CashFlowDTO> findAllByDeletedIsFalse() {
        return cashFlowRepository.findAllByDeletedIsFalse();
    }

    @Override
    public void softDelete(Long cashFlowId) {
         cashFlowRepository.softDelete(cashFlowId);
    }

    @Override
    public List<CashFlowDTO> choiceMethod(Long methodId) {
        return cashFlowRepository.choiceMethod(methodId);
    }

    @Override
    public BigDecimal getSumChoiceMoney(Long methodId) {
        return cashFlowRepository.getSumChoiceMoney(methodId);
    }

    @Override
    public Date getDateStart() {
        return cashFlowRepository.getDateStart();
    }

    @Override
    public Date getDateEnd() {
        return cashFlowRepository.getDateEnd();
    }

    @Override
    public List<CashFlowDTO> getAllCashFlowBetweenDate(Date dateStart, Date dateEnd) {
        return cashFlowRepository.getAllCashFlowBetweenDate(dateStart, dateEnd);
    }
}
