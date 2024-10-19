package com.csse.api.controller;

import com.csse.api.dto.collection_record.CollectionRecordRequestDTO;
import com.csse.api.dto.collection_record.CollectionRecordResponseDTO;
import com.csse.api.service.CollectionRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CollectionRecordControllerTest {

    @InjectMocks
    private CollectionRecordController controller;

    @Mock
    private CollectionRecordService service;

    private CollectionRecordResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        responseDTO = new CollectionRecordResponseDTO(1L, 1L, 1L, null, 10, "audio.mp3", "video.mp4");
    }

    @Test
    public void create_ShouldReturnResponseEntity() {
        CollectionRecordRequestDTO requestDTO = new CollectionRecordRequestDTO(1L, 1L, null, 10, "audio.mp3", "video.mp4");

        when(service.create(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<CollectionRecordResponseDTO> responseEntity = controller.create(requestDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(responseDTO);
    }

    @Test
    public void getAll_ShouldReturnListOfResponseEntity() {
        when(service.getAll()).thenReturn(Collections.singletonList(responseDTO));

        ResponseEntity<List<CollectionRecordResponseDTO>> responseEntity = controller.getAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).containsExactly(responseDTO);
    }

    @Test
    public void getById_ShouldReturnResponseEntity() {
        when(service.getById(1L)).thenReturn(responseDTO);

        ResponseEntity<CollectionRecordResponseDTO> responseEntity = controller.getById(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(responseDTO);
    }

    @Test
    public void update_ShouldReturnResponseEntity() {
        CollectionRecordRequestDTO requestDTO = new CollectionRecordRequestDTO(1L, 1L, null, 20, "audio2.mp3", "video2.mp4");

        when(service.update(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<CollectionRecordResponseDTO> responseEntity = controller.update(1L, requestDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(responseDTO);
    }

    @Test
    public void delete_ShouldReturnNoContent() {
        doNothing().when(service).delete(1L);

        ResponseEntity<Void> responseEntity = controller.delete(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
