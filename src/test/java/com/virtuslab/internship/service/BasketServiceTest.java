package com.virtuslab.internship.service;

import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.dto.BasketDTO;
import com.virtuslab.internship.dto.ReceiptDTO;
import com.virtuslab.internship.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class BasketServiceTest {

    @Mock
    private BasketDTO basketDTO;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldApplyTwoDiscounts()
    {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(12)));
        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(12)));
        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(12)));

        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(45)));
        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(45)));
        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(45)));

        when(basketDTO.getProducts()).thenReturn(products);

        ReceiptDTO receiptDTO = basketService.getReceipt(basketDTO);

        assertNotNull(receiptDTO);
        assertEquals(2, receiptDTO.getDiscounts().size());
        assertEquals(FifteenPercentDiscount.NAME, receiptDTO.getDiscounts().get(0));
        assertEquals(TenPercentDiscount.NAME, receiptDTO.getDiscounts().get(1));
    }

    @Test
    public void shouldApplyOnlyFifteenPercentDiscount()
    {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(2)));
        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(2)));
        products.add(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(2)));

        when(basketDTO.getProducts()).thenReturn(products);

        ReceiptDTO receiptDTO = basketService.getReceipt(basketDTO);

        assertNotNull(receiptDTO);
        assertEquals(1, receiptDTO.getDiscounts().size());
        assertEquals(FifteenPercentDiscount.NAME, receiptDTO.getDiscounts().get(0));
    }

    @Test
    public void shouldApplyOnlyTenPercentDiscount()
    {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(50)));
        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(50)));
        products.add(new Product("Orange", Product.Type.FRUITS, BigDecimal.valueOf(50)));


        when(basketDTO.getProducts()).thenReturn(products);

        ReceiptDTO receiptDTO = basketService.getReceipt(basketDTO);

        assertNotNull(receiptDTO);
        assertEquals(1, receiptDTO.getDiscounts().size());
        assertEquals(TenPercentDiscount.NAME, receiptDTO.getDiscounts().get(0));
    }
}
