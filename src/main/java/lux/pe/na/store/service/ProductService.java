package lux.pe.na.store.service;

import lux.pe.na.store.model.dto.FilterDto;
import lux.pe.na.store.model.dto.ProductDto;
import lux.pe.na.store.model.dto.ProductListDto;

import java.util.List;

public interface ProductService {
    List<ProductListDto> findAll();

    ProductDto getById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    ProductDto delete(Long id);

    List<ProductListDto> filterByName(FilterDto filterDto);
}
