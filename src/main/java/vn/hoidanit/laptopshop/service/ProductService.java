package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;

import java.util.List;


@Service
public class ProductService {
   private final ProductRepository productRepository;
   public ProductService(ProductRepository productRepository) {
       this.productRepository = productRepository;
   }

   public Product createProduct(Product product) {
       return this.productRepository.save(product);
   }

   public List<Product> getAllProducts() {
       return this.productRepository.findAll();
   }

   public void deleteProduct(long id) {
       this.productRepository.deleteById(id);
   }

   public Product getProductById(long id) {
       return this.productRepository.findById(id).orElse(null);
   }

   public void handleSaveProduct(Product product) {
       this.productRepository.save(product);
   }
}
