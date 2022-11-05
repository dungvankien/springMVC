package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CashFlowStringDTO {
    private Long id;

    private String time;

    private MethodDTO method;

    private BigDecimal amountMoney;

    private String description;

    private CategoryDTO category;

    private UserDTO user;

    public CashFlowDTO toCashFlowDTO() throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);

        return new CashFlowDTO()
                .setId(id)
                .setTime(date)
                .setMethod(method)
                .setAmountMoney(amountMoney)
                .setCategory(category)
                .setDescription(description)
                .setUser(user);
    }
}
