package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.model.Note;
import com.hil4rion.lifeplanner.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Transactional(readOnly = true)
    public Note getNote(UUID id) {
        return noteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Note updateNoteText(UUID id, String newText) {
        Note note = getNote(id);
        note.setText(newText);
        return note;
    }

    public void deleteNote(UUID id) {
        noteRepository.deleteById(id);
    }
}
