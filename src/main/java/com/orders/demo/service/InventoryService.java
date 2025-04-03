package com.orders.demo.service;

import com.orders.demo.dto.InventoryRequestDTO;

public interface InventoryService {
    boolean updateInventory(InventoryRequestDTO inventoryRequestDTO);
}
