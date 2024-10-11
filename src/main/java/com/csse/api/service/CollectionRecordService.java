package com.csse.api.service;

import com.csse.api.dto.CollectionRecordDTO;
import com.csse.api.model.CollectionRecord;
import com.csse.api.repository.CollectionRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionRecordService {

    @Autowired
    private CollectionRecordRepository collectionRecordRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectionRecordDTO createCollectionRecord(CollectionRecordDTO collectionRecordDTO) {
        CollectionRecord collectionRecord = modelMapper.map(collectionRecordDTO, CollectionRecord.class);
        collectionRecord = collectionRecordRepository.save(collectionRecord);
        return modelMapper.map(collectionRecord, CollectionRecordDTO.class);
    }

    public List<CollectionRecordDTO> getAllCollectionRecords() {
        List<CollectionRecord> records = collectionRecordRepository.findAll();
        return records.stream()
                .map(record -> modelMapper.map(record, CollectionRecordDTO.class))
                .toList();
    }

    public Optional<CollectionRecordDTO> getCollectionRecordById(long id) {
        return collectionRecordRepository.findById(id)
                .map(record -> modelMapper.map(record, CollectionRecordDTO.class));
    }

    public CollectionRecordDTO updateCollectionRecord(long id, CollectionRecordDTO collectionRecordDTO) {
        CollectionRecord collectionRecord = collectionRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collection Record not found"));
        modelMapper.map(collectionRecordDTO, collectionRecord);
        collectionRecord = collectionRecordRepository.save(collectionRecord);
        return modelMapper.map(collectionRecord, CollectionRecordDTO.class);
    }

    public void deleteCollectionRecord(long id) {
        collectionRecordRepository.deleteById(id);
    }
}
