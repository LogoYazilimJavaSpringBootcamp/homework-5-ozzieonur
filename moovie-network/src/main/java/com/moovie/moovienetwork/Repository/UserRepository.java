package com.moovie.moovienetwork.Repository;

import com.moovie.moovienetwork.Model.Enums.Role;
import com.moovie.moovienetwork.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByRole(Role role);
}
