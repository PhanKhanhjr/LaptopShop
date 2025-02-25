package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
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

    public void handleAddProductToCart(String email, long productId) {
        // check lay ra user
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            //check user da co cart hay chua
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                //tao moi neu chua co cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart = this.cartRepository.save(newCart);
            }
            // tim product by id
            Optional<Product> product = this.productRepository.findById(productId);
            if (product.isPresent()) {
                Product realProduct = product.get();

                CartDetail cartDetail = new CartDetail();
                cartDetail.setProduct(realProduct);
                cartDetail.setCart(cart);
                cartDetail.setQuantity(1);
                cartDetail.setPrice(realProduct.getPrice());
                this.cartDetailRepository.save(cartDetail);
            }
        }
    }
}
