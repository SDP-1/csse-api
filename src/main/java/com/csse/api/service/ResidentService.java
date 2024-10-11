package com.csse.api.service;

import com.csse.api.dto.ResidentDTO;
import com.csse.api.model.Resident;
import com.csse.api.repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResidentDTO createResident(ResidentDTO residentDTO) {
        Resident resident = modelMapper.map(residentDTO, Resident.class);
        resident = residentRepository.save(resident);
        return modelMapper.map(resident, ResidentDTO.class);
    }

    public List<ResidentDTO> getAllResidents() {
        List<Resident> residents = residentRepository.findAll();
        return residents.stream()
                .map(resident -> modelMapper.map(resident, ResidentDTO.class))
                .toList();
    }

    public Optional<ResidentDTO> getResidentById(long id) {
        return residentRepository.findById(id)
                .map(resident -> modelMapper.map(resident, ResidentDTO.class));
    }

    public ResidentDTO updateResident(long id, ResidentDTO residentDTO) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
        modelMapper.map(residentDTO, resident);
        resident = residentRepository.save(resident);
        return modelMapper.map(resident, ResidentDTO.class);
    }

    public void deleteResident(long id) {
        residentRepository.deleteById(id);
    }
}
