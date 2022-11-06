package com.cg.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CashFlowStringDTO  {
    private Long id;
    private String time;

    private MethodDTO method;

    private BigDecimal amountMoney;
    @Size(min = 2, message = "Diễn giải lớn hơn 2 ký tự")
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
