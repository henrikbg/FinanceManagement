//package FinanceManagement;

/*
    CurrencyConversion.java
    Class to convert currencies in the application
*/

public class CurrencyConversion {
    
    // Method that converts to only dollars
    public static double convertToDollar(String currency, double amount) {
        
        final String COLUMN_NAME = "DollarConversion";
        double convertedAmount;
        double conversionRate;
        String currencyQuery;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Getting the currency conversion rate
        currencyQuery = "SELECT " + COLUMN_NAME + " FROM CurrencyConversion WHERE Currency = '" + currency + "'";
        
        conversionRate = (double) databaseObject.getSpecificData(currencyQuery, COLUMN_NAME);
        
        // Calculating currency conversion
        convertedAmount  =amount * conversionRate;
        convertedAmount = Math.round(convertedAmount * 10d) / 10d;
        
        return convertedAmount;
        
    }
    
    // Method that converts from any currency to any currency
    public static double convertToCurrency(String inputCurrency, String outputCurrency, double amount) {
        
        final String COLUMN_NAME = "DollarConversion";
        double dollarValue;
        double convertedAmount;
        double conversionRate;
        String currencyQuery;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Converting to dollars
        currencyQuery = "SELECT " + COLUMN_NAME + " FROM CurrencyConversion WHERE Currency = '" + inputCurrency + "'";
        
        conversionRate = (double) databaseObject.getSpecificData(currencyQuery, COLUMN_NAME);
        
        dollarValue = amount * conversionRate;
        
        // Converting to output currency
        currencyQuery = "SELECT " + COLUMN_NAME + " FROM CurrencyConversion WHERE Currency = '" + outputCurrency + "'";
        
        conversionRate = (double) databaseObject.getSpecificData(currencyQuery, COLUMN_NAME);
        
        convertedAmount = dollarValue / conversionRate;
        convertedAmount = Math.round(convertedAmount * 10d) / 10d;
        
        return convertedAmount;
       
    }
    
}
