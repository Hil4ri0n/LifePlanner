package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
