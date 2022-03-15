package com.virtuslab.internship.controller;

import com.virtuslab.internship.dto.BasketDTO;
import com.virtuslab.internship.dto.ReceiptDTO;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
@CrossOrigin(origins = "*")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/accept")
    private ResponseEntity<ReceiptDTO> acceptBasket(@RequestBody BasketDTO basketDTO)
    {
        ReceiptDTO receiptDTO = basketService.getReceipt(basketDTO);

        if(receiptDTO != null)
            return new ResponseEntity<>(receiptDTO, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
