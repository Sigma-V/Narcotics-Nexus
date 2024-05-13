package org.project.narcoticsnexus.exception;

public class InsufficientFundException extends Throwable {
    @Override
    public String getMessage() {
        return "Not enough fund!";
    }
}
