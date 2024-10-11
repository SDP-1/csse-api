package com.csse.api.model;

import com.csse.api.enums.FrequencyType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class CollectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startDate;
    private Date endDate;
    private FrequencyType frequency;

    @ManyToOne
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;

    @OneToMany(mappedBy = "collectionSchedule", cascade = CascadeType.ALL)
    private List<CollectorAssignment> assignements;
}
