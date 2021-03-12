package lux.pe.na.store.implementation;

import lux.pe.na.store.model.dto.FilterDto;
import lux.pe.na.store.model.dto.ProductDto;
import lux.pe.na.store.model.dto.ProductListDto;
import lux.pe.na.store.model.entity.Category;
import lux.pe.na.store.model.entity.Product;
import lux.pe.na.store.repository.ProductRepository;
import lux.pe.na.store.service.ProductService;

import static lux.pe.na.store.utils.DataStatus.*;

import lux.pe.na.store.validation.ValidNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private ProductListDto convertToListDto(Product product) {
        return mapper.map(product, ProductListDto.class);
    }

    private ProductDto convertToDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    @Override
    public List<ProductListDto> findAll() {
        List<Product> products = repository.findByStatus(ENABLED);
        return products.stream()
                .map(this::convertToListDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        return convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_ID + id)));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        productDto.setStatus(ENABLED);
        Product product = convertToEntity(productDto);
        return convertToDto(repository.save(product));
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        ProductDto edit = convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_ID + id)));
        edit.setName(productDto.getName());
        edit.setStock(productDto.getStock());
        edit.setPrice(productDto.getPrice());
        edit.setCategoryId(productDto.getCategoryId());
        Product product = convertToEntity(edit);
        return convertToDto(repository.save(product));
    }

    @Override
    public ProductDto delete(Long id) {
        ProductDto drop = convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_ID + id)));
        drop.setStatus(DISABLED);
        Product product = convertToEntity(drop);
        return convertToDto(repository.save(product));
    }

    @Override
    public List<ProductListDto> filterByName(FilterDto filterDto) {
        List<Product> products = repository.findByStatusAndName(ENABLED, filterDto.getName().toLowerCase());
        return products.stream()
                .map(this::convertToListDto)
                .collect(Collectors.toList());
    }
}
