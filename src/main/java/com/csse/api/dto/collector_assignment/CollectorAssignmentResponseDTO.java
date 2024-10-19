package com.csse.api.dto.collector_assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectorAssignmentResponseDTO {
    private long id;
    private long collectionScheduleId;
    private long collectorId;
    private Date assignedDate;
}
