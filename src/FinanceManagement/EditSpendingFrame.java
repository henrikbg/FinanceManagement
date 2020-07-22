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
    EditSpendingFrame.java
    Frame that allows the user to edit a spending
*/

public class EditSpendingFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel EDIT_SPENDING_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel OLD_SPENDING_ID_LABEL;
    private final JLabel SPENDING_NAME_LABEL;
    private final JLabel SPENDING_CATEGORY_LABEL;
    private final JLabel SPENDING_AMOUNT_LABEL;
    
    private final JTextField SPENDING_NAME_FIELD;
    private final JTextField SPENDING_AMOUNT_FIELD;
    
    private final JButton EDIT_SPENDING_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final JComboBox<String> OLD_SPENDING_ID_COMBO_BOX;
    private final JComboBox<String> SPENDING_CATEGORIES_COMBO_BOX;
    
    public EditSpendingFrame(int accountID) {
        
        // Setting the frame name
        super("Edit Spending Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        EDIT_SPENDING_LABEL = new JLabel("Edit Spending");
        INPUT_DESCRIPTION_LABEL = new JLabel("When editing a spending, enter integers only.");
        OLD_SPENDING_ID_LABEL = new JLabel("Old Spending ID: ");
        SPENDING_NAME_LABEL = new JLabel("New Spending Name:");
        SPENDING_CATEGORY_LABEL = new JLabel("New Spending Category:");
        SPENDING_AMOUNT_LABEL = new JLabel("New Spending Amount:");
        
        SPENDING_NAME_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        SPENDING_AMOUNT_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        EDIT_SPENDING_BUTTON = new JButton("Edit Spending");
        BACK_BUTTON = new JButton("Back");
        
        OLD_SPENDING_ID_COMBO_BOX = new JComboBox<>(getOldSpendingIDs());
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
        EDIT_SPENDING_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        OLD_SPENDING_ID_LABEL.setFont(Constants.MAIN_FONT);
        OLD_SPENDING_ID_COMBO_BOX.setFont(Constants.MAIN_FONT);
        SPENDING_NAME_LABEL.setFont(Constants.MAIN_FONT);
        SPENDING_CATEGORY_LABEL.setFont(Constants.MAIN_FONT);
        SPENDING_CATEGORIES_COMBO_BOX.setFont(Constants.MAIN_FONT);
        SPENDING_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        EDIT_SPENDING_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        EDIT_SPENDING_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, OLD_SPENDING_ID_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, OLD_SPENDING_ID_COMBO_BOX, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, SPENDING_NAME_LABEL, 0, 1, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_NAME_FIELD, 1, 1, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, SPENDING_CATEGORY_LABEL, 0, 2, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_CATEGORIES_COMBO_BOX, 1, 2, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, SPENDING_AMOUNT_LABEL, 0, 3, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, SPENDING_AMOUNT_FIELD, 1, 3, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, EDIT_SPENDING_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EDIT_SPENDING_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == EDIT_SPENDING_BUTTON) {
            int oldSpendingID = Integer.parseInt((String) OLD_SPENDING_ID_COMBO_BOX.getSelectedItem());
            String spendingNameInput = SPENDING_NAME_FIELD.getText();
            String spendingCategoryInput = (String) SPENDING_CATEGORIES_COMBO_BOX.getSelectedItem();
            String spendingAmountInput = SPENDING_AMOUNT_FIELD.getText();
            
            if(Constants.isDouble(spendingAmountInput)) {
                double spendingAmount = Double.parseDouble(spendingAmountInput);
                
                System.out.println(editSpending(oldSpendingID, spendingNameInput, spendingCategoryInput, spendingAmount));
                
                NetWorth netWorthObject = new NetWorth();
                
                netWorthObject.calculateNetWorth(ACCOUNT_ID);
                
                SuccessFrame successGUI = new SuccessFrame("Spending edited in database.");
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
    
    // Returns spending categories for the combo box
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
    
    // Returns the spending ids for the database
    public String[] getOldSpendingIDs() {

        final String ACCOUNT_TABLE_NAME = "AccountSpendingsNames";
        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "SpendingID", "SpendingName"};
        Object[] objectCategoryIDsArray;
        String[] stringCategoryIDsArray;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String dbQuery = "SELECT * FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID;
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_HEADERS, dbQuery);
        
        objectCategoryIDsArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectCategoryIDsArray[i] = tableData[i][1];
        }
        
        stringCategoryIDsArray = Arrays.copyOf(objectCategoryIDsArray, objectCategoryIDsArray.length, String[].class);
        
        return stringCategoryIDsArray;
    }
    
    // Edits the spendings in the database
    public boolean editSpending(int oldSpendingID, String spendingName, String spendingCategory, double spendingAmount) {
        
        final String TABLE_NAME = "AccountSpendingsNames";
        final String[] TABLE_HEADERS = {"AccountID", "SpendingID", "SpendingName"};
        final int ID_COLUMN = 1;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        try {
            // PreparedStatement to update database
            String accountSpendingsNamesQuery = "UPDATE AccountSpendingsNames SET SpendingName = ? WHERE AccountID = ? AND SpendingID = ?";
        
            PreparedStatement ps1 = databaseObject.getDatabaseConnection().prepareStatement(accountSpendingsNamesQuery);
            ps1.setString(1, spendingName);
            ps1.setInt(2, ACCOUNT_ID);
            ps1.setInt(3, oldSpendingID);
            ps1.executeUpdate();
            
            String spendingAmountQuery = "UPDATE SpendingAmount SET SpendingAmount = ? WHERE AccountID = ? AND SpendingID = ?";
        
            PreparedStatement ps2 = databaseObject.getDatabaseConnection().prepareStatement(spendingAmountQuery);
            ps2.setDouble(1, spendingAmount);
            ps2.setInt(2, ACCOUNT_ID);
            ps2.setInt(3, oldSpendingID);
            ps2.executeUpdate();
            
            String spendingCategoriesToSpendingsQuery = "UPDATE SpendingCategoriesToSpendings SET SpendingCategoryID = ? WHERE AccountID = ? AND SpendingID = ?";
        
            PreparedStatement ps3 = databaseObject.getDatabaseConnection().prepareStatement(spendingCategoriesToSpendingsQuery);
            ps3.setInt(1, getSpendingCategoryID(spendingCategory));
            ps3.setInt(2, ACCOUNT_ID);
            ps3.setInt(3, oldSpendingID);
            ps3.executeUpdate();
            
            return true;
        }
        catch(SQLException error) {
            System.out.println("Error in insertAccount()!");
            error.printStackTrace();
            return false;
        }
        
    }
    
    public int getSpendingCategoryID(String spendingCategory) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String spendingIDQuery = "SELECT SpendingCategoryID FROM SpendingCategories WHERE SpendingCategoryName = '" + spendingCategory + "'";
        String tableHeader = "SpendingCategoryID";
        
        int spendingCategoryID = (Integer) databaseObject.getSpecificData(spendingIDQuery, tableHeader);
        
        return spendingCategoryID;
    }
    
}
