package com.virtuslab.internship.dto;

import com.virtuslab.internship.receipt.ReceiptEntry;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptDTO {
    private List<ReceiptEntry> entries;
    private List<String> discounts;
    private BigDecimal totalPrice;
}
