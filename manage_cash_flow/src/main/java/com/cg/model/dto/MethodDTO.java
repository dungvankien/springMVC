package com.cg.model.dto;

import com.cg.model.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MethodDTO {
    private Long id;

    private String nameMethod;

    public Method toMethod(){
        return new Method()
                .setId(id)
                .setNameMethod(nameMethod);
    }
}
