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
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long routeId;
    private String routeName;
    private String routeDescription;
    private String startLocation;
    private String endLocation;
    private String area;
    private Date lastOptimizedDate;

    @ManyToOne
    @JoinColumn(name="wma", nullable = false)
    private WMA wma;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<CollectionSchedule> collectionSchedules;
}
