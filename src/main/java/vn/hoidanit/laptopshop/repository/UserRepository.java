package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;

import java.util.List;

//CRUD CREATE, READ, UPDATE, DELETE;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User fanKhanh);

    List<User> findByEmailAndAddress(String email, String address);

    User getUserById(long id);
}
