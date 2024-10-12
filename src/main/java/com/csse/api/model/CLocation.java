package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId; // Unique identifier for each location

    private String address; // Address of the location
    private String description; // Description of the location
}

