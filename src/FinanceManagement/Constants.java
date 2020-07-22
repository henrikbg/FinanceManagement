//package FinanceManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
    Constants.java
    A class full with constants that are used throughout the application.
*/

public class Constants {
    
    // Database
    public static final String DATABASE_NAME = "FinanceManagement";
    
    // Icon
    private static final java.net.URL FINANCE_PICTURE = Constants.class.getResource("Finance_80x80.png");
    public static final ImageIcon FINANCE_ICON = new ImageIcon(FINANCE_PICTURE);
    
    // Frame sizing
    public static final int HORIZONTAL_DISPLACEMENT = 400;
    public static final int VERTICAL_DISPLACEMENT = 200;
    public static final int EXTRA_WIDTH = 520;
    public static final int EXTRA_HEIGHT = 170;
    public static final int INPUT_WIDTH = 700;
    public static final int INPUT_HEIGHT = 470;
    
    // Frame colors
    public static final Color HEADER_BACKGROUND_COLOR = new Color(147, 188, 255);
    private static final Color HEADER_BORDER_COLOR = new Color(128, 128, 128);
    private static final Color NAVIGATION_BORDER_COLOR = new Color(198, 198, 198);
    public static final Color SUCCESS_COLOR = new Color(0, 153, 51);
    public static final Color ERROR_COLOR = new Color(255, 0, 0);
    
    // Panel borders
    public static final Border HEADER_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 0, HEADER_BORDER_COLOR);
    public static final Border NAVIGATION_BORDER = BorderFactory.createMatteBorder(1, 0, 0, 0, NAVIGATION_BORDER_COLOR);
    
    // Label fonts
    public static final Font HEADER_FONT = new Font("Impact", Font.BOLD, 40);
    public static final Font SUB_HEADER_FONT = new Font("Impact", Font.BOLD, 26);
    public static final Font MAIN_FONT = new Font("Lucida Grande", Font.PLAIN, 16);
    public static final Font SUCCESS_FONT = new Font("Lucida Grande", Font.BOLD, 26);
    public static final Font ERROR_FONT = new Font("Lucida Grande", Font.BOLD, 26);
    
    // Textfield sizing
    public static final int INPUT_COLUMNS = 20;
    
    // Method to increment primary key based on the last primary key in the table
    public static int incrementPrimaryKey(String tableName, String[] tableHeaders, int idColumn, int accountID) {
        
        int primaryKey;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String dbQuery = "SELECT * FROM " + tableName + " WHERE AccountID = " + accountID;
        
        Object[][] tableData = databaseObject.getData(tableHeaders, dbQuery);
        
        if(tableData.length == 0)
            primaryKey = 1;
        else {
            primaryKey = Integer.parseInt((String) tableData[tableData.length - 1][idColumn]);
            primaryKey++;
        }
        
        return primaryKey;
        
    }
    
    // Method to increment primary key based on the last primary key in the table
    public static int incrementPrimaryKey(String tableName, String[] tableHeaders, int idColumn) {
        
        int primaryKey;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(tableName, tableHeaders);
        
        if(tableData.length == 0)
            primaryKey = 1;
        else {
            primaryKey = Integer.parseInt((String) tableData[tableData.length - 1][idColumn]);
            primaryKey++;
        }
        
        return primaryKey;
        
    }
    
    // Method to check if an object is a string
    public static boolean isString(Object o) {
        if(o instanceof String) {
            return true;
        } else
            return false;
    }
    
    // Method to check if an object is a double
    public static boolean isDouble(Object o) {
        if(o instanceof Double) {
            return true;
        } else
            return false;
    }
    
    // Also another method to check if a string is a double
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    // Method for adding constraints for gridbaglayout
    public static void addComponent(JPanel panel, JComponent component, int x, int y, int width, int height, int align) {
        
        // Creating an object of GridBagConstraints
        GridBagConstraints c = new GridBagConstraints();
        
        // Setting each property individually
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = 100.0;
        c.weighty = 100.0;
        c.insets = new Insets(5, 0, 5, 0);
        c.anchor = align;
        c.fill = GridBagConstraints.NONE;
        
        // Adding component to panel
        panel.add(component, c);
        
    }
    
    // Method to get all the currencies in the database
    public static String[] getCurrencies() {

        final String TABLE_NAME = "CurrencyConversion";
        final String[] TABLE_HEADERS = {"Currency", "DollarConversion"};
        Object[] currencyObjectArray;
        String[] currencyStringArray;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(TABLE_NAME, TABLE_HEADERS);
        
        currencyObjectArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            currencyObjectArray[i] = tableData[i][0];
        }
        
        currencyStringArray = Arrays.copyOf(currencyObjectArray, currencyObjectArray.length, String[].class);
        
        return currencyStringArray;
    }
    
}
