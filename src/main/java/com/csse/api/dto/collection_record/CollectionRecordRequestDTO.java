package com.csse.api.dto.collection_record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRecordRequestDTO {
    private long binId;
    private long collectionScheduleId;
    private LocalDateTime collectionDateTime;
    private float collectedWasteAmount;
    private String audioProof;
    private String videoProof;
}
