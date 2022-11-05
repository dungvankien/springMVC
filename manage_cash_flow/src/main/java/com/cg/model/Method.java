package com.cg.model;

import com.cg.model.dto.MethodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "methods")
public class Method {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameMethod;


    public MethodDTO toMethodDTO(){
        return new MethodDTO()
                .setId(id)
                .setNameMethod(nameMethod);
    }
}
