package lux.pe.na.store.controller;

import lux.pe.na.store.model.dto.CategoryListDto;
import lux.pe.na.store.model.dto.FilterDto;
import lux.pe.na.store.model.dto.ProductDto;
import lux.pe.na.store.model.dto.ProductListDto;
import lux.pe.na.store.service.ProductService;

import static lux.pe.na.store.utils.DataStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/get")
    public ResponseEntity<List<ProductListDto>> findAll(@RequestBody FilterDto filterDto) {
        List<ProductListDto> products;
        if (filterDto.getName().equals("")) {
            products = service.findAll();
        } else {
            products = service.filterByName(filterDto);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    //@PreAuthorize(ROLE_USER)
    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(service.save(productDto), HttpStatus.CREATED);
    }

    //@PreAuthorize(ROLE_ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(service.update(id, productDto), HttpStatus.CREATED);
    }

    //@PreAuthorize(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}
