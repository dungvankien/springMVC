package com.example.phonemanagement.repository;

import com.example.phonemanagement.model.Smartphone;
import org.springframework.data.repository.CrudRepository;

public interface ISmartphoneRepository extends CrudRepository<Smartphone, Long> {
}
