package com.sda.java11.coursecenter.repositories;

import com.sda.java11.coursecenter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
      User findByUsername(String username);
}
