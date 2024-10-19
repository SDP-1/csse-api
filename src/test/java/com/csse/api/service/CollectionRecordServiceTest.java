package com.csse.api.service;

import com.csse.api.dto.collection_record.CollectionRecordRequestDTO;
import com.csse.api.dto.collection_record.CollectionRecordResponseDTO;
import com.csse.api.exception.CollectionRecordNotFoundException;
import com.csse.api.model.CollectionRecord;
import com.csse.api.model.Bin;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.repository.CollectionRecordRepository;
import com.csse.api.repository.BinRepository;
import com.csse.api.repository.CollectionScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class CollectionRecordServiceTest {

    @InjectMocks
    private CollectionRecordService service;

    @Mock
    private CollectionRecordRepository repository;

    @Mock
    private BinRepository binRepository;

    @Mock
    private CollectionScheduleRepository collectionScheduleRepository;

    private CollectionRecord collectionRecord;
    private Bin bin;
    private CollectionSchedule collectionSchedule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bin = new Bin();
        bin.setId(1L);

        collectionSchedule = new CollectionSchedule();
        collectionSchedule.setId(1L);

        collectionRecord = new CollectionRecord();
        collectionRecord.setId(1L);
        collectionRecord.setBin(bin);
        collectionRecord.setCollectionSchedule(collectionSchedule);
        collectionRecord.setCollectionDateTime(LocalDateTime.now());
        collectionRecord.setCollectedWasteAmount(10);
    }

    @Test
    public void create_ShouldReturnCollectionRecordResponseDTO() {
        CollectionRecordRequestDTO requestDTO = new CollectionRecordRequestDTO(
                1L, 1L, LocalDateTime.now(), 10, "audio.mp3", "video.mp4");

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(collectionScheduleRepository.findById(1L)).thenReturn(Optional.of(collectionSchedule));
        when(repository.save(any(CollectionRecord.class))).thenReturn(collectionRecord);

        CollectionRecordResponseDTO responseDTO = service.create(requestDTO);

        assertThat(responseDTO.getId()).isEqualTo(collectionRecord.getId());
        assertThat(responseDTO.getBinId()).isEqualTo(bin.getId());
    }

    @Test
    public void getById_ShouldReturnCollectionRecordResponseDTO_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(collectionRecord));

        CollectionRecordResponseDTO responseDTO = service.getById(1L);

        assertThat(responseDTO.getId()).isEqualTo(collectionRecord.getId());
    }

    @Test
    public void getById_ShouldThrowException_WhenNotExists() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(1L))
                .isInstanceOf(CollectionRecordNotFoundException.class)
                .hasMessage("CollectionRecord not found with id: 1");
    }

    @Test
    public void update_ShouldReturnUpdatedCollectionRecordResponseDTO() {
        CollectionRecordRequestDTO requestDTO = new CollectionRecordRequestDTO(
                1L, 1L, LocalDateTime.now(), 20, "audio2.mp3", "video2.mp4");

        when(repository.findById(1L)).thenReturn(Optional.of(collectionRecord));
        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(collectionScheduleRepository.findById(1L)).thenReturn(Optional.of(collectionSchedule));
        when(repository.save(any(CollectionRecord.class))).thenReturn(collectionRecord);

        CollectionRecordResponseDTO responseDTO = service.update(1L, requestDTO);

        assertThat(responseDTO.getCollectedWasteAmount()).isEqualTo(20);
    }

    @Test
    public void delete_ShouldCallDeleteMethod() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
