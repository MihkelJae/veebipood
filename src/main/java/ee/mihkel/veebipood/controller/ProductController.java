package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProduct() {
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("Coca", 1.2, true, ""));
//        products.add(new Product());
//        return products;
        return productRepository.findByOrderByIdAsc();
    }

    //pageable tekitab automaateselt järgmised @RequestParamid juurde:
    //public-products?page=0&sort=id
    @GetMapping("public-products")
    public Page<Product> getPublicProduct(@RequestParam Long categoryId, Pageable pageable) {
        if(categoryId == 0) {
            return productRepository.findByOrderByIdAsc(pageable);
        }
        return productRepository.findByCategory_IdOrderByIdAsc(categoryId, pageable);
    }

//    @GetMapping("products-by-category")
//    public Page<Product> getProductsByCategory(@RequestParam Long categoryId, Pageable pageable) {
//        return productRepository.findByCategory_IdOrderByIdAsc(categoryId, pageable);
//    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
//        productRepository.save(new Product(productName, 1.2, true, ""));
        if(product.getName() == null) {
            throw new RuntimeException("Toote nimi puudub");
        }
        if (product.getName().toLowerCase().charAt(0) == product.getName().charAt(0)) {
            throw new IllegalArgumentException("Toode väikse tähega");
        }
        if (product.getPrice() < 0) {
            throw new RuntimeException("Hind miinusmärgiga");
        }
        if (product.getId() !=null) { //&& productRepository.findById(product.getId()).isPresent()
            throw new RuntimeException("ID-d ei tohi lisada");
        }
        product.setActive(true);
        productRepository.save(product);
        return productRepository.findByOrderByIdAsc();
    }

//    @RequestParam /products?id=1 (2 või enam ja GET)
//    @PathVariable /products/1 (1 muutuja)
//    @RequestBody (POST)

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findByOrderByIdAsc();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow();
    }

    @PatchMapping("product-active")
    public List<Product> changeProductActive(@RequestParam Long id, @RequestParam Boolean active) {
        Product product = productRepository.findById(id).orElseThrow(); //getReferenceById(id);
        product.setActive(active);
        productRepository.save(product);
        return productRepository.findByOrderByIdAsc();
    }

    @PutMapping("products") // TODO: POST ja PUT päringute veateted samaks
    public List<Product> editProduct(@RequestBody Product product) {

        if (product.getName() == null || product.getName().toLowerCase().charAt(0) == product.getName().charAt(0)) {
            throw new RuntimeException("Toode peab olemas suure tähega");
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Hind peab olema pluss märgiga");
        }
        if (product.getId() == null || productRepository.findById(product.getId()).isEmpty()) {
            throw new RuntimeException("Sellist ID-ga toodet pole");
        }
        productRepository.save(product);
        return productRepository.findByOrderByIdAsc();
    }
}
