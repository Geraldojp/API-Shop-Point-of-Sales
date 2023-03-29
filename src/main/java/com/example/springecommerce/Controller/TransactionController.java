package com.example.springecommerce.Controller;

import com.example.springecommerce.Model.Request.TransactionRequest;
import com.example.springecommerce.Model.Request.TransactionResponse;
import com.example.springecommerce.Model.Response.SuccessResponse;
import com.example.springecommerce.Service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;
    @PostMapping
    public ResponseEntity save(@RequestBody TransactionRequest transaction) {
        transactionService.save(transaction);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Saved", transaction));
    }
    @GetMapping
    public ResponseEntity findAll() {
        Iterable<TransactionResponse> find = transactionService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success", find));
    }
    @GetMapping("/daily")
    public ResponseEntity getDailyReport(@RequestParam LocalDate date) {
        List<TransactionResponse> find = transactionService.getDailyReport(date);
        String total = "Total Daily Transaction is " + find.get(0).getDailyTotal();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>(total, find));
    }
    @GetMapping("/monthly")
    public ResponseEntity getMonthlyReport(@RequestParam int month,@RequestParam int year) {
        List<TransactionResponse> find = transactionService.getMonthlyReport(month, year);
        String total = "Total Monthly Transaction is " + find.get(0).getMonthlyTotal();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>(total, find));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>("Deleted", id + " has been deleted"));
    }
}
