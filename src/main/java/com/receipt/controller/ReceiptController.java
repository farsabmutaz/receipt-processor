package com.receipt.controller;

import com.receipt.exception.IdNotFoundException;
import com.receipt.model.Receipt;
import com.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public ResponseEntity<?> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok().body("{\"id\": \"" + id + "\"}");
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        Integer points = receiptService.getPoints(id);
        if (points == null) {
            throw new IdNotFoundException("No receipt found for that id "+ id);
        }
        return ResponseEntity.ok().body("{\"Total points\": " + points + "}");
    }
}
