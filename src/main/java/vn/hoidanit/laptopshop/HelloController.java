package vn.hoidanit.laptopshop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello World! fankhanh2022 update !!! oke hoat dong roi";
    }

    @GetMapping("/user")
    public String user() {
        return "from user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "from admin";
    }

}
