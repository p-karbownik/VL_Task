package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

import static com.virtuslab.internship.product.Product.Type.GRAINS;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt){
        if(shouldApply(receipt))
        {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt)
    {
        return receipt.entries().stream().filter( (p) -> p.product().type() == GRAINS).mapToInt(ReceiptEntry::quantity).sum() >= 3;
    }
}
