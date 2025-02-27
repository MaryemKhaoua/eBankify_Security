package com.example.ebankify_security.controller;

import com.example.ebankify_security.dto.TransactionDto;
import com.example.ebankify_security.domain.requests.TransactionRequest;
import com.example.ebankify_security.vm.TransactionVM;
import com.example.ebankify_security.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/save")
    public ResponseEntity<TransactionVM> saveTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = transactionService.saveTransaction(transactionRequest);
        TransactionVM response = TransactionVM.builder()
                .transactionDto(transactionDto)
                .message("transaction saved successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<TransactionVM> getAllTransactions() {
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
        TransactionVM response = TransactionVM.builder()
                .transactions(transactionDtos)
                .message("all transactions found successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionVM> getTransactionById(@PathVariable Long id) {
        TransactionDto transactionDto = transactionService.getTransactionById(id);
        TransactionVM response = TransactionVM.builder()
                .transactionDto(transactionDto)
                .message("transaction found successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionVM> updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequest transactionRequest) {
        TransactionDto transactionDto = transactionService.updateTransaction(id, transactionRequest);
        TransactionVM response = TransactionVM.builder()
                .transactionDto(transactionDto)
                .message("transaction updated successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionVM> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        TransactionVM response = TransactionVM.builder()
                .message("transaction deleted successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/approve/{id}")
    public ResponseEntity<TransactionVM> approveTransaction(@PathVariable Long id) {
        transactionService.acceptTransaction(id);
        TransactionVM response = TransactionVM.builder()
                .message("transaction approved successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/reject/{id}")
    public ResponseEntity<TransactionVM> rejectTransaction(@PathVariable Long id) {
        transactionService.cancelTransaction(id);
        TransactionVM response = TransactionVM.builder()
                .message("transaction rejected successfuly")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/history/{accountId}")
    public ResponseEntity<TransactionVM> getTransactionHistory(@PathVariable Long accountId) {
        List<TransactionDto> transactionDtos = transactionService.getTransactionHistoryByAccountId(accountId);
        TransactionVM response = TransactionVM.builder()
                .transactions(transactionDtos)
                .message("Transaction history retrieved successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
