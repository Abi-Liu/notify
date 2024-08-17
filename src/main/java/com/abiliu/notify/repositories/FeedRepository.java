package com.abiliu.notify.repositories;

import com.abiliu.notify.entities.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {
    Optional<Feed> findByUrl(String url);

    @Query("SELECT f from Feed f ORDER BY f.updatedAt ASC")
    List<Feed> fetchNextFeeds(@Param("limit") int limit);
}
