package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CollectorAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "collection_schedule_id", nullable = false)
    private CollectionSchedule collectionSchedule;

    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private GarbageCollector collector;

    private Date assignmentDate; // Ensure this field exists

    // Constructor with parameters
    public CollectorAssignment(long id, CollectionSchedule collectionSchedule, GarbageCollector collector, Date assignmentDate) {
        this.id = id;
        this.collectionSchedule = collectionSchedule;
        this.collector = collector;
        this.assignmentDate = assignmentDate;
    }
}
