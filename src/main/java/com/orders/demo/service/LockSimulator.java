package com.orders.demo.service;

import com.orders.demo.model.Inventory;
import com.orders.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LockSimulator {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void lockInventoryAndHold(String productId, long holdMillis) {
        Inventory inv = inventoryRepository.findByIdForUpdate(productId).orElseThrow();
        try {
            Thread.sleep(holdMillis); // hold lock
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
