package com.csse.api.dto.collector_assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectorAssignmentResponseDTO {
    private long id; // Assuming this is the primary key
    private long collectionScheduleId; // For mapping to the CollectionSchedule entity
    private long collectorId; // For mapping to the GarbageCollector entity
    private Date assignedDate; // The specific date of the assignment
}
