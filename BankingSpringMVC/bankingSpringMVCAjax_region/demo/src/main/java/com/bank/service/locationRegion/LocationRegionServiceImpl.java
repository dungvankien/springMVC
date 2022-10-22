package com.bank.service.locationRegion;

import com.bank.model.LocationRegion;
import com.bank.repository.LocationRegionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class LocationRegionServiceImpl implements ILocationRegionService {
    @Autowired
    private LocationRegionRespository locationRegionRespository;
    @Override
    public Iterable<LocationRegion> findfAll() {
        return null;
    }

    @Override
    public LocationRegion getById(Long id) {
        return null;
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRespository.save(locationRegion);
    }

    @Override
    public void remove(Long id) {

    }
}
