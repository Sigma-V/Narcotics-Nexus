package org.project.narcoticsnexus.exception;

public class InsufficientStockException extends Throwable {
    @Override
    public String getMessage() {
        return "Not enough stock!";
    }
}
