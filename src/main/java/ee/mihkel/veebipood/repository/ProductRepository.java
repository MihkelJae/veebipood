package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrderByIdAsc();
    Page<Product> findByOrderByIdAsc(Pageable pageable);

    Page<Product> findByCategory_IdOrderByIdAsc(Long id, Pageable pageable);

}
