package com.hil4rion.lifeplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExpenseCategory {
    @Id
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;
}
