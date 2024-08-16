package com.abiliu.notify.repositories;

import com.abiliu.notify.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByCredentialsEmail(String email);

    Optional<User> findByApiKey(String apiKey);
}
