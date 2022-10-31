package com.cg.model.dto;

import com.cg.model.City;
import com.cg.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CityDTO {

    private String id;

    @NotBlank(message = "Vui lòng nhập thông tin thành phố!")
    private String cityName;

    @NotBlank(message = "Vui lòng nhập thông tin diện tích!")
    @Pattern(regexp = "^\\d*(\\.\\d+)?$", message = "Vui lòng kiểm tra thông tin diện tích!")
    private String area;

    @NotBlank(message = "Vui lòng nhập thông tin dân số!")
    @Pattern(regexp = "^[0-9]+$", message = "Dân số phải là số nguyên dương!")
    private String population;

    @NotBlank(message = "Vui lòng nhập thông tin GDP!")
    @Pattern(regexp = "^[0-9]+$")
    private String gdp;

    @NotBlank(message = "Vui lòng nhập thông tin giới thiệu!")
    private String description;

    private CountryDTO country;

    public CityDTO(Long id, String cityName, Double area, Long population, Long gdp, String description, Country country) {
        this.id = id.toString();
        this.cityName = cityName;
        this.area = area.toString();
        this.population = population.toString();
        this.gdp = gdp.toString();
        this.description = description;
        this.country = country.toCountryDTO();
    }

    public City toCity() {
        return new City()
                .setId(Long.parseLong(id))
                .setCityName(cityName)
                .setArea(Double.parseDouble(area))
                .setPopulation(Long.parseLong(population))
                .setGdp(Long.parseLong(gdp))
                .setDescription(description)
                .setCountry(country.toCountry());
    }

}
