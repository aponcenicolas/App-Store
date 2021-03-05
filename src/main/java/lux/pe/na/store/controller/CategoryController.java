package lux.pe.na.store.controller;

import lux.pe.na.store.model.dto.CategoryDto;
import lux.pe.na.store.model.dto.CategoryListDto;
import lux.pe.na.store.service.CategoryService;

import static lux.pe.na.store.utils.DataStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PreAuthorize(ROLE_USER)
    @PostMapping
    public ResponseEntity<CategoryDto> save(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(service.save(categoryDto), HttpStatus.CREATED);
    }

    @PreAuthorize(ROLE_ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(service.update(id, categoryDto), HttpStatus.CREATED);
    }

    @PreAuthorize(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}
