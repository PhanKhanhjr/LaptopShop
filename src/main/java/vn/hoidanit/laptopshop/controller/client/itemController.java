package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.hoidanit.laptopshop.domain.Product;

@Controller
public class itemController {

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable long id, Model model) {
        return "client/product/detail";
    }

    @GetMapping("/admin/product/addnew")
    public String getAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/addNewProduct";
    }
}
