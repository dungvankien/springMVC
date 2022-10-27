package com.cg.model.dto;

import com.cg.model.City;
import com.cg.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CountryDTO {
    private String id;

    private String nameCountry;

    public CountryDTO(Long id, String nameCountry) {
        this.id = id.toString();
        this.nameCountry = nameCountry;
    }

    public Country toCountry() {
        return new Country()
                .setId(Long.parseLong(id))
                .setNameCountry(nameCountry);
    }
}
