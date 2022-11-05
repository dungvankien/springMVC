package com.cg.model;

import com.cg.model.dto.CashFlowDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cash_flows")
public class CashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date time;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal amountMoney;

    private String description;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted = false;

    @OneToOne
    @JoinColumn (name = "method_id")
    private Method method;

    @OneToOne
    @JoinColumn (name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;
    public CashFlowDTO toCashFlowDTO(){
        return new CashFlowDTO()
                .setId(id)
                .setTime(time)
                .setMethod(method.toMethodDTO())
                .setCategory(category.toCategoryDTO())
                .setDescription(description)
                .setAmountMoney(amountMoney)
                .setUser(user.toUserDTO());
    }
}
