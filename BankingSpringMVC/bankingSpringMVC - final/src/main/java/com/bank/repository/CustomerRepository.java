package com.bank.repository;

import com.bank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findAllByDeletedIsFalse();

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Customer c SET c.balance = c.balance + :transferAmount WHERE c.id = :id")
    void incrementBalance(@Param("transferAmount")BigDecimal transferAmount, @Param("id") Long id );

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Customer c SET c.balance = c.balance - :transferAmount WHERE c.id = :id")
    void reduceBalance(@Param("transferAmount") BigDecimal transferAmount, @Param("id") Long id);

    @Query("SELECT c FROM Customer c WHERE c.deleted = false AND c.id <> :id")
    Iterable<Customer> findAllByDeletedIsFalseAndByIdWithOutSender(@Param("id") Long id);


}
