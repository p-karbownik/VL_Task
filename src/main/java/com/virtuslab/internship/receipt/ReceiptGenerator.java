package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public static Receipt generate(Basket basket) {
        var receipt = getReceipt(basket);
        return receipt;
    }

    private static Receipt getReceipt(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        Map<Product, Long> results = basket.getProducts().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for(Product p : results.keySet())
            receiptEntries.add(new ReceiptEntry(p, results.get(p).intValue()));

        return new Receipt(receiptEntries);
    }
}
