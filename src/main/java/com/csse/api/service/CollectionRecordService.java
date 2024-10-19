package com.csse.api.service;

import com.csse.api.dto.collection_record.CollectionRecordRequestDTO;
import com.csse.api.dto.collection_record.CollectionRecordResponseDTO;
import com.csse.api.exception.CollectionRecordNotFoundException;
import com.csse.api.model.CollectionRecord;
import com.csse.api.model.Bin;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.repository.CollectionRecordRepository;
import com.csse.api.repository.BinRepository; // Assuming you have this repository
import com.csse.api.repository.CollectionScheduleRepository; // Assuming you have this repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionRecordService {

    private final CollectionRecordRepository repository;

    private final BinRepository binRepository;

    private final CollectionScheduleRepository collectionScheduleRepository;

    public CollectionRecordService(CollectionRecordRepository repository, BinRepository binRepository, CollectionScheduleRepository collectionScheduleRepository) {
        this.repository = repository;
        this.binRepository = binRepository;
        this.collectionScheduleRepository = collectionScheduleRepository;
    }

    public CollectionRecordResponseDTO create(CollectionRecordRequestDTO dto) {
        CollectionRecord entity = new CollectionRecord();

        // Set values manually instead of using ModelMapper
        return getCollectionRecordResponseDTO(dto, entity);
    }

    public List<CollectionRecordResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public CollectionRecordResponseDTO getById(long id) {
        return repository.findById(id)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new CollectionRecordNotFoundException("CollectionRecord not found with id: " + id));
    }

    public CollectionRecordResponseDTO update(long id, CollectionRecordRequestDTO dto) {
        CollectionRecord entity = repository.findById(id)
                .orElseThrow(() -> new CollectionRecordNotFoundException("CollectionRecord not found with id: " + id));

        return getCollectionRecordResponseDTO(dto, entity);
    }

    private CollectionRecordResponseDTO getCollectionRecordResponseDTO(CollectionRecordRequestDTO dto, CollectionRecord entity) {
        Bin bin = binRepository.findById(dto.getBinId())
                .orElseThrow(() -> new CollectionRecordNotFoundException("Bin not found with id: " + dto.getBinId()));
        CollectionSchedule collectionSchedule = collectionScheduleRepository.findById(dto.getCollectionScheduleId())
                .orElseThrow(() -> new CollectionRecordNotFoundException("Collection Schedule not found with id: " + dto.getCollectionScheduleId()));

        entity.setBin(bin);
        entity.setCollectionSchedule(collectionSchedule);
        entity.setCollectionDateTime(dto.getCollectionDateTime());
        entity.setCollectedWasteAmount(dto.getCollectedWasteAmount());
        entity.setAudioProof(dto.getAudioProof());
        entity.setVideoProof(dto.getVideoProof());

        return convertToResponseDTO(repository.save(entity));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    private CollectionRecordResponseDTO convertToResponseDTO(CollectionRecord entity) {
        CollectionRecordResponseDTO responseDTO = new CollectionRecordResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setBinId(entity.getBin().getId());
        responseDTO.setCollectionScheduleId(entity.getCollectionSchedule().getId());
        responseDTO.setCollectionDateTime(entity.getCollectionDateTime());
        responseDTO.setCollectedWasteAmount(entity.getCollectedWasteAmount());
        responseDTO.setAudioProof(entity.getAudioProof());
        responseDTO.setVideoProof(entity.getVideoProof());
        return responseDTO;
    }
}
