//package FinanceManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
    DatabaseAccess.java
    Class for accessing SQL databases using derby
*/

public class DatabaseAccess {

    // Declaring variables
    private String databaseName;
    private Object[][] data;
    private Object specificData;
    private Connection databaseConnection;
    private Object[] columnData;
    
    // Constructor for when the database name is known
    public DatabaseAccess(String databaseName) {
        this.databaseName = databaseName;
        this.data = null;
        this.specificData = null;
        setDatabaseConnection();
    }
    
    // Constructor for when the database name is unknown
    public DatabaseAccess() {
        this.databaseName = "";
        this.data = null;
        this.specificData = null;
        this.databaseConnection = null;
    }
    
    // Returns database name
    public String getDatabaseName() {
        return databaseName;
    }
    
    // Sets the database name
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
    // Returns a two-dimensional object with table data
    public Object[][] getData(String tableName, String[] tableHeaders) {
        // Declaring variables required for retrieving data
        int columnCount = tableHeaders.length;
        ResultSet rs = null;
        Statement s = null;
        String databaseQuery = "SELECT * FROM " + tableName;
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        
        try {
            // Creating a s
            s = this.databaseConnection.createStatement();
            
            // Initializing the result set with the database query
            rs = s.executeQuery(databaseQuery);
            
            // Going through all the rows in the result set
            while(rs.next()) {
                
                // Creating a row arraylist
                ArrayList<String> row = new ArrayList<>();
                
                // Going through all the rows and adding them to a row object
                for(int i = 0; i < columnCount; i++)
                    row.add(rs.getString(tableHeaders[i]));
                
                // Adding the row object to the data list with all the rows
                dataList.add(row);
                
            }
            
            // Initializing the data object
            this.data = new Object[dataList.size()][columnCount];
            
            // Going through all the data list
            for(int i = 0; i < dataList.size(); i++) {
                
                // Populating the data object
                ArrayList<String> row = dataList.get(i);
                
                for(int j = 0; j < columnCount; j++)
                    this.data[i][j] = row.get(j);
                
            }
            
        }
        
        // Catching SQL error
        catch(SQLException error) {
            System.out.println("Unable to retrieve data from database!");
            error.printStackTrace();
            System.exit(0);
        }
        
        // Returning the data object
        return this.data;
        
    }
    
    public Object[][] getData(String[] tableHeaders, String dbQuery) {
        // Declaring variables required for retrieving data
        int columnCount = tableHeaders.length;
        ResultSet rs = null;
        Statement s = null;
        String databaseQuery = dbQuery;
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        
        try {
            // Creating a s
            s = this.databaseConnection.createStatement();
            
            // Initializing the result set with the database query
            rs = s.executeQuery(databaseQuery);
            
            // Going through all the rows in the result set
            while(rs.next()) {
                
                // Creating a row arraylist
                ArrayList<String> row = new ArrayList<>();
                
                // Going through all the rows and adding them to a row object
                for(int i = 0; i < columnCount; i++)
                    row.add(rs.getString(tableHeaders[i]));
                
                // Adding the row object to the data list with all the rows
                dataList.add(row);
                
            }
            
            // Initializing the data object
            this.data = new Object[dataList.size()][columnCount];
            
            // Going through all the data list
            for(int i = 0; i < dataList.size(); i++) {
                
                // Populating the data object
                ArrayList<String> row = dataList.get(i);
                
                for(int j = 0; j < columnCount; j++)
                    this.data[i][j] = row.get(j);
                
            }
            
        }
        
        // Catching SQL error
        catch(SQLException error) {
            System.out.println("Unable to retrieve data from database!");
            error.printStackTrace();
            System.exit(0);
        }
        
        // Returning the data object
        return this.data;
        
    }
    
    // Sets the data variable
    private void setData(Object[][] data) {
        this.data = data;
    }
    
