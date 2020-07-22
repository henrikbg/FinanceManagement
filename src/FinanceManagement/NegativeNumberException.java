//package FinanceManagement;

/*
    NegativeNumberException.java
    Exception for negative numbers
*/

public class NegativeNumberException extends Exception {
    
    // Constructor calling to the exception class
    public NegativeNumberException() {
        super();
    }
    
    // Constructor calling to the exception class with a message
    public NegativeNumberException(String message) {
        super(message);
    }
    
    // This returns when the object of this class is called
    @Override
    public String toString() {
        return "Number must be larger than zero!";
    }
    
}
