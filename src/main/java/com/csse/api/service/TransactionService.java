package com.csse.api.service;

import com.csse.api.dto.transaction.TransactionRequestDTO;
import com.csse.api.dto.transaction.TransactionResponseDTO;
import com.csse.api.model.Resident;
import com.csse.api.model.Transaction;
import com.csse.api.model.WMA;
import com.csse.api.repository.ResidentRepository;
import com.csse.api.repository.TransactionRepository;
import com.csse.api.repository.WMARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private WMARepository wmaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TransactionResponseDTO create(TransactionRequestDTO dto) {
        Transaction transaction = modelMapper.map(dto, Transaction.class);

        // Find and set Resident and WMA entities
        Resident resident = residentRepository.findById(dto.getResidentId()).orElseThrow();
        WMA wma = wmaRepository.findById(String.valueOf(dto.getAuthorityId())).orElseThrow();
        transaction.setResident(resident);
        transaction.setWma(wma);

        return modelMapper.map(transactionRepository.save(transaction), TransactionResponseDTO.class);
    }

    public List<TransactionResponseDTO> getAll() {
        return transactionRepository.findAll().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TransactionResponseDTO getById(long id) {
        return transactionRepository.findById(id)
                .map(transaction -> modelMapper.map(transaction, TransactionResponseDTO.class))
                .orElse(null);
    }

    public TransactionResponseDTO update(long id, TransactionRequestDTO dto) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();

        // Update the transaction with new data
        modelMapper.map(dto, transaction);

        // Update the related Resident and WMA
        Resident resident = residentRepository.findById(dto.getResidentId()).orElseThrow();
        WMA wma = wmaRepository.findById(String.valueOf(dto.getAuthorityId())).orElseThrow();
        transaction.setResident(resident);
        transaction.setWma(wma);

        return modelMapper.map(transactionRepository.save(transaction), TransactionResponseDTO.class);
    }

    public void delete(long id) {
        transactionRepository.deleteById(id);
    }
}
