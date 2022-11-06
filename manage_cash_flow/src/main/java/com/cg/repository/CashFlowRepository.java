package com.cg.repository;

import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    @Query("SELECT NEW com.cg.model.dto.CashFlowDTO(" +
            "c.id, " +
            "c.time, " +
            "c.method," +
            "c.category, " +
            "c.description, " +
            "c.amountMoney, " +
            "c.user) " +
            "FROM CashFlow AS c " +
            "WHERE c.deleted = false ")
    List<CashFlowDTO> findAllByDeletedIsFalse();
    @Modifying
    @Query("UPDATE CashFlow AS c SET c.deleted = true WHERE c.id = :cashFlowId" )
    void softDelete(@Param("cashFlowId") Long cashFlowId);
    @Query("SELECT NEW com.cg.model.dto.CashFlowDTO(" +
            "c.id," +
            "c.time, " +
            "c.method, " +
            "c.category, " +
            "c.description, " +
            "c.amountMoney, " +
            "c.user) " +
            "FROM CashFlow AS c " +
            "WHERE c.deleted = false AND c.method.id = :methodId")
    List<CashFlowDTO> choiceMethod(@Param("methodId") Long methodId);

    @Query("SELECT SUM(c.amountMoney) FROM CashFlow AS c WHERE c.deleted = false AND c.method.id = :methodId")
    BigDecimal getSumChoiceMoney(@Param("methodId") Long methodId);

    @Query("SELECT MIN(c.time) FROM CashFlow AS c WHERE c.deleted = false")
    Date getDateStart();

    @Query("SELECT MAX(c.time) FROM CashFlow AS c WHERE c.deleted = false")
    Date getDateEnd();


    @Query("SELECT NEW com.cg.model.dto.CashFlowDTO(" +
            "c.id, " +
            "c.time, " +
            "c.method," +
            "c.category, " +
            "c.description, " +
            "c.amountMoney, " +
            "c.user) " +
            "FROM CashFlow AS c " +
            "WHERE (c.time BETWEEN :dateStart AND :dateEnd) AND (c.deleted = false )")
    List<CashFlowDTO> getAllCashFlowBetweenDate(@Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);


}
