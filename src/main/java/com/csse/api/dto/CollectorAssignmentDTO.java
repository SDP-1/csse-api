package com.csse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectorAssignmentDTO {
    private long id;
    private long collectionScheduleId; // Assuming CollectionSchedule ID for mapping
    private long collectorId; // Assuming GarbageCollector ID for mapping
    private Date assignedDate;
}
