package lux.pe.na.store.service;

import lux.pe.na.store.model.dto.CategoryDto;
import lux.pe.na.store.model.dto.CategoryListDto;

import java.util.List;

public interface CategoryService {
    List<CategoryListDto> findAll();

    CategoryDto getById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    CategoryDto delete(Long id);
}
