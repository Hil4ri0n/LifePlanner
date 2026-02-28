package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
