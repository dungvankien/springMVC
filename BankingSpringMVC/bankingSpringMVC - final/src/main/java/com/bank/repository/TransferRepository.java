package com.bank.repository;

import com.bank.model.Customer;
import com.bank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Query("SELECT SUM(feesAmount) FROM Transfer")
    BigDecimal sumFeesAmount();
}
