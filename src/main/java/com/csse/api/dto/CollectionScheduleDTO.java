package com.csse.api.dto;

import com.csse.api.enums.FrequencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionScheduleDTO {
    private long id;
    private Date startDate;
    private Date endDate;
    private FrequencyType frequency;
    private long routeId; // Assuming Route ID for mapping
    private List<Long> assignmentIds; // Assuming List of CollectorAssignment IDs for mapping
}
