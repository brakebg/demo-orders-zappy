package com.orders.demo.service;

import com.orders.demo.dto.OrderRequestDTO;
import com.orders.demo.model.Inventory;
import com.orders.demo.model.Order;
import com.orders.demo.repository.InventoryRepository;
import com.orders.demo.repository.OrderRepository;
import com.orders.demo.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Default implementation of the OrderService interface.
 * Provides basic order processing functionality.
 */
@Slf4j
@Service
public class DefaultOrderService implements OrderService {


    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    public DefaultOrderService(OrderRepository orderRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Creates a new order based on the provided order request.
     *
     * @param orderRequestDTO the order request data
     * @return true if the order was created successfully, false otherwise
     */

    @Retryable(
            include = {
                    PessimisticLockingFailureException.class,
                    CannotAcquireLockException.class
            },
            maxAttempts = 3,
            backoff = @Backoff(delay = 200),
            recover = "recover"
    )
    @Transactional
    @Override
    public boolean createOrder(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return false;
        }


        Order order = MapperUtil.mapToOrder(orderRequestDTO);

        // inventory lock for update
        Optional<Inventory> productInventory = inventoryRepository.findByIdForUpdate(order.getProduct());

        if (productInventory.isPresent() && hasValidInventory(productInventory.get(), order.getQuantity())) {
            orderRepository.save(order);
            updateInventory(productInventory.get(), productInventory.get().getQuantity() - order.getQuantity());
            System.out.println("Created order: " + order.getUser() + ", " + order.getProduct() + ", " + order.getQuantity());
        } else {
            System.out.println("NOT Created order: " + (productInventory.isPresent() ? productInventory.get().getQuantity() : "0") + ", " + order.getUser() + ", " + order.getProduct() + ", " + order.getQuantity());
            return false;
        }

        return true;

    }

    @Recover
    public boolean recover(PessimisticLockingFailureException ex, OrderRequestDTO dto) {
        log.error("Retry failed for product {} due to {}", dto.getProduct(), ex.getMessage());
        return false;
    }

    private void updateInventory(Inventory inventory, int quantity) {
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    private boolean hasValidInventory(Inventory inventory, int quantity) {
        return inventory.getQuantity() >= quantity;
    }
}
