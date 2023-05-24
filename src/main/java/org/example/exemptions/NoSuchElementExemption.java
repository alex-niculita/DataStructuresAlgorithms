package org.example.exemptions;

public class NoSuchElementExemption extends RuntimeException{
    public NoSuchElementExemption(String message) {
        super(message);
    }
}
