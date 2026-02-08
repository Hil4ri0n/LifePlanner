package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
}
