package lux.pe.na.store.implementation;

import lux.pe.na.store.model.dto.CategoryDto;
import lux.pe.na.store.model.dto.CategoryListDto;
import lux.pe.na.store.model.entity.Category;
import lux.pe.na.store.repository.CategoryRepository;
import lux.pe.na.store.service.CategoryService;

import static lux.pe.na.store.utils.DataStatus.*;

import lux.pe.na.store.validation.ValidNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private CategoryDto convertToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    private CategoryListDto convertToListDto(Category category) {
        return mapper.map(category, CategoryListDto.class);
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    @Override
    public List<CategoryListDto> findAll() {
        List<Category> categories = repository.findByStatus(ENABLED);
        return categories.stream()
                .map(this::convertToListDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        return convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_ID + id)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        categoryDto.setStatus(ENABLED);
        Category category = convertToEntity(categoryDto);
        return convertToDto(repository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_ID + id));
        category.setName(categoryDto.getName());
        return convertToDto(repository.save(category));
    }

    @Override
    public CategoryDto delete(Long id) {
        Category category = repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_ID + id));
        category.setStatus(DISABLED);
        return convertToDto(repository.save(category));
    }
}
