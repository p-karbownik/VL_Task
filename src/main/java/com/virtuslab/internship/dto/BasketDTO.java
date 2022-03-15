package com.virtuslab.internship.dto;

import com.virtuslab.internship.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class BasketDTO {
    private List<Product> products;
}
