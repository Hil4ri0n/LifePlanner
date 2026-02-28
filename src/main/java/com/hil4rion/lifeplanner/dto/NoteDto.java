package com.hil4rion.lifeplanner.dto;

import com.hil4rion.lifeplanner.model.NoteScope;

import java.time.LocalDate;

public record NoteDto(
        NoteScope noteScope,
        LocalDate periodStart,
        String text
) {
}
