package com.csse.api.service;

import com.csse.api.dto.BusinessDTO;
import com.csse.api.model.Business;
import com.csse.api.repository.BusinessRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BusinessDTO createBusiness(BusinessDTO businessDTO) {
        Business business = modelMapper.map(businessDTO, Business.class);
        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDTO.class);
    }

    public List<BusinessDTO> getAllBusinesses() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(business -> modelMapper.map(business, BusinessDTO.class))
                .toList();
    }

    public Optional<BusinessDTO> getBusinessById(long id) {
        return businessRepository.findById(id)
                .map(business -> modelMapper.map(business, BusinessDTO.class));
    }

    public BusinessDTO updateBusiness(long id, BusinessDTO businessDTO) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        modelMapper.map(businessDTO, business);
        business = businessRepository.save(business);
        return modelMapper.map(business, BusinessDTO.class);
    }

    public void deleteBusiness(long id) {
        businessRepository.deleteById(id);
    }
}
