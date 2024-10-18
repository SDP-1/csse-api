package com.csse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRecordDTO {
    private long id;
    private long binId; // Assuming Bin ID for mapping
    private long collectionScheduleId; // Assuming CollectionSchedule ID for mapping
    private LocalDateTime collectionDateTime;
    private float collectedWasteAmount;
    private String audioProof;
    private String videoProof;
}
