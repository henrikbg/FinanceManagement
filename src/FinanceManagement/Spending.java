//package FinanceManagement;

/*
    Spending.java
    Abstract class for spending to be used in the application
*/

public class Spending {
    
    // Declaring variables
    private int id;
    private String name;
    private String category;
    private double amount;
    
    // Two constructors, overloading with different parameters
    public Spending() {
        this.id = 0;
        this.name = "";
        this.category = "";
        this.amount = 0;
    }
    
    public Spending(int id, String name, String category, double amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    // Bunch of set and get methods
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
    
    // Returns when the object of this class is called
    @Override
    public String toString() {
        return "Spending" +
               "\n---" +
               "\nID: " + getId() + 
               "\nName: " + getName() + 
               "\nCategory: " + getCategory() + 
               "\nAmount: " + getAmount();
    }
    
}
