package lux.pe.na.store.service;

import lux.pe.na.store.model.dto.ProductDto;
import lux.pe.na.store.model.dto.ProductListDto;

import java.util.List;

public interface ProductService {
    List<ProductListDto> findById();

    ProductDto getById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    ProductDto delete(Long id);
}
