//package FinanceManagement;

/*
    PieChartArc.java
    Class used to calculate the arcs needed to create a spendings pie chart
*/

public class PieChartArcs {
    
    public static Object[][] calculateAmountsPerCategory(int accountID) {
        
        // Getting all the data from the database to calculate the pie chart arcs
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[] tableHeaders1 = {"SpendingCategoryID", "SpendingCategoryName"};
        String query1 = "SELECT SpendingCategoryID, SpendingCategoryName FROM SpendingCategories WHERE AccountID = " + accountID;
        
        Object[][] spendingCategories = databaseObject.getData(tableHeaders1, query1);
        
        String[] tableHeaders2 = {"SpendingID", "SpendingCategoryID"};
        String query2 = "SELECT SpendingID, SpendingCategoryID FROM SpendingCategoriesToSpendings WHERE AccountID = " + accountID;
        
        Object[][] spendingCategoriesToSpendings = databaseObject.getData(tableHeaders2, query2);
        
        String[] tableHeaders3 = {"SpendingID", "SpendingAmount"};
        String query3 = "SELECT SpendingID, SpendingAmount FROM SpendingAmount WHERE AccountID = " + accountID;
        
        Object[][] spendingAmount = databaseObject.getData(tableHeaders3, query3);
        
        Object[][] spendingCategoriesWithAmounts = new Object[spendingCategoriesToSpendings.length][3];
        
        // Combining all the arrays into one array
        for(int i = 0; i < spendingCategoriesToSpendings.length; i++) {
            spendingCategoriesWithAmounts[i][0] = spendingCategoriesToSpendings[i][0];
            spendingCategoriesWithAmounts[i][1] = spendingCategoriesToSpendings[i][1];
            spendingCategoriesWithAmounts[i][2] = spendingAmount[i][1];
        }
        
        Object[][] amountPerSpendingCategory = new Object[spendingCategories.length][3];
        
        int amountOfCategories = 0;
        
        for(int i = 0; i < spendingCategories.length; i++) {
            amountPerSpendingCategory[i][0] = i + 1;
            amountOfCategories++;
        }
        
        double totalSpendingsPerCategory = 0;
        
        // Calculating the total spendings per category
        for(int i = 0; i < amountOfCategories; i++) {
            for(int j = 0; j < spendingCategoriesWithAmounts.length; j++) {
                if(Integer.parseInt((String) spendingCategoriesWithAmounts[j][1]) == i + 1) {
                    totalSpendingsPerCategory = totalSpendingsPerCategory + Double.parseDouble((String) spendingCategoriesWithAmounts[j][2]);
                }
            }
            amountPerSpendingCategory[i][2] = totalSpendingsPerCategory;
            
            totalSpendingsPerCategory = 0;
        }
        
        for(int i = 0; i < spendingCategories.length; i++) {
            amountPerSpendingCategory[i][1] = spendingCategories[i][1];
        }
        
        // Returning the amount spent per category
        return amountPerSpendingCategory;
    }
    
}
