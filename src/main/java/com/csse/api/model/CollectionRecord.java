package com.csse.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @ManyToOne
    @JoinColumn(name = "collection_schedule_id")
    private CollectionSchedule collectionSchedule;

    private LocalDateTime collectionDateTime;
    private float collectedWasteAmount;
    private String audioProof;
    private String videoProof;

}
