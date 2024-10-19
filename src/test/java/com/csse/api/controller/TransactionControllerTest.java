package com.csse.api.controller;

import com.csse.api.dto.transaction.TransactionRequestDTO;
import com.csse.api.dto.transaction.TransactionResponseDTO;
import com.csse.api.exception.TransactionNotFoundException;
import com.csse.api.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionById_TransactionNotFound() {
        long transactionId = 1L;
        when(transactionService.getById(transactionId)).thenThrow(new TransactionNotFoundException("Transaction not found with ID: " + transactionId));

        ResponseEntity<String> response = transactionController.handleTransactionNotFound(new TransactionNotFoundException("Transaction not found with ID: " + transactionId));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Transaction not found with ID: " + transactionId, response.getBody());
    }

    @Test
    void createTransaction() {
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        when(transactionService.create(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TransactionResponseDTO> response = transactionController.createTransaction(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void updateTransaction() {
        long transactionId = 1L;
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        when(transactionService.update(transactionId, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TransactionResponseDTO> response = transactionController.updateTransaction(transactionId, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void deleteTransaction() {
        long transactionId = 1L;
        doNothing().when(transactionService).delete(transactionId);

        ResponseEntity<Void> response = transactionController.deleteTransaction(transactionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllTransactions() {
        List<TransactionResponseDTO> transactions = Collections.emptyList();
        when(transactionService.getAll()).thenReturn(transactions);

        ResponseEntity<List<TransactionResponseDTO>> response = transactionController.getAllTransactions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }
}
