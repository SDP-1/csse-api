package com.csse.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CollectorAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private CollectionSchedule collectionSchedule; // The collection schedule for this assignment

    @ManyToOne
    @JoinColumn(name = "collector_id", nullable = false)
    private GarbageCollector collector; // The garbage collector assigned to this schedule

    private Date assignedDate; // The specific date of the assignment
}
