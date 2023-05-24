package org.example.exemptions;

public class WrongIndexExemption extends RuntimeException{
    public WrongIndexExemption(String message) {
        super(message);
    }
}
