package com.csse.api.dto.collector_assignment;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollectorAssignmentRequestDTO {
    private long collectionScheduleId;
    private long collectorId;
    private Date assignedDate;
}
