package com.cg.model.dto;

import com.cg.model.User;
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
public class UserDTO {
    private Long id;

    private String fullName;

    public User toUser(){
        return new User()
                .setId(id)
                .setFullName(fullName);
    }
}
