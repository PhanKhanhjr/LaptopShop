package vn.hoidanit.laptopshop.controller;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;


@Controller
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }


    @RequestMapping("/")
    public String getHomePage(Model model) {
        return "phankhanh";
    }

    @RequestMapping("/admin/user")
    public String getUsersPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping("/admin/user/{id}")
    //Lấy giá trị trên URL
    public String userDetailPage(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
//        User useri4 = this.userService.getUserById(id);
        model.addAttribute("user", this.userService.getUserById(id));
        return "admin/user/user-detail";
    }

    @RequestMapping(value = "/admin/user/edit/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("editUser", currentUser);
        return "admin/user/user-edit";
    }

    @PostMapping(value = "admin/user/update")
    public String postUpdateUser(@ModelAttribute User editUser) {
        User currentUser = this.userService.getUserById(editUser.getId());
        if (currentUser != null) {
            currentUser.setAddress(editUser.getAddress());
            currentUser.setFullName(editUser.getFullName());
            currentUser.setPhone(editUser.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String deleteUser(@PathVariable long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("id", id);
        model.addAttribute("deleteUser", user);
        return "admin/user/user-delete";
    }

    @PostMapping(value = "/admin/user/delete")
    public String postDeleteUser(@ModelAttribute User deleteUser) {
        this.userService.deleteUserById(deleteUser.getId());
        return "redirect:/admin/user";
    }

    //Mặc định không truyền gì thì sẽ là phương thức GET.
//    @GetMapping
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User nguoidung) {
        //dat nguoi dung cho de phan biet
        this.userService.handleSaveUser(nguoidung);
        return "redirect:/admin/user";
    }
}


