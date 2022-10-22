package com.bank.repository;

import com.bank.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRegionRespository extends JpaRepository<LocationRegion, Long> {
}
