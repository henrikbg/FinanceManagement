//package FinanceManagement;

/*
    Investment.java
    Abstract class for an investment in the application
*/

public class Investment {
    
    // Declaring variables
    private int id;
    private String name;
    private String category;
    private double amount;
    
    // Constructor with no parameters
    public Investment() {
        this.id = 0;
        this.name = "";
        this.category = "";
        this.amount = 0;
    }
    
    // Constructor with parameters to construct the variables
    public Investment(int id, String name, String category, double amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    // Lots of set and get methods
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if(amount > 0) {
            this.amount = amount;
        } else {
            this.amount = 0;
        }
    }
    
    // This returns when the object is called
    @Override
    public String toString() {
        return "Investment" +
               "\n---" +
               "\nID: " + getId() + 
               "\nName: " + getName() + 
               "\nCategory: " + getCategory() + 
               "\nAmount: " + getAmount();
    }
    
}
