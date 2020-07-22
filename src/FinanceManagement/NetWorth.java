//package FinanceManagement;

/*
    NetWorth.java
    Net worth abstract class for balance, spendings, investments, and status
*/

public class NetWorth {
    
    // Declaring variables
    private double balance;
    private double totalSpendings;
    private double totalInvestments;
    private boolean status;
    
    // Constructor with default values
    public NetWorth() {
        balance = 0;
        totalSpendings = 0;
        totalInvestments = 0;
        setStatus(balance);
        status = false;
    }
    
    // Constructor with values to initialize the variables
    public NetWorth(double balance, double totalSpendings, double totalInvestments) {
        this.balance = balance;
        this.totalSpendings = totalSpendings;
        this.totalInvestments = totalInvestments;
        setStatus(this.balance);
        status = true;
    }

    // Lots of set and get methods
    public double getBalance() {
        return balance;
    }

    private void setNetWorth(double totalSpendings, double totalInvestments) {
        balance = calculateBalance(totalSpendings, totalInvestments);
    }
    
    private void setNetWorth(double[] spendings, double[] investments) {
        balance = calculateBalance(spendings, investments);
    }

    public double getTotalSpendings() {
        return totalSpendings;
    }

    public void setTotalSpendings(double totalSpendings) {
        this.totalSpendings = totalSpendings;
    }

    public double getTotalInvestments() {
        return totalInvestments;
    }

    public void setTotalInvestments(double totalInvestments) {
        this.totalInvestments = totalInvestments;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    private void setStatus(double balance) {
        if(balance > 0)
            status = true;
        else
            status = false;
    }
    
    // Custom methods to calculate balance (overloading)
    public double calculateBalance(double totalSpendings, double totalInvestments) {
        balance = totalInvestments - totalSpendings;
        return balance;
    }
    
    public double calculateBalance(double[] spendings, double[] investments) {

        for(int i = 0; i < spendings.length; i++)
            totalSpendings = totalSpendings + spendings[i];
        
        for(int j = 0; j < investments.length; j++)
            totalInvestments = totalInvestments + investments[j];
        
        balance = totalInvestments - totalSpendings;

        return balance;
        
    }
    
    // Method to calculate net worth
    public void calculateNetWorth(int accountID) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        double[] spendings;
        double[] investments;
        
        double finalNetWorth;
        
        // Getting information to calculate net worth
        String[] spendingAmountHeaders = {"AccountID", "SpendingID", "SpendingAmount"};
        String spendingAmountQuery = "SELECT * FROM SpendingAmount WHERE AccountID = " + accountID;
        String[] investmentAmountHeaders = {"AccountID", "InvestmentID", "InvestmentAmount"};
        String investmentAmountQuery = "SELECT * FROM InvestmentAmount WHERE AccountID = " + accountID;
        
        Object[][] spendingAmount = databaseObject.getData(spendingAmountHeaders, spendingAmountQuery);
        Object[][] investmentAmount = databaseObject.getData(investmentAmountHeaders, investmentAmountQuery);
        
        spendings = new double[spendingAmount.length];
        investments = new double[investmentAmount.length];
        
        for(int i = 0; i < spendingAmount.length; i++) {
            spendings[i] = Double.parseDouble((String) spendingAmount[i][2]);
        }
        
        for(int j = 0; j < investmentAmount.length; j++) {
            investments[j] = Double.parseDouble((String) investmentAmount[j][2]);
        }
        
        // Calculating the net worth
        finalNetWorth = calculateBalance(spendings, investments);
        
        finalNetWorth = Math.round(finalNetWorth * 10d) / 10d;
        
        databaseObject.executeQuery("UPDATE NetWorth SET NetWorth = " + finalNetWorth + " WHERE AccountID = " + accountID);
        
        if(finalNetWorth < 0) {
            NotificationFrame notificationGUI = new NotificationFrame("Heads up! Your net worth is in the negative.");
        }
    }
    
    // This returns when the object of this class is called
    @Override
    public String toString() {
        if(status == true)
            return "Net Worth" +
                   "\n---" +
                   "\nBalance: " + getBalance() + 
                   "\nTotal Spendings: " + getTotalSpendings() + 
                   "\nTotal Investments: " + getTotalInvestments() + 
                   "\nStatus: Positive (" + getStatus() + ")";
        else
            return "Net Worth" +
                   "\n---" +
                   "\nBalance: " + getBalance() + 
                   "\nTotal Spendings: " + getTotalSpendings() + 
                   "\nTotal Investments: " + getTotalInvestments() + 
                   "\nStatus: Negative (" + getStatus() + ")";
    }
    
}
