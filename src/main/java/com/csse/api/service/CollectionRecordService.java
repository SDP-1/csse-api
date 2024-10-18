package com.csse.api.service;

import com.csse.api.dto.collection_record.CollectionRecordRequestDTO;
import com.csse.api.dto.collection_record.CollectionRecordResponseDTO;
import com.csse.api.model.CollectionRecord;
import com.csse.api.repository.CollectionRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionRecordService {

    @Autowired
    private CollectionRecordRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectionRecordResponseDTO create(CollectionRecordRequestDTO dto) {
        CollectionRecord entity = modelMapper.map(dto, CollectionRecord.class);
        return modelMapper.map(repository.save(entity), CollectionRecordResponseDTO.class);
    }

    public List<CollectionRecordResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CollectionRecordResponseDTO.class))
                .toList();
    }

    public CollectionRecordResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, CollectionRecordResponseDTO.class))
                .orElse(null);
    }

    public CollectionRecordResponseDTO update(long id, CollectionRecordRequestDTO dto) {
        CollectionRecord entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), CollectionRecordResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
