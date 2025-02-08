package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homePageController {
    @GetMapping("/")
    public String homePage() {return "client/homepage/show";}
}
