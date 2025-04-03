package com.orders.demo.controller;

import com.orders.demo.dto.OrderRequestDTO;
import com.orders.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/simulate-lock")
    public void simulateLock(@RequestParam String product, @RequestParam int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            executor.submit(() -> {
                OrderRequestDTO dto = new OrderRequestDTO("user-" + finalI, product, 1);
                orderService.createOrder(dto);
            });
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        boolean isCreated = orderService.createOrder(orderRequestDTO);
        if (!isCreated) {
            return ResponseEntity.badRequest().body("order not created");
        }
        return ResponseEntity.ok("order created");
    }

    @GetMapping("/pod-info")
    public ResponseEntity<Map<String, String>> getPodInfo() {
        Map<String, String> podInfo = new HashMap<>();
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            
            podInfo.put("hostName", hostName);
            podInfo.put("hostAddress", hostAddress);
            podInfo.put("timestamp", String.valueOf(System.currentTimeMillis()));
            
            System.out.println("Request handled by pod: " + hostName + " (" + hostAddress + ")");
        } catch (UnknownHostException e) {
            podInfo.put("error", "Unable to determine host information");
        }
        return ResponseEntity.ok(podInfo);
    }
}
