package com.hil4rion.lifeplanner.dto;

import java.time.LocalDate;

public record TaskDto(
        String name,
        LocalDate deadline,
        boolean completed
) {
}
