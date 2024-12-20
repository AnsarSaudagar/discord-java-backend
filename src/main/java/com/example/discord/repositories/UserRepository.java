package com.example.discord.repositories;

import com.example.discord.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
    User findById(long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}