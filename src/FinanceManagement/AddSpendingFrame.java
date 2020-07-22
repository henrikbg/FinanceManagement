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
    AddSpendingFrame.java
    Frame that allows the user to add a spending
*/

public class AddSpendingFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel ADD_SPENDING_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel SPENDING_NAME_LABEL;
    private final JLabel SPENDING_CATEGORY_LABEL;
    private final JLabel SPENDING_AMOUNT_LABEL;
    
    private final JTextField SPENDING_NAME_FIELD;
    private final JTextField SPENDING_AMOUNT_FIELD;
    
    private final JButton ADD_SPENDING_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final JComboBox<String> SPENDING_CATEGORIES_COMBO_BOX;
    
    public AddSpendingFrame(int accountID) {
        
        // Setting the frame name
        super("Add Spending Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        ADD_SPENDING_LABEL = new JLabel("Add Spending");
        INPUT_DESCRIPTION_LABEL = new JLabel("When adding a spending, enter integers only.");
        SPENDING_NAME_LABEL = new JLabel("Spending Name:");
        SPENDING_CATEGORY_LABEL = new JLabel("Spending Category:");
        SPENDING_AMOUNT_LABEL = new JLabel("Spending Amount:");
        
        SPENDING_NAME_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        SPENDING_AMOUNT_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        ADD_SPENDING_BUTTON = new JButton("Add Spending");
        BACK_BUTTON = new JButton("Back");
        
        SPENDING_CATEGORIES_COMBO_BOX = new JComboBox<>(getSpendingCategories());
        
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
        ADD_SPENDING_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        SPENDING_NAME_LABEL.setFont(Constants.MAIN_FONT);
        SPENDING_CATEGORY_LABEL.setFont(Constants.MAIN_FONT);
        SPENDING_CATEGORIES_COMBO_BOX.setFont(Constants.MAIN_FONT);
        SPENDING_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        ADD_SPENDING_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        ADD_SPENDING_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, SPENDING_NAME_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_NAME_FIELD, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, SPENDING_CATEGORY_LABEL, 0, 1, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_CATEGORIES_COMBO_BOX, 1, 1, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, SPENDING_AMOUNT_LABEL, 0, 2, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_AMOUNT_FIELD, 1, 2, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, ADD_SPENDING_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, ADD_SPENDING_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == ADD_SPENDING_BUTTON) {
            String spendingNameInput = SPENDING_NAME_FIELD.getText();
            String spendingCategoryInput = (String) SPENDING_CATEGORIES_COMBO_BOX.getSelectedItem();
            String spendingAmountInput = SPENDING_AMOUNT_FIELD.getText();
            
            if(Constants.isDouble(spendingAmountInput)) {
                double spendingAmountDouble = Double.parseDouble(spendingAmountInput);
                
                addSpending(spendingNameInput, spendingCategoryInput, spendingAmountDouble);

                NetWorth netWorthObject = new NetWorth();
                
                netWorthObject.calculateNetWorth(ACCOUNT_ID);

                SuccessFrame successGUI = new SuccessFrame("Spending added to database.");
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Spending name or amount is invalid.");
            }
            
            SPENDING_NAME_FIELD.setText("");
            SPENDING_AMOUNT_FIELD.setText("");
            
        }
        
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Getting the spending categories for adding spending
    public String[] getSpendingCategories() {

        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "SpendingCategoryID", "SpendingCategoryName"};
        Object[] objectCategoriesArray;
        String[] stringCategoriesArray;
        
        String dbQuery = "SELECT * FROM SpendingCategories WHERE AccountID = " + ACCOUNT_ID;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_HEADERS, dbQuery);
        
        objectCategoriesArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectCategoriesArray[i] = tableData[i][2];
        }
        
        stringCategoriesArray = Arrays.copyOf(objectCategoriesArray, objectCategoriesArray.length, String[].class);
        
        return stringCategoriesArray;
    }
    
    // Adding a spending to the database
    public boolean addSpending(String spendingName, String spendingCategory, double spendingAmount) {
        
        final String TABLE_NAME = "AccountSpendingsNames";
        final String[] TABLE_HEADERS = {"AccountID", "SpendingID", "SpendingName"};
        final int ID_COLUMN = 1;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Only one call to method is needed as primary key for account name and description will be the same
        int primaryKey = Constants.incrementPrimaryKey(TABLE_NAME, TABLE_HEADERS, ID_COLUMN, ACCOUNT_ID);
        
        try {
            String accountSpendingsNamesQuery = "INSERT INTO AccountSpendingsNames VALUES (?, ?, ?)";
        
            PreparedStatement ps1 = databaseObject.getDatabaseConnection().prepareStatement(accountSpendingsNamesQuery);
            ps1.setInt(1, ACCOUNT_ID);
            ps1.setInt(2, primaryKey);
            ps1.setString(3, spendingName);
            ps1.executeUpdate();
        
            String spendingAmountQuery = "INSERT INTO SpendingAmount VALUES (?, ?, ?)";
        
            PreparedStatement ps2 = databaseObject.getDatabaseConnection().prepareStatement(spendingAmountQuery);
            ps2.setInt(1, ACCOUNT_ID);
            ps2.setInt(2, primaryKey);
            ps2.setDouble(3, spendingAmount);
            ps2.executeUpdate();
            
            String spendingCategoriesToSpendingsQuery = "INSERT INTO SpendingCategoriesToSpendings VALUES (?, ?, ?)";
        
            PreparedStatement ps3 = databaseObject.getDatabaseConnection().prepareStatement(spendingCategoriesToSpendingsQuery);
            ps3.setInt(1, ACCOUNT_ID);
            ps3.setInt(2, primaryKey);
            ps3.setInt(3, getSpendingCategoryID(spendingCategory));
            ps3.executeUpdate();

            return true;
        }
        catch(SQLException error) {
            System.out.println("Error in addSpending()!");
            error.printStackTrace();
            return false;
        }
        
    }
    
    // Getting a spending category ID to enter into the database
    public int getSpendingCategoryID(String spendingCategory) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String spendingIDQuery = "SELECT SpendingCategoryID FROM SpendingCategories WHERE SpendingCategoryName = '" + spendingCategory + "'";
        String tableHeader = "SpendingCategoryID";
        
        int spendingCategoryID = (Integer) databaseObject.getSpecificData(spendingIDQuery, tableHeader);
        
        return spendingCategoryID;
    }
    
}
