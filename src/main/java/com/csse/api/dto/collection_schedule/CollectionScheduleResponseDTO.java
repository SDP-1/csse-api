package com.csse.api.dto.collection_schedule;

import com.csse.api.enums.FrequencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionScheduleResponseDTO {
    private long id; // Primary key
    private Date startDate;
    private Date endDate;
    private FrequencyType frequency;
    private long routeId; // For mapping to the Route entity
}
