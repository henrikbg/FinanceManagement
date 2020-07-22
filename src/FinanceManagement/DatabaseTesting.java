//package FinanceManagement;

/*
    DatabaseTesting.java
    Used for testing the database for any potetial issues
*/

public class DatabaseTesting {
    
    public static void main(String[] args) {
        
        DatabaseAccess databaseObject = new DatabaseAccess("FinanceManagement");
        
        databaseObject.executeQuery("DELETE FROM AccountSpendingsNames WHERE AccountID = 2");
        databaseObject.executeQuery("DELETE FROM SpendingAmount WHERE AccountID = 2");
        databaseObject.executeQuery("DELETE FROM SpendingCategoriesToSpendings WHERE AccountID = 2");
      
   }
    
}
