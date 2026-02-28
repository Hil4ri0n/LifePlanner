package com.hil4rion.lifeplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Habit {
    @Id
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserProfile userProfile;
}
