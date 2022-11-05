package com.cg.service.method;

import com.cg.model.Method;
import com.cg.model.dto.MethodDTO;
import com.cg.repository.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MethodServiceImpl implements IMethodService{

    @Autowired
    private MethodRepository methodRepository;
    @Override
    public List<Method> findAll() {
        return null;
    }

    @Override
    public Method getById(Long id) {
        return null;
    }

    @Override
    public Optional<Method> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Method save(Method method) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<MethodDTO> getAllMethodDTO() {
        return methodRepository.getAllMethodDTO();
    }
}
