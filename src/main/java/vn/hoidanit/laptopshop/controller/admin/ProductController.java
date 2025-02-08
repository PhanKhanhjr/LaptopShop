package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import java.util.List;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final UploadService uploadService;
    private final ProductService productService;
    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }
    @GetMapping("/admin/product")
    public String getProductsController(Model model) {
        List<Product> prs = productService.getAllProducts();
        model.addAttribute("products", prs);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getProductCreatePage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("admin/product/create")
    public String handleCreateProduct(
            @Valid @ModelAttribute("newProduct")  Product pr ,
            BindingResult newProductBindingResult,
            Model model,
            @RequestParam("phankhanhfile") MultipartFile file)
    {
            if(newProductBindingResult.hasErrors()) {
                return "admin/product/create";
            }
            String image = this.uploadService.handelUploadFile(file,  "product");
            pr.setImage(image);
            this.productService.createProduct(pr);
            return "redirect:/admin/product";
    }
    @GetMapping(value = "/admin/product/delete/{id}")
    public String handleDeleteProduct(@PathVariable long id, Model model) {
        Product pr = new Product();
        pr.setId(id);
        model.addAttribute("deletePr", pr);
        model.addAttribute("id", id);
        return "/admin/product/product-delete";
    }

    @PostMapping(value = "/admin/product/delete")
    public String postDeleteProduct(@ModelAttribute Product pr){
        this.productService.deleteProduct(pr.getId());
        return "redirect:/admin/product";
    }

    @GetMapping(value = "/admin/product/update/{id}")
    public String getProductEditPage(@PathVariable long id, Model model) {
        Product pr = this.productService.getProductById(id);
        model.addAttribute("editPr", pr);
        return "admin/product/product-edit";
    }
    @PostMapping(value = "/admin/product/update")
    public String handleEditProduct(@ModelAttribute("editPr") @Valid Product editPr, BindingResult editPrBindingResult, @RequestParam("phankhanhfile") MultipartFile file) {
        Product currentPr = this.productService.getProductById(editPr.getId());
        if(editPrBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        if(currentPr != null) {

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename(); // Hoặc logic lưu file của bạn
                currentPr.setImage(fileName);
            }
            currentPr.setName(editPr.getName());
            currentPr.setPrice(editPr.getPrice());
            currentPr.setQuantity(editPr.getQuantity());
            currentPr.setDetailDesc(editPr.getDetailDesc());
            currentPr.setShortDesc(editPr.getShortDesc());
            currentPr.setFactory(editPr.getFactory());
            currentPr.setTarget(editPr.getTarget());
            this.productService.handleSaveProduct(currentPr);
        } return "redirect:/admin/product";
    }
}
