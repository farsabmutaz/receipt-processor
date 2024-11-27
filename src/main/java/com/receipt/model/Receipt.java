package com.receipt.model;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private String total;
    private List<Item> items;
    @Getter
    @Setter
    public static class Item {
        private String shortDescription;
        private String price;

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
