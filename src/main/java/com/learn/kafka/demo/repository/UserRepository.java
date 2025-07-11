package com.learn.kafka.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.kafka.demo.model.User;

/**
 * Repository interface for User entity.
 * Provides CRUD operations for User entities in the H2 database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will automatically implement basic CRUD operations
    // We can add custom query methods here if needed
}
