package com.orders.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.ToString;

@Entity
@ToString
public class Inventory {

    @Id
    private String productId;
    private int quantity;

    @Version
    private Long version;
    
    // Default constructor required by JPA
    public Inventory() {
    }
    
    // Constructor for manual mapping
    public Inventory(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    
    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Long getVersion() {
        return version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
}
