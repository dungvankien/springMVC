package com.cg.model.dto;

import com.cg.model.Category;
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
public class CategoryDTO {
    private Long id;

    private String nameCategory;

    public Category toCategory(){
        return new Category()
                .setId(id)
                .setNameCategory(nameCategory);
    }

}
