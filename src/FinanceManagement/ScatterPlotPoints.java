//package FinanceManagement;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
    ScatterPlotPoints.java
    Class to calculate the graph points for the investments scatter plot
*/

public class ScatterPlotPoints {
    
    public static ArrayList<Point> calculateGraphPoints(int accountID, int investmentID) {
        
        // Creating an arraylist of type point for graph points
        ArrayList<Point> finalPoints = new ArrayList<>();
        
        // Variables
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        final String[] TABLE_HEADERS = {"InvestmentDate","InvestmentAmount"};
        String tableQuery = "SELECT InvestmentDate, InvestmentAmount FROM InvestmentTrends WHERE AccountID = " + accountID + " AND InvestmentID = " + investmentID;
        Object[][] tableData;
        int MAX_COLUMNS = 5;
        
        // Getting database information
        tableData = databaseObject.getData(TABLE_HEADERS, tableQuery);
        
        System.out.println(Arrays.deepToString(tableData));
        
        // Calculating the graph points
        for(int i = tableData.length - 1; i > (tableData.length - 2 - MAX_COLUMNS) && i > -1; i--) {    
            int x = i;
            int y = (int) Double.parseDouble(tableData[i][1].toString());
            finalPoints.add(new Point(x,y));
            System.out.println("Added new point: " + new Point(x,y));
        }
        
        Collections.reverse(finalPoints);
        
        return finalPoints;
            
    }
    
}
