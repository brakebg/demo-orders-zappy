package com.orders.demo.util;

import com.orders.demo.dto.InventoryRequestDTO;
import com.orders.demo.dto.OrderRequestDTO;
import com.orders.demo.model.Inventory;
import com.orders.demo.model.Order;

/**
 * Utility class for mapping between DTOs and model objects.
 * Uses direct method calls without reflection for maximum performance.
 */
public class MapperUtil {

    /**
     * Maps an OrderRequestDTO to an Order entity using direct method calls.
     * No reflection is used, making this the fastest possible approach.
     *
     * @param dto the source DTO
     * @return a new Order entity with properties set from the DTO
     */
public static Order mapToOrder(OrderRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        // Use constructor-based mapping (fastest approach)
        return new Order(dto.getUser(), dto.getProduct(), dto.getQuantity());

    }
    
    /**
     * Maps an Order entity to an OrderRequestDTO using direct method calls.
     * No reflection is used, making this the fastest possible approach.
     *
     * @param order the source entity
     * @return a new OrderRequestDTO with properties set from the entity
     */
    public static OrderRequestDTO mapToOrderDTO(Order order) {
        if (order == null) {
            return null;
        }
        
        // Use constructor-based mapping (fastest approach)
        return new OrderRequestDTO(order.getUser(), order.getProduct(), order.getQuantity());
    }
    
    /**
     * Maps an InventoryRequestDTO to an Inventory entity using direct method calls.
     * No reflection is used, making this the fastest possible approach.
     *
     * @param dto the source DTO
     * @return a new Inventory entity with properties set from the DTO
     */
    public static Inventory mapToInventory(InventoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Inventory(dto.getProductId(), dto.getQuantity());
    }
    
    /**
     * Maps an Inventory entity to an InventoryRequestDTO using direct method calls.
     * No reflection is used, making this the fastest possible approach.
     *
     * @param inventory the source entity
     * @return a new InventoryRequestDTO with properties set from the entity
     */
    public static InventoryRequestDTO mapToInventoryDTO(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        
        // Use constructor-based mapping (fastest approach)
        return new InventoryRequestDTO(inventory.getProductId(), inventory.getQuantity());
        

    }

}
