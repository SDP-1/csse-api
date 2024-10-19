package com.csse.api.repository;

import com.csse.api.model.Bin;
import com.csse.api.model.CollectionRecord;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.BinTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CollectionRecordRepositoryTest {

    @Autowired
    private CollectionRecordRepository collectionRecordRepository;

    @Autowired
    private BinRepository binRepository;

    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    private Bin bin;
    private CollectionSchedule collectionSchedule;
    private CollectionRecord collectionRecord;

    @BeforeEach
    public void setUp() {
        bin = new Bin();
        bin.setBinType(new BinTypes());
        bin = binRepository.save(bin);


        collectionSchedule = new CollectionSchedule();
        collectionSchedule = collectionScheduleRepository.save(collectionSchedule);

        // Create and save CollectionRecord
        collectionRecord = new CollectionRecord();
        collectionRecord.setBin(bin);
        collectionRecord.setCollectionSchedule(collectionSchedule);
        collectionRecord.setCollectionDateTime(LocalDateTime.now());
        collectionRecord.setCollectedWasteAmount(10);

        collectionRecord = collectionRecordRepository.save(collectionRecord);
    }

    @Test
    public void findById_ShouldReturnCollectionRecord_WhenExists() {
        Optional<CollectionRecord> foundRecord = collectionRecordRepository.findById(collectionRecord.getId());

        assertThat(foundRecord).isPresent();
        assertThat(foundRecord.get().getId()).isEqualTo(collectionRecord.getId());
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenNotExists() {
        Optional<CollectionRecord> foundRecord = collectionRecordRepository.findById(999L);

        assertThat(foundRecord).isNotPresent();
    }

    @Test
    public void save_ShouldPersistCollectionRecord() {
        CollectionRecord newRecord = new CollectionRecord();
        newRecord.setBin(bin);
        newRecord.setCollectionSchedule(collectionSchedule);
        newRecord.setCollectionDateTime(LocalDateTime.now());
        newRecord.setCollectedWasteAmount(15);

        CollectionRecord savedRecord = collectionRecordRepository.save(newRecord);

        assertThat(savedRecord.getId()).isNotNull();
        assertThat(savedRecord.getCollectedWasteAmount()).isEqualTo(15);
    }
}
