package com.example.pdfinvoice.repository;

import com.example.pdfinvoice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides built-in methods like:
    // save(), findById(), findAll(), deleteById(), etc.

    // You can add custom query methods if needed, e.g.:
    // List<User> findByName(String name);
}