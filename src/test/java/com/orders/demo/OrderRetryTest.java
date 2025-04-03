package com.orders.demo;

import com.orders.demo.dto.OrderRequestDTO;
import com.orders.demo.model.Inventory;
import com.orders.demo.model.Order;
import com.orders.demo.repository.InventoryRepository;
import com.orders.demo.repository.OrderRepository;
import com.orders.demo.service.DefaultOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderRetryTest {

    @Mock
    private InventoryRepository inventoryRepository;
    
    @Mock
    private OrderRepository orderRepository;
    
    private DefaultOrderService orderService;
    
    @BeforeEach
    void setUp() {
        orderService = new DefaultOrderService(orderRepository, inventoryRepository);
    }
    
    @Test
    public void testCreateOrderSuccess() {
        // Arrange
        Inventory inventory = new Inventory("product-1", 10);
        OrderRequestDTO request = new OrderRequestDTO("user1", "product-1", 2);
        
        when(inventoryRepository.findByIdForUpdate("product-1")).thenReturn(Optional.of(inventory));
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        
        // Act
        boolean result = orderService.createOrder(request);
        
        // Assert
        assertTrue(result);
        verify(orderRepository).save(any(Order.class));
        verify(inventoryRepository).save(any(Inventory.class));
        assertEquals(8, inventory.getQuantity());
    }
    
    @Test
    public void testCreateOrderInsufficientInventory() {
        // Arrange
        Inventory inventory = new Inventory("product-1", 1);
        OrderRequestDTO request = new OrderRequestDTO("user1", "product-1", 2);
        
        when(inventoryRepository.findByIdForUpdate("product-1")).thenReturn(Optional.of(inventory));
        
        // Act
        boolean result = orderService.createOrder(request);
        
        // Assert
        assertFalse(result);
        verify(orderRepository, never()).save(any(Order.class));
        verify(inventoryRepository, never()).save(any(Inventory.class));
    }
    
    @Test
    public void testCreateOrderWithProductNotFound() {
        // Arrange
        OrderRequestDTO request = new OrderRequestDTO("user1", "non-existent-product", 1);
        
        when(inventoryRepository.findByIdForUpdate("non-existent-product")).thenReturn(Optional.empty());
        
        // Act
        boolean result = orderService.createOrder(request);
        
        // Assert
        assertFalse(result);
        verify(orderRepository, never()).save(any(Order.class));
    }
}
