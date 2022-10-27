package com.cg.repository;

import com.cg.model.Country;
import com.cg.model.dto.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT NEW com.cg.model.dto.CountryDTO (" +
            "c.id, " +
            "c.nameCountry )" +
            "FROM Country c ")
    List<CountryDTO> getAllCountryDTO();

}
