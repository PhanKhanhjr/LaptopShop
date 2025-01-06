package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("test1", "test");
        return "phankhanh";
    }

    @RequestMapping("/admin/user")
    public String getUsersPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    //Mặc định không truyền gì thì sẽ là phương thức GET.
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        System.out.println(user);
        return "hello";
    }
}


