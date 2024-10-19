package com.csse.api.service;

import com.csse.api.dto.transaction.TransactionRequestDTO;
import com.csse.api.dto.transaction.TransactionResponseDTO;
import com.csse.api.exception.TransactionNotFoundException;
import com.csse.api.model.Transaction;
import com.csse.api.repository.TransactionRepository;
import com.csse.api.repository.ResidentRepository;
import com.csse.api.repository.WMARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ResidentRepository residentRepository;

    @Mock
    private WMARepository wmaRepository;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction();
        transaction.setTransactionId(1L);
    }

    @Test
    void getById_TransactionNotFound() {
        long transactionId = 1L;
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.getById(transactionId);
        });

        assertEquals("Transaction not found with ID: " + transactionId, exception.getMessage());
    }

    @Test
    void update_TransactionNotFound() {
        long transactionId = 1L;
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.update(transactionId, requestDTO);
        });

        assertEquals("Transaction not found with ID: " + transactionId, exception.getMessage());
    }

    @Test
    void delete_TransactionNotFound() {
        long transactionId = 1L;
        when(transactionRepository.existsById(transactionId)).thenReturn(false);

        Exception exception = assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.delete(transactionId);
        });

        assertEquals("Transaction not found with ID: " + transactionId, exception.getMessage());
    }
}
