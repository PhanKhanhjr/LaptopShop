package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public String handleHello() {
        return "Hello from service";
    }

    public UserService(UserRepository userRepository,RoleRepository roleRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void handleSaveUser(User user) {
       this.userRepository.save(user);
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

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
    public Role getRoleByName(String name) {
         return this.roleRepository.findByName(name);
    }
}
