//package FinanceManagement;

/*
    RateRangeException.java
    Exception for negative numbers
*/

public class RateRangeException extends Exception {
    
    // Two constructors to call exception class
    public RateRangeException() {
        super();
    }
    
    public RateRangeException(String message) {
        super(message);
    }
    
    // Returns when the object is called
    @Override
    public String toString() {
        return "Rate can't be larger than 100%!";
    }
    
}
