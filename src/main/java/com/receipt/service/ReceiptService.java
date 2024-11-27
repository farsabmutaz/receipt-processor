package com.receipt.service;

import com.receipt.exception.InvalidIdException;
import com.receipt.exception.InvalidReciptException;
import com.receipt.model.Receipt;
import com.receipt.storage.ReceiptStorage;
import com.receipt.util.PointCalculator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptService {
    public String processReceipt(Receipt receipt) {

        validateReceipt(receipt);
        String id = UUID.randomUUID().toString();
        int points = PointCalculator.calculatePoints(receipt);
        ReceiptStorage.storeReceipt(id, points);
        return id;
    }

    public Integer getPoints(String id) {
        if(id == null || id.isEmpty()) {
            throw new InvalidIdException("Invalid receipt id "+id);
        }
        return ReceiptStorage.getPoints(id);
    }
    private void validateReceipt(Receipt receipt) {
        if(receipt == null) {
            throw new InvalidReciptException("The receipt is invalid");
        }
        if (receipt.getRetailer() == null || receipt.getRetailer().trim().isEmpty()) {
        throw new InvalidReciptException("The receipt is invalid");
    }

        if (receipt.getPurchaseDate() == null || !receipt.getPurchaseDate().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidReciptException("The receipt is invalid");
        }
    }
}
