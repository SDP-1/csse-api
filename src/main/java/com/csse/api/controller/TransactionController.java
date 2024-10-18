package com.csse.api.controller;

import com.csse.api.dto.transaction.TransactionRequestDTO;
import com.csse.api.dto.transaction.TransactionResponseDTO;
import com.csse.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.create(transactionRequestDTO);
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable long id) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getById(id);
        if (transactionResponseDTO != null) {
            return ResponseEntity.ok(transactionResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable long id, @RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.update(id, transactionRequestDTO);
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

