package lux.pe.na.store.repository;

import lux.pe.na.store.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(boolean status);

    Optional<Product> findByStatusAndId(boolean status, Long id);

    List<Product> findByStatusAndName(boolean status, String name);
}
