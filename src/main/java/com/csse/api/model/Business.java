package com.csse.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Business extends Resident{
    private String businessType;
    private String businessRegistration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "business_waste_types",
            joinColumns = @JoinColumn(name = "business_id"),
            inverseJoinColumns = @JoinColumn(name = "waste_type_id")
    )
    private List<WasteType> wasteTypes;
}
