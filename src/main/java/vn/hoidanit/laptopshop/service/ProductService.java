package vn.hoidanit.laptopshop.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.*;
import vn.hoidanit.laptopshop.repository.*;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OderRepository oderRepository;
    private final OderDetailRepository oderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserService userService, OderRepository oderRepository, OderDetailRepository oderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.oderRepository = oderRepository;
        this.oderDetailRepository = oderDetailRepository;
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> getAllProducts(Pageable page) {
        return this.productRepository.findAll(page);
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

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        // check lay ra user
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            //check user da co cart hay chua
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                //tao moi neu chua co cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                cart = this.cartRepository.save(newCart);
            }
            // tim product by id
            Optional<Product> product = this.productRepository.findById(productId);
            if (product.isPresent()) {
                Product realProduct = product.get();

                //check xem san pham da duoc them vao gio hang truoc day hay chua
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setProduct(realProduct);
                    cartDetail.setCart(cart);
                    cartDetail.setQuantity(1);
                    cartDetail.setPrice(realProduct.getPrice());
                    this.cartDetailRepository.save(cartDetail);

                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }
            }
        }
    }

    public Cart fetchCart(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();
            // delete cart-detail
            this.cartDetailRepository.deleteById(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart (sum = 1)
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOder(User user, HttpSession session, String receiverAddress, String receiverName, String receiverPhone) {
        Cart cart = this.cartRepository.findByUser(user);

        //create Oder


        //create Oderdetail
        //step 1: get cart

        if (cart != null) {
            List<CartDetail> cartDetail = cart.getCartDetailLists();
            if (cartDetail != null) {
                Oder oder = new Oder();
                oder.setUser(user);
                oder.setReceiverAddress(receiverAddress);
                oder.setReceiverName(receiverName);
                oder.setReceiverPhone(receiverPhone);

                oder.setStatus("PENDING");

                double sum = 0;
                for (CartDetail detail : cartDetail) {
                    sum += detail.getPrice();
                }
                oder.setTotalPrice(sum);
                oder = this.oderRepository.save(oder);
                for (CartDetail detail : cartDetail) {
                    OderDetail oderDetail = new OderDetail();
                    oderDetail.setOder(oder);
                    oderDetail.setProduct(detail.getProduct());
                    oderDetail.setPrice(detail.getPrice());
                    oderDetail.setQuantity(detail.getQuantity());
                    this.oderDetailRepository.save(oderDetail);
                }
                for (CartDetail detail : cartDetail) {
                    this.cartDetailRepository.deleteById(detail.getId());
                }
                this.cartRepository.deleteById(cart.getId());

                session.setAttribute("sum", 0);
            }

        }
    }
}
