package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM tb_user u where u.email = ? and u.password = ?", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);
}
