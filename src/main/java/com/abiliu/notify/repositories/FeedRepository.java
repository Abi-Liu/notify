package com.abiliu.notify.repositories;

import com.abiliu.notify.entities.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {
    Optional<Feed> findByUrl(String url);
}
