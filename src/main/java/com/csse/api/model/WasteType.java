package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WasteType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private float collectorPricePerKg;
    private float paybackPricePerKg;
    private float sellingPricePerKg;

    @OneToMany(mappedBy = "wasteType")
    private List<Bin> bins;

    @ManyToMany(mappedBy = "wasteTypes")
    private List<Business> businesses;
}
