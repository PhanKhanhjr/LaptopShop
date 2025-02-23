package vn.hoidanit.laptopshop.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.DTO.RegisterDTO;
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

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
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

    //mapper registerDTO to user
    public User registerDTOtoUser(RegisterDTO dto) {
        User user = new User();
        user.setFullName(dto.getFirstName() + " " + dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
