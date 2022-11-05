package com.cg.repository;

import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
