package com.cg.service.category;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<CategoryDTO> getAllCategorysDTO() {
        return categoryRepository.getAllCategorysDTO();
    }
}
