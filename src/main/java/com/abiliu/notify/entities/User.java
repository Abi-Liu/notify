package com.abiliu.notify.entities;

import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_feeds",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_id")
    )
    @ToString.Exclude
    private List<Feed> followedFeeds;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
        apiKey = generateApiKey();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    private String generateApiKey() {
        String randomValue = UUID.randomUUID().toString();
        return Hashing.sha256()
                .hashString(randomValue, StandardCharsets.UTF_8)
                .toString();
    }
}
