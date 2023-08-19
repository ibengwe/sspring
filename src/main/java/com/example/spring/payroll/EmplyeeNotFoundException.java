package com.example.spring.payroll;

public class EmplyeeNotFoundException extends RuntimeException {
    public EmplyeeNotFoundException(Long Id) {
        super("Could not find Employee with this id "+Id);
    }
}
