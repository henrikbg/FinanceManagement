//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
    AddInvestmentFrame.java
    Frame that allows the user to add an investment
*/

public class AddInvestmentFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel ADD_INVESTMENT_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel INVESTMENT_NAME_LABEL;
    private final JLabel INVESTMENT_CATEGORY_LABEL;
    private final JLabel INVESTMENT_AMOUNT_LABEL;
    private final JLabel INVESTMENT_DATE_LABEL;
    
    private final JTextField INVESTMENT_NAME_FIELD;
    private final JTextField INVESTMENT_AMOUNT_FIELD;
    
    private final JButton ADD_INVESTMENT_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final String[] MONTHS;
    private final String[] DAYS;
    private final String[] YEARS;
    
    private final JComboBox<String> INVESTMENT_CATEGORIES_COMBO_BOX;
    private final JComboBox<String> MONTH_COMBO_BOX;
    private final JComboBox<String> DAY_COMBO_BOX;
    private final JComboBox<String> YEAR_COMBO_BOX;
    
    public AddInvestmentFrame(int accountID) {
        
        // Setting the frame name
        super("Add Investment Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        ADD_INVESTMENT_LABEL = new JLabel("Add Investment");
        INPUT_DESCRIPTION_LABEL = new JLabel("When adding an investment, enter integers only.");
        INVESTMENT_NAME_LABEL = new JLabel("Investment Name:");
        INVESTMENT_CATEGORY_LABEL = new JLabel("Investment Category:");
        INVESTMENT_AMOUNT_LABEL = new JLabel("Investment Amount:");
        INVESTMENT_DATE_LABEL = new JLabel("Investment Date (MM/DD/YYYY):");
        
        INVESTMENT_NAME_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        INVESTMENT_AMOUNT_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        ADD_INVESTMENT_BUTTON = new JButton("Add Investment");
        BACK_BUTTON = new JButton("Back");
        
        MONTHS = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",};
        DAYS = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        YEARS = new String[] {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};
        
        INVESTMENT_CATEGORIES_COMBO_BOX = new JComboBox<>(getInvestmentCategories());
        MONTH_COMBO_BOX = new JComboBox<>(MONTHS);
        DAY_COMBO_BOX = new JComboBox<>(DAYS);
        YEAR_COMBO_BOX = new JComboBox<>(YEARS);
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        ADD_INVESTMENT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_NAME_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_CATEGORY_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_CATEGORIES_COMBO_BOX.setFont(Constants.MAIN_FONT);
        INVESTMENT_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_DATE_LABEL.setFont(Constants.MAIN_FONT);
        MONTH_COMBO_BOX.setFont(Constants.MAIN_FONT);
        DAY_COMBO_BOX.setFont(Constants.MAIN_FONT);
        YEAR_COMBO_BOX.setFont(Constants.MAIN_FONT);
        ADD_INVESTMENT_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        ADD_INVESTMENT_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_NAME_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_NAME_FIELD, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_CATEGORY_LABEL, 0, 1, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_CATEGORIES_COMBO_BOX, 1, 1, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_AMOUNT_LABEL, 0, 2, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_AMOUNT_FIELD, 1, 2, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_DATE_LABEL, 0, 3, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, MONTH_COMBO_BOX, 1, 3, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, DAY_COMBO_BOX, 2, 3, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, YEAR_COMBO_BOX, 3, 3, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, ADD_INVESTMENT_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, ADD_INVESTMENT_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
        NAVIGATION_PANEL.add(BACK_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // actionPerformed method for event-driven programming
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object buttonPressed = e.getSource();
        
        // Checking various button calls
        if(buttonPressed == ADD_INVESTMENT_BUTTON) {
            String investmentNameInput = INVESTMENT_NAME_FIELD.getText();
            String investmentCategoryInput = (String) INVESTMENT_CATEGORIES_COMBO_BOX.getSelectedItem();
            String investmentAmountInput =INVESTMENT_AMOUNT_FIELD.getText();
            
            int month = Integer.parseInt((String) MONTH_COMBO_BOX.getSelectedItem());
            int day = Integer.parseInt((String) DAY_COMBO_BOX.getSelectedItem());
            int year = Integer.parseInt((String) YEAR_COMBO_BOX.getSelectedItem());
            
            if(Constants.isDouble(investmentAmountInput)) {
                double investmentAmount = Double.parseDouble(investmentAmountInput);
                
                addInvestment(investmentNameInput, investmentCategoryInput, investmentAmount, month, day, year);
                
                NetWorth netWorthObject = new NetWorth();
                
                netWorthObject.calculateNetWorth(ACCOUNT_ID);
                
                SuccessFrame successGUI = new SuccessFrame("Investment added to database.");
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Investment name or amount is invalid.");
            }
            
            INVESTMENT_NAME_FIELD.setText("");
            INVESTMENT_AMOUNT_FIELD.setText("");
            
        }
        
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Getting the investment categories for the combo box
    public String[] getInvestmentCategories() {

        final String ACCOUNT_TABLE_NAME = "InvestmentCategories";
        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "InvestmentCategoryID", "InvestmentCategoryName"};
        Object[] objectCategoriesArray;
        String[] stringCategoriesArray;
        
        String dbQuery = "SELECT * FROM InvestmentCategories WHERE AccountID = " + ACCOUNT_ID;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_HEADERS, dbQuery);
        
        objectCategoriesArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectCategoriesArray[i] = tableData[i][2];
        }
        
        stringCategoriesArray = Arrays.copyOf(objectCategoriesArray, objectCategoriesArray.length, String[].class);
        
        return stringCategoriesArray;
    }
    
    // Adding the investment to the database after button has been clicked
    public boolean addInvestment(String investmentName, String investmentCategory, double investmentAmount, int month, int day, int year) {
        
        final String TABLE_NAME = "AccountInvestmentsNames";
        final String[] TABLE_HEADERS = {"AccountID", "InvestmentID", "InvestmentName"};
        final int ID_COLUMN = 1;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Only one call to method is needed as primary key for account name and description will be the same
        int primaryKey = Constants.incrementPrimaryKey(TABLE_NAME, TABLE_HEADERS, ID_COLUMN, ACCOUNT_ID);
        
        try {
            // All the prepared statements to insert into database
            String accountInvestmentsNamesQuery = "INSERT INTO AccountInvestmentsNames VALUES (?, ?, ?)";
        
            PreparedStatement ps1 = databaseObject.getDatabaseConnection().prepareStatement(accountInvestmentsNamesQuery);
            ps1.setInt(1, ACCOUNT_ID);
            ps1.setInt(2, primaryKey);
            ps1.setString(3, investmentName);
            ps1.executeUpdate();
        
            String investmentAmountQuery = "INSERT INTO InvestmentAmount VALUES (?, ?, ?)";
        
            PreparedStatement ps2 = databaseObject.getDatabaseConnection().prepareStatement(investmentAmountQuery);
            ps2.setInt(1, ACCOUNT_ID);
            ps2.setInt(2, primaryKey);
            ps2.setDouble(3, investmentAmount);
            ps2.executeUpdate();
            
            String investmentCategoriesToInvestmentsQuery = "INSERT INTO InvestmentCategoriesToInvestments VALUES (?, ?, ?)";
        
            PreparedStatement ps3 = databaseObject.getDatabaseConnection().prepareStatement(investmentCategoriesToInvestmentsQuery);
            ps3.setInt(1, ACCOUNT_ID);
            ps3.setInt(2, primaryKey);
            ps3.setInt(3, getInvestmentCategoryID(investmentCategory));
            ps3.executeUpdate();
            
            addDate(primaryKey, month, day, year, investmentAmount);
            
            return true;
        }
        catch(SQLException error) {
            System.out.println("Error in insertAccount()!");
            error.printStackTrace();
            return false;
        }
        
    }
    
    // Retrieving the investment category ID for database entry
    public int getInvestmentCategoryID(String investmentCategory) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String spendingIDQuery = "SELECT InvestmentCategoryID FROM InvestmentCategories WHERE InvestmentCategoryName = '" + investmentCategory + "'";
        String tableHeader = "InvestmentCategoryID";
        
        int spendingCategoryID = (Integer) databaseObject.getSpecificData(spendingIDQuery, tableHeader);
        
        return spendingCategoryID;
    }
    
    // Adding a date to the investment trends table
    public void addDate(int investmentID, int month, int day, int year, double investmentAmount) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String investmentDate = month + "/" + day + "/" + year;
        
        System.out.println(investmentDate);
        
        String addDateQuery = "INSERT INTO InvestmentTrends VALUES (" + ACCOUNT_ID + ", " + investmentID + ", '" + investmentDate + "', " + investmentAmount + ")";
        
        databaseObject.executeQuery(addDateQuery);
            
        System.out.println("Date has been added");
        
    }
    
}
