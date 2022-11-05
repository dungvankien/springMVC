package com.cg.repository;

import com.cg.model.Method;
import com.cg.model.dto.MethodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MethodRepository extends JpaRepository<Method, Long> {
    @Query("SELECT NEW com.cg.model.dto.MethodDTO(m.id, m.nameMethod)FROM Method m")
    List<MethodDTO> getAllMethodDTO();
}