    public Object getSpecificData(String databaseQuery, String tableHeader) {
        ResultSet rs = null;
        Statement s = null;
        
        try {
            s = this.databaseConnection.createStatement();
            rs = s.executeQuery(databaseQuery);
            
            rs.next();
            
            this.specificData = rs.getObject(tableHeader);
        }
        catch(SQLException error) {
            System.out.println("Unable to retrieve data from database!");
            error.printStackTrace();
            System.exit(0);
        }

        return this.specificData;
        
    }
        
    // Sets the data variable
    private void setSpecificData(Object specificData) {
        this.specificData = specificData;
    }
    
    // Returns the database connection
    public Connection getDatabaseConnection() {
        return this.databaseConnection;
    }
    
    // Sets the database connection
    public void setDatabaseConnection() {
        // Initializing the derby connection URL
        String connectionURL = "jdbc:derby:" + this.databaseName;
        
        this.databaseConnection = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.databaseConnection = DriverManager.getConnection(connectionURL);
        }
        catch(SQLException error) {
            System.out.println("SQL connection error!");
            System.exit(0);
        }
        catch(ClassNotFoundException error) { 
            System.out.println("Class not found (JDBC Embedded Driver)!");
            System.exit(0);
        }
        
    }
    
    // Closes the database connection
    public void closeDatabaseConnection() {
        
        try {
            this.databaseConnection.close();
        }
        catch(Exception error) {
            System.out.println("Database closing error!");
            System.exit(0);
        }
        
    }
    
    // Creates a SQL database
    public void createDatabase(String newDatabaseName) {
        // Setting the database name
        this.databaseName = newDatabaseName;
        
        // Setting the connection URL
        String connectionURL = "jdbc:derby:" + this.databaseName + ";create=true";
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.databaseConnection = DriverManager.getConnection(connectionURL);
            System.out.println("New database created: " + this.databaseName);
            this.databaseConnection.close();
        }
        catch(SQLException error) {
            System.out.println("SQL error creating database: " + this.databaseName);
            System.exit(0);
        }
        catch(ClassNotFoundException error) {
            System.out.println("Class error creating database: " + this.databaseName);
            System.exit(0);
        }
    }
    
    // Creates a SQL table
    public void createTable(String newTable, String databaseName) {
        Statement statement;
        
        setDatabaseName(databaseName);
        setDatabaseConnection();
        try {
            statement = this.databaseConnection.createStatement();
            statement.execute(newTable);
            System.out.println("New table created: " + newTable);
            this.databaseConnection.close();
        }
        catch(SQLException error) {
            System.out.println("Error creating table: " + newTable);
            System.exit(0);
        }
    }
    
    // SQL insert query
    public void executeQuery(String databaseQuery) {
        
        System.out.println("Executing: \n" + databaseQuery);

        try {
            Statement s = this.databaseConnection.createStatement();;
            s.executeUpdate(databaseQuery);
            System.out.println("Query executed.");
        }
        catch(SQLException error) {
            System.out.println("SQL query error.");
            error.printStackTrace();
            System.exit(0);
        }
 
    }
    
    // Checking if data exists in a specific table
    public boolean dataExists(String databaseQuery) {
        ResultSet rs = null;
        Statement s = null;
        boolean exists = false;
        
        try {
            s = this.databaseConnection.createStatement();
            rs = s.executeQuery(databaseQuery);
            
            if(rs.next())
                exists = true;
            else
                exists = false;
        }
        catch(SQLException error) {
            System.out.println("Unable to retrieve data from database!");
            error.printStackTrace();
            System.exit(0);
        }
        
        return exists;
    }
    
    // Checking if a database exists
    public boolean databaseExists(String databaseName) {
        
        String connectionURL = "jdbc:derby:" + databaseName;
        
        this.databaseConnection = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.databaseConnection = DriverManager.getConnection(connectionURL);
            this.databaseConnection.close();
        }
        catch(SQLException error) {
            return false;
        }
        catch(ClassNotFoundException error) { 
            System.out.println("Class not found (JDBC Embedded Driver)!");
            System.exit(0);
        }
        
        return true;
    }
    
}
