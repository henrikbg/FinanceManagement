//package FinanceManagement;

/*
    DatabaseInstallation.java
    Installs the necessary database and tables for the application to function properly
*/

public class DatabaseInstallation {
    
    // Declaring constants
    private final String DATABASE_NAME;
    
    private final String ACCOUNT_NAMES_TABLE;
    private final String ACCOUNT_DESCRIPTIONS_TABLE;
    private final String ACCOUNT_SPENDINGS_NAMES_TABLE;
    private final String SPENDING_AMOUNT_TABLE;
    private final String SPENDING_CATEGORIES_TABLE;
    private final String SPENDING_CATEGORIES_TO_SPENDINGS_TABLE;
    private final String ACCOUNT_INVESTMENTS_NAMES_TABLE;
    private final String INVESTMENT_AMOUNT_TABLE;
    private final String INVESTMENT_CATEGORIES_TABLE;
    private final String INVESTMENT_CATEGORIES_TO_INVESTMENTS_TABLE;
    private final String INVESTMENT_TRENDS_TABLE;
    private final String NET_WORTH_TABLE;
    private final String CURRENCY_CONVERSION_TABLE;
    
    private final String UNITED_STATES_DOLLAR_QUERY;
    private final String EURO_QUERY;
    private final String BRITISH_POUND_QUERY;
    private final String AUSTRALIAN_DOLLAR_QUERY;
    private final String CANADIAN_DOLLAR_QUERY;
    private final String SWISS_FRANC_QUERY;
    private final String JAPANESE_YEN_QUERY;
    private final String INDIAN_RUPEE_QUERY;
    private final String RUSSIAN_RUBLE_QUERY;
    private final String DANISH_KRONE_QUERY;
    private final String NEW_ZEALAND_DOLLAR_QUERY;
    private final String SOUTH_KOREAN_WON_QUERY;
    private final String HONG_KONG_DOLLAR_QUERY;
    private final String MEXICAN_PESO_QUERY;
    private final String NORWEGIAN_KRONE_QUERY;
    private final String BRAZILIAN_REAL_QUERY;
    
