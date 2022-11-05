package com.cg.model.dto;

import com.cg.model.CashFlow;
import com.cg.model.Category;
import com.cg.model.Method;
import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CashFlowDTO {

    private Long id;

    private String time;

    private MethodDTO method;

    private BigDecimal amountMoney;

    private String description;

    private CategoryDTO category;

    private UserDTO user;

    public CashFlowDTO (Long id, String time, Method method, Category category, String description, BigDecimal amountMoney, User user){
        this.id = id;
        this.time = time;
        this.method = method.toMethodDTO();
        this.category = category.toCategoryDTO();
        this.description = description;
        this.amountMoney = amountMoney;
        this.user = user.toUserDTO();
    }

    @SneakyThrows
    public CashFlow toCashFlow(){

        return new CashFlow()
                .setId(id)
                .setTime(time)
                .setMethod(method.toMethod())
                .setCategory(category.toCategory())
                .setDescription(description)
                .setAmountMoney(amountMoney)
                .setUser(user.toUser());
    }
}
