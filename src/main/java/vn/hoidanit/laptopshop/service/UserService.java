package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public String handleHello() {
        return "Hello from service";
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        User fanKhanh = this.userRepository.save(user);
        System.out.println(fanKhanh);
        return fanKhanh;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> findByEmailAndAddress(String email, String address) {
        return this.userRepository.findByEmailAndAddress(email, address);
    }

    public User getUserById(long id) {
        return this.userRepository.getUserById(id);
    }
}
