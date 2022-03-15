package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.dto.BasketDTO;
import com.virtuslab.internship.dto.ReceiptDTO;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.stereotype.Service;


@Service
public class BasketService {

    public ReceiptDTO getReceipt(BasketDTO basketDTO)
    {
        Basket basket = new Basket();

        for(Product product : basketDTO.getProducts())
        {
            basket.addProduct(product);
        }

        Receipt receipt = ReceiptGenerator.generate(basket);

        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();

        receipt = tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setDiscounts(receipt.discounts());
        receiptDTO.setTotalPrice(receipt.totalPrice());
        receiptDTO.setEntries(receipt.entries());

        return receiptDTO;
    }
}