    public DatabaseInstallation() {
        
        // Intializing constants
        DATABASE_NAME = "FinanceManagement";
        
        ACCOUNT_NAMES_TABLE = "CREATE TABLE AccountNames (AccountID int, AccountName varchar(255))";
        ACCOUNT_DESCRIPTIONS_TABLE = "CREATE TABLE AccountDescriptions (AccountID int, AccountDescription varchar(255))";
        ACCOUNT_SPENDINGS_NAMES_TABLE = "CREATE TABLE AccountSpendingsNames (AccountID int, SpendingID int, SpendingName varchar(255))";
        SPENDING_AMOUNT_TABLE = "CREATE TABLE SpendingAmount (AccountID int, SpendingID int, SpendingAmount float)";
        SPENDING_CATEGORIES_TABLE = "CREATE TABLE SpendingCategories (AccountID int, SpendingCategoryID int, SpendingCategoryName varchar(255))";
        SPENDING_CATEGORIES_TO_SPENDINGS_TABLE = "CREATE TABLE SpendingCategoriesToSpendings (AccountID int, SpendingID int, SpendingCategoryID int)";
        ACCOUNT_INVESTMENTS_NAMES_TABLE = "CREATE TABLE AccountInvestmentsNames (AccountID int, InvestmentID int, InvestmentName varchar(255))";
        INVESTMENT_AMOUNT_TABLE = "CREATE TABLE InvestmentAmount (AccountID int, InvestmentID int, InvestmentAmount float)";
        INVESTMENT_CATEGORIES_TABLE = "CREATE TABLE InvestmentCategories (AccountID int, InvestmentCategoryID int, InvestmentCategoryName varchar(255))";
        INVESTMENT_CATEGORIES_TO_INVESTMENTS_TABLE = "CREATE TABLE InvestmentCategoriesToInvestments (AccountID int, InvestmentID int, InvestmentCategoryID int)";
        INVESTMENT_TRENDS_TABLE = "CREATE TABLE InvestmentTrends (AccountID int, InvestmentID int, InvestmentDate varchar(255), InvestmentAmount float)";
        NET_WORTH_TABLE = "CREATE TABLE NetWorth (AccountID int, NetWorth float)";
        CURRENCY_CONVERSION_TABLE = "CREATE TABLE CurrencyConversion (Currency varchar(255), DollarConversion float, PRIMARY KEY (Currency))";
        
        UNITED_STATES_DOLLAR_QUERY = "INSERT INTO CurrencyConversion VALUES ('United States Dollar', 1)";
        EURO_QUERY = "INSERT INTO CurrencyConversion VALUES ('Euro', 1.14)";
        BRITISH_POUND_QUERY = "INSERT INTO CurrencyConversion VALUES ('British Pound', 1.33)";
        AUSTRALIAN_DOLLAR_QUERY = "INSERT INTO CurrencyConversion VALUES ('Australian Dollar', 0.71)";
        CANADIAN_DOLLAR_QUERY = "INSERT INTO CurrencyConversion VALUES ('Canadian Dollar', 0.75)";
        SWISS_FRANC_QUERY = "INSERT INTO CurrencyConversion VALUES ('Swiss Franc', 1)";
        JAPANESE_YEN_QUERY = "INSERT INTO CurrencyConversion VALUES ('Japanese Yen', 0.009)";
        INDIAN_RUPEE_QUERY = "INSERT INTO CurrencyConversion VALUES ('Indian Rupee', 0.015)";
        RUSSIAN_RUBLE_QUERY = "INSERT INTO CurrencyConversion VALUES ('Russian Ruble', 0.016)";
        DANISH_KRONE_QUERY = "INSERT INTO CurrencyConversion VALUES ('Danish Krone', 0.15)";
        NEW_ZEALAND_DOLLAR_QUERY = "INSERT INTO CurrencyConversion VALUES ('New Zealand Dollar', 0.69)";
        SOUTH_KOREAN_WON_QUERY = "INSERT INTO CurrencyConversion VALUES ('South Korean Won', 0.00089)";
        HONG_KONG_DOLLAR_QUERY = "INSERT INTO CurrencyConversion VALUES ('Hong Kong Dollar', 0.13)";
        MEXICAN_PESO_QUERY = "INSERT INTO CurrencyConversion VALUES ('Mexican Peso', 0.053)";
        NORWEGIAN_KRONE_QUERY = "INSERT INTO CurrencyConversion VALUES ('Norwegian Krone', 0.12)";
        BRAZILIAN_REAL_QUERY = "INSERT INTO CurrencyConversion VALUES ('Brazilian Real', 0.26)";
        
        // Creating object to access database
        DatabaseAccess databaseObject = new DatabaseAccess();
        
        // Creating new database and setting connection
        databaseObject.createDatabase(DATABASE_NAME);
        databaseObject.setDatabaseConnection();
        
        // Creating tables with SQL queries
        databaseObject.createTable(ACCOUNT_NAMES_TABLE, DATABASE_NAME);
        databaseObject.createTable(ACCOUNT_DESCRIPTIONS_TABLE, DATABASE_NAME);
        databaseObject.createTable(ACCOUNT_SPENDINGS_NAMES_TABLE, DATABASE_NAME);
        databaseObject.createTable(SPENDING_AMOUNT_TABLE, DATABASE_NAME);
        databaseObject.createTable(SPENDING_CATEGORIES_TABLE, DATABASE_NAME);
        databaseObject.createTable(SPENDING_CATEGORIES_TO_SPENDINGS_TABLE, DATABASE_NAME);
        databaseObject.createTable(ACCOUNT_INVESTMENTS_NAMES_TABLE, DATABASE_NAME);
        databaseObject.createTable(INVESTMENT_AMOUNT_TABLE, DATABASE_NAME);
        databaseObject.createTable(INVESTMENT_CATEGORIES_TABLE, DATABASE_NAME);
        databaseObject.createTable(INVESTMENT_CATEGORIES_TO_INVESTMENTS_TABLE, DATABASE_NAME);
        databaseObject.createTable(INVESTMENT_TRENDS_TABLE, DATABASE_NAME);
        databaseObject.createTable(NET_WORTH_TABLE, DATABASE_NAME);
        databaseObject.createTable(CURRENCY_CONVERSION_TABLE, DATABASE_NAME);
        
        databaseObject.setDatabaseConnection();
        
        // Inserting currency conversions
        databaseObject.executeQuery(UNITED_STATES_DOLLAR_QUERY);
        databaseObject.executeQuery(EURO_QUERY);
        databaseObject.executeQuery(BRITISH_POUND_QUERY);
        databaseObject.executeQuery(AUSTRALIAN_DOLLAR_QUERY);
        databaseObject.executeQuery(CANADIAN_DOLLAR_QUERY);
        databaseObject.executeQuery(SWISS_FRANC_QUERY);
        databaseObject.executeQuery(JAPANESE_YEN_QUERY);
        databaseObject.executeQuery(INDIAN_RUPEE_QUERY);
        databaseObject.executeQuery(RUSSIAN_RUBLE_QUERY);
        databaseObject.executeQuery(DANISH_KRONE_QUERY);
        databaseObject.executeQuery(NEW_ZEALAND_DOLLAR_QUERY);
        databaseObject.executeQuery(SOUTH_KOREAN_WON_QUERY);
        databaseObject.executeQuery(HONG_KONG_DOLLAR_QUERY);
        databaseObject.executeQuery(MEXICAN_PESO_QUERY);
        databaseObject.executeQuery(NORWEGIAN_KRONE_QUERY);
        databaseObject.executeQuery(BRAZILIAN_REAL_QUERY);
        
    }
    
    public static void main(String[] args) {
        DatabaseInstallation dbInstall = new DatabaseInstallation();
    }
    
}
