package com.abiliu.notify.repositories;

import com.abiliu.notify.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByGuid(String guid);
}
