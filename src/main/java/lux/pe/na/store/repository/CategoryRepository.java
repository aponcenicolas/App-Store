package lux.pe.na.store.repository;

import lux.pe.na.store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStatus(boolean status);

    Optional<Category> findByStatusAndId(boolean status, Long id);

    List<Category> findByStatusAndName(boolean status, String name);
}
