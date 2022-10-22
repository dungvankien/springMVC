package com.bank.model.dto;

import com.bank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerCreateDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;

    private String balance;

    private LocationRegionDTO locationRegionDTO;
    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(new BigDecimal(0L))
                .setLocationRegion(locationRegionDTO.toLocationRegion());
    }
}
