package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;

import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.AikhomuLuckyOkoedion.OnlineBookStore.util.ReceiptGenerator;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final OrderRepository orderRepository;
    private final ReceiptGenerator receiptGenerator;

    public ReceiptController(OrderRepository orderRepository, ReceiptGenerator receiptGenerator) {
        this.orderRepository = orderRepository;
        this.receiptGenerator = receiptGenerator;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable UUID orderId) throws IOException {
        var order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        byte[] pdf = receiptGenerator.generateReceipt(order.getUserId(), order.getItems());

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf")
            .body(pdf);
    }
}

