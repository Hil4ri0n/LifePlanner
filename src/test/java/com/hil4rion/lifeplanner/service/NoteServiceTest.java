package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.model.Note;
import com.hil4rion.lifeplanner.model.NoteScope;
import com.hil4rion.lifeplanner.model.UserProfile;
import com.hil4rion.lifeplanner.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private Note testNote;

    @BeforeEach
    void setUp() {
        UserProfile testUserProfile = new UserProfile();
        testNote = Note.builder()
                .id(UUID.randomUUID())
                .noteScope(NoteScope.DAY)
                .periodStartDate(LocalDate.now())
                .text("example note")
                .userProfile(testUserProfile)
                .build();
    }

    @Test
    void should_CreateNote() {
        // when
        noteService.createNote(testNote);

        // then
        verify(noteRepository).save(testNote);
    }

    @Test
    void should_ReturnNote_when_NoteExists() {
        // given
        when(noteRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(testNote));

        // when
        Note returnedNote = noteService.getNote(UUID.randomUUID());

        // then
        assertEquals(testNote, returnedNote);
        verify(noteRepository).findById(any());
    }

    @Test
    void should_Throw_when_NoteDoesntExist() {
        // given
        when(noteRepository.findById(any())).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            noteService.getNote(UUID.randomUUID());
        });
    }

    @Test
    void should_UpdateNoteText_when_NoteExists() {
        // given
        when(noteRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(testNote));
        String newNoteText = "new text";

        // when
        Note updatedNote = noteService.updateNoteText(UUID.randomUUID(), newNoteText);

        // then
        assertEquals(newNoteText, updatedNote.getText());
    }

    @Test
    void should_Throw_when_NoteToUpdateDoesntExist() {
        // given
        when(noteRepository.findById(any())).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            noteService.updateNoteText(UUID.randomUUID(), "new text");
        });
    }

    @Test
    void should_DeleteNote() {
        // given
        UUID id = UUID.randomUUID();

        // when
        noteService.deleteNote(id);

        // then
        verify(noteRepository).deleteById(id);
    }
}