package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long routeId;
    private String routeName;
    private String routeDescription;
    private String startLocation;
    private String endLocation;
    private String area;
    private Date lastOptimizedDate;

    @ElementCollection
    private List<Long> collectors;  // Collectors array of type long

    
}
