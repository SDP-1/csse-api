package com.csse.api.dto.collection_schedule;

import com.csse.api.enums.FrequencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionScheduleRequestDTO {
    private Date startDate;
    private Date endDate;
    private FrequencyType frequency;
    private long routeId;
}
