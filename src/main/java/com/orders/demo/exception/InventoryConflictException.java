package com.orders.demo.exception;

public class InventoryConflictException extends RuntimeException {
    public InventoryConflictException(String s, Object p1) {
        super(String.format(s, p1));
    }
}
