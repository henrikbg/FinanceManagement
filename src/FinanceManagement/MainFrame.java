//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
    MainFrame.java
    JFrame that shows spendings, investments, and other extra information
*/

public class MainFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel EXTRA_PANEL;
    private final JPanel NET_WORTH_PANEL;
    private final JPanel EXTRA_BUTTON_PANEL;
    private final JPanel SPENDINGS_PANEL;
    private final JPanel SPENDINGS_BUTTON_PANEL;
    private final JPanel SPENDINGS_INFORMATION_PANEL;
    private final JPanel INVESTMENTS_PANEL;
    private final JPanel INVESTMENTS_BUTTON_PANEL;
    private final JPanel INVESTMENTS_INFORMATION_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel NET_WORTH_LABEL;
    private final JLabel NET_WORTH_AMOUNT_LABEL;
    private final JLabel ACCOUNT_ID_LABEL;
    private final JLabel SPENDINGS_LABEL;
    private final JLabel SPENDING_CATEGORIES_LABEL;
    private final JLabel INVESTMENTS_LABEL;
    private final JLabel INVESTMENT_CATEGORIES_LABEL;
    
    private final JButton CURRENCY_CONVERSION_BUTTON;
    private final JButton DEPRECIATION_BUTTON;
    private final JButton ADD_SPENDING_BUTTON;
    private final JButton EDIT_SPENDING_BUTTON;
    private final JButton DELETE_SPENDING_BUTTON;
    private final JButton ADD_SPENDING_CATEGORY_BUTTON;
    private final JButton EDIT_SPENDING_CATEGORY_BUTTON;
    private final JButton SPENDINGS_EXTENSION_BUTTON;
    private final JButton ADD_INVESTMENT_BUTTON;
    private final JButton EDIT_INVESTMENT_BUTTON;
    private final JButton DELETE_INVESTMENT_BUTTON;
    private final JButton ADD_INVESTMENT_CATEGORY_BUTTON;
    private final JButton EDIT_INVESTMENT_CATEGORY_BUTTON;
    private final JButton INVESTMENTS_EXTENSION_BUTTON;
    private final JButton HELP_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final String[] SPENDINGS_TABLE_HEADERS;
    private final String[] INVESTMENTS_TABLE_HEADERS;
    
    private final JTable SPENDINGS_TABLE;
    private final JTable INVESTMENTS_TABLE;
    
    private final int HORIZONTAL_DISPLACEMENT;
    private final int VERTICAL_DISPLACEMENT;
    private final int WIDTH;
    private final int HEIGHT;
    
    public MainFrame(int accountID) {
        
        // Setting the frame name
        super("Main Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        EXTRA_PANEL = new JPanel(new GridBagLayout());
        NET_WORTH_PANEL = new JPanel(new GridBagLayout());
        EXTRA_BUTTON_PANEL = new JPanel(new GridBagLayout());
        SPENDINGS_PANEL = new JPanel(new GridBagLayout());
        SPENDINGS_BUTTON_PANEL = new JPanel(new GridBagLayout());
        SPENDINGS_INFORMATION_PANEL = new JPanel(new GridBagLayout());
        INVESTMENTS_PANEL = new JPanel(new GridBagLayout());
        INVESTMENTS_BUTTON_PANEL = new JPanel(new GridBagLayout());
        INVESTMENTS_INFORMATION_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        NET_WORTH_LABEL = new JLabel("Net Worth: ");
        NET_WORTH_AMOUNT_LABEL = new JLabel("$" + Double.toString(getNetWorth()));
        ACCOUNT_ID_LABEL = new JLabel("Account ID: " + ACCOUNT_ID);
        SPENDINGS_LABEL = new JLabel("Spendings");
        SPENDING_CATEGORIES_LABEL = new JLabel("Categories");
        INVESTMENTS_LABEL = new JLabel("Investments");
        INVESTMENT_CATEGORIES_LABEL = new JLabel("Categories");
        
        CURRENCY_CONVERSION_BUTTON = new JButton("Currency Conversion");
        DEPRECIATION_BUTTON = new JButton("Depreciation");
        ADD_SPENDING_BUTTON = new JButton("Add Spending");
        EDIT_SPENDING_BUTTON = new JButton("Edit Spending");
        DELETE_SPENDING_BUTTON = new JButton("Delete Spending");
        ADD_SPENDING_CATEGORY_BUTTON = new JButton("Add Spending Category");
        EDIT_SPENDING_CATEGORY_BUTTON = new JButton("Edit Spending Category");
        SPENDINGS_EXTENSION_BUTTON = new JButton("Expand Spendings");
        ADD_INVESTMENT_BUTTON = new JButton("Add Investment");
        EDIT_INVESTMENT_BUTTON = new JButton("Edit Investment");
        DELETE_INVESTMENT_BUTTON = new JButton("Delete Investment");
        ADD_INVESTMENT_CATEGORY_BUTTON = new JButton("Add Investment Category");
        EDIT_INVESTMENT_CATEGORY_BUTTON = new JButton("Edit Investment Category");
        INVESTMENTS_EXTENSION_BUTTON = new JButton("Expand Investments");
        HELP_BUTTON = new JButton("Help");
        BACK_BUTTON = new JButton("Back");
        
        SPENDINGS_TABLE_HEADERS = new String[] {"Spending ID","Spending Name","Spending Category","Spending Amount"};
        INVESTMENTS_TABLE_HEADERS = new String[] {"Investment ID","Investment Name","Investment Category","Investment Amount"};
        
        SPENDINGS_TABLE = new JTable(getSpendingsTableRecords(), SPENDINGS_TABLE_HEADERS);
        INVESTMENTS_TABLE = new JTable(getInvestmentsTableRecords(), INVESTMENTS_TABLE_HEADERS);
        
        HORIZONTAL_DISPLACEMENT = 0;
        VERTICAL_DISPLACEMENT = 0;
        WIDTH = 1540;
        HEIGHT = 830;
        
        // Setting up the frame
        this.setBounds(HORIZONTAL_DISPLACEMENT, VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        if(getNetWorth() > 0) {
            NET_WORTH_AMOUNT_LABEL.setForeground(Constants.SUCCESS_COLOR);
        } else {
            NET_WORTH_AMOUNT_LABEL.setForeground(Constants.ERROR_COLOR);
        }
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        NET_WORTH_LABEL.setFont(Constants.SUB_HEADER_FONT);
        NET_WORTH_AMOUNT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        ACCOUNT_ID_LABEL.setFont(Constants.MAIN_FONT);
        SPENDINGS_LABEL.setFont(Constants.SUB_HEADER_FONT);
        SPENDING_CATEGORIES_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INVESTMENTS_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INVESTMENT_CATEGORIES_LABEL.setFont(Constants.SUB_HEADER_FONT);
        CURRENCY_CONVERSION_BUTTON.setFont(Constants.MAIN_FONT);
        DEPRECIATION_BUTTON.setFont(Constants.MAIN_FONT);
        ADD_SPENDING_BUTTON.setFont(Constants.MAIN_FONT);
        EDIT_SPENDING_BUTTON.setFont(Constants.MAIN_FONT);
        DELETE_SPENDING_BUTTON.setFont(Constants.MAIN_FONT);
        ADD_SPENDING_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        EDIT_SPENDING_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        SPENDINGS_EXTENSION_BUTTON.setFont(Constants.MAIN_FONT);
        ADD_INVESTMENT_BUTTON.setFont(Constants.MAIN_FONT);
        EDIT_INVESTMENT_BUTTON.setFont(Constants.MAIN_FONT);
        DELETE_INVESTMENT_BUTTON.setFont(Constants.MAIN_FONT);
        ADD_INVESTMENT_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        EDIT_INVESTMENT_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        INVESTMENTS_EXTENSION_BUTTON.setFont(Constants.MAIN_FONT);
        HELP_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);

        SPENDINGS_TABLE.setEnabled(false);
        SPENDINGS_TABLE.setGridColor(Color.LIGHT_GRAY);
        INVESTMENTS_TABLE.setEnabled(false);
        INVESTMENTS_TABLE.setGridColor(Color.LIGHT_GRAY);
        
        CURRENCY_CONVERSION_BUTTON.addActionListener(this);
        DEPRECIATION_BUTTON.addActionListener(this);
        ADD_SPENDING_BUTTON.addActionListener(this);
        EDIT_SPENDING_BUTTON.addActionListener(this);
        DELETE_SPENDING_BUTTON.addActionListener(this);
        ADD_SPENDING_CATEGORY_BUTTON.addActionListener(this);
        EDIT_SPENDING_CATEGORY_BUTTON.addActionListener(this);
        SPENDINGS_EXTENSION_BUTTON.addActionListener(this);
        ADD_INVESTMENT_BUTTON.addActionListener(this);
        EDIT_INVESTMENT_BUTTON.addActionListener(this);
        DELETE_INVESTMENT_BUTTON.addActionListener(this);
        ADD_INVESTMENT_CATEGORY_BUTTON.addActionListener(this);
        EDIT_INVESTMENT_CATEGORY_BUTTON.addActionListener(this);
        INVESTMENTS_EXTENSION_BUTTON.addActionListener(this);
        HELP_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 8, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 9, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 10, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 11, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 12, 0, 1, 1, GridBagConstraints.CENTER);
        
        // Extra panel
        Constants.addComponent(NET_WORTH_PANEL, NET_WORTH_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(NET_WORTH_PANEL, NET_WORTH_AMOUNT_LABEL, 1, 0, 1, 1, GridBagConstraints.LINE_START);

        Constants.addComponent(EXTRA_BUTTON_PANEL, ACCOUNT_ID_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTRA_BUTTON_PANEL, CURRENCY_CONVERSION_BUTTON, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTRA_BUTTON_PANEL, DEPRECIATION_BUTTON, 0, 2, 1, 1, GridBagConstraints.CENTER);
        
        Constants.addComponent(EXTRA_PANEL, NET_WORTH_PANEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTRA_PANEL, EXTRA_BUTTON_PANEL, 0, 1, 1, 1, GridBagConstraints.CENTER);

        // Spendings panel
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, SPENDINGS_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, ADD_SPENDING_BUTTON, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, EDIT_SPENDING_BUTTON, 1, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, DELETE_SPENDING_BUTTON, 2, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, SPENDING_CATEGORIES_LABEL, 1, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, ADD_SPENDING_CATEGORY_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_BUTTON_PANEL, EDIT_SPENDING_CATEGORY_BUTTON, 1, 3, 1, 1, GridBagConstraints.CENTER);
        
        Constants.addComponent(SPENDINGS_INFORMATION_PANEL, new JScrollPane(SPENDINGS_TABLE), 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_INFORMATION_PANEL, SPENDINGS_EXTENSION_BUTTON, 0, 1, 1, 1, GridBagConstraints.LAST_LINE_END);
        
        Constants.addComponent(SPENDINGS_PANEL, SPENDINGS_BUTTON_PANEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(SPENDINGS_PANEL, SPENDINGS_INFORMATION_PANEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        
        // Investments panel
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, INVESTMENTS_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, ADD_INVESTMENT_BUTTON, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, EDIT_INVESTMENT_BUTTON, 1, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, DELETE_INVESTMENT_BUTTON, 2, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, INVESTMENT_CATEGORIES_LABEL, 1, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, ADD_INVESTMENT_CATEGORY_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_BUTTON_PANEL, EDIT_INVESTMENT_CATEGORY_BUTTON, 1, 3, 1, 1, GridBagConstraints.CENTER);
        
        Constants.addComponent(INVESTMENTS_INFORMATION_PANEL, new JScrollPane(INVESTMENTS_TABLE), 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_INFORMATION_PANEL, INVESTMENTS_EXTENSION_BUTTON, 0, 1, 1, 1, GridBagConstraints.LAST_LINE_END);
        
        Constants.addComponent(INVESTMENTS_PANEL, INVESTMENTS_BUTTON_PANEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INVESTMENTS_PANEL, INVESTMENTS_INFORMATION_PANEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        
        // Adding everything to content panel
        Constants.addComponent(CONTENT_PANEL, EXTRA_PANEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, SPENDINGS_PANEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INVESTMENTS_PANEL, 2, 0, 1, 1, GridBagConstraints.CENTER);
        
        NAVIGATION_PANEL.add(HELP_BUTTON);
        NAVIGATION_PANEL.add(BACK_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // Returns the net worth based on account
    public double getNetWorth() {
        final String TABLE_HEADER = "NetWorth";
        String netWorthQuery = "SELECT NetWorth FROM NetWorth WHERE AccountID = " + ACCOUNT_ID;
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        double netWorth;
        
        netWorth = (double) databaseObject.getSpecificData(netWorthQuery, TABLE_HEADER);
        
        return netWorth;
    }
    
    // Returns the records of the spending table
    public String[][] getSpendingsTableRecords() {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[][] spendingsTableRecords;
        int numberOfSpendings;
        
        String[] tableHeaders1 = {"SpendingID", "SpendingName"};
        String query1 = "SELECT SpendingID, SpendingName FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders2 = {"SpendingID", "SpendingAmount"};
        String query2 = "SELECT SpendingID, SpendingAmount FROM SpendingAmount WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders3 = {"SpendingID", "SpendingCategoryID"};
        String query3 = "SELECT SpendingID, SpendingCategoryID FROM SpendingCategoriesToSpendings WHERE AccountID = " + ACCOUNT_ID;
        
        Object[][] accountSpendingsNames = databaseObject.getData(tableHeaders1, query1);
        Object[][] spendingAmount = databaseObject.getData(tableHeaders2, query2);
        Object[][] spendingCategories = databaseObject.getData(tableHeaders3, query3);
        
        numberOfSpendings = accountSpendingsNames.length;
        
        spendingsTableRecords = new String[numberOfSpendings][4];
        
        for(int i = 0; i < numberOfSpendings; i++) {
            spendingsTableRecords[i][0] = (String) accountSpendingsNames[i][0];
            spendingsTableRecords[i][1] = (String) accountSpendingsNames[i][1];
            spendingsTableRecords[i][2] = getSpendingCategoryString(Integer.parseInt((String) spendingCategories[i][1]));
            spendingsTableRecords[i][3] = (String) spendingAmount[i][1];
        }

        return spendingsTableRecords;
        
    }
    
    // Changed ID to string (spending)
    public String getSpendingCategoryString(int spendingCategoryID) {
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        return (String) databaseObject.getSpecificData("SELECT SpendingCategoryName FROM SpendingCategories WHERE SpendingCategoryID = " + spendingCategoryID, "SpendingCategoryName");

    }
    
    // Returns the records of the investment table
    public String[][] getInvestmentsTableRecords() {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[][] investmentsTableRecords;
        int numberOfInvestments;
        
        String[] tableHeaders1 = {"InvestmentID", "InvestmentName"};
        String query1 = "SELECT InvestmentID, InvestmentName FROM AccountInvestmentsNames WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders2 = {"InvestmentID", "InvestmentAmount"};
        String query2 = "SELECT InvestmentID, InvestmentAmount FROM InvestmentAmount WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders3 = {"InvestmentID", "InvestmentCategoryID"};
        String query3 = "SELECT InvestmentID, InvestmentCategoryID FROM InvestmentCategoriesToInvestments WHERE AccountID = " + ACCOUNT_ID;
        
        Object[][] accountInvestmentsNames = databaseObject.getData(tableHeaders1, query1);
        Object[][] investmentAmount = databaseObject.getData(tableHeaders2, query2);
        Object[][] investmentCategories = databaseObject.getData(tableHeaders3, query3);
        
        numberOfInvestments = accountInvestmentsNames.length;
        
        investmentsTableRecords = new String[numberOfInvestments][4];
        
        for(int i = 0; i < numberOfInvestments; i++) {
            investmentsTableRecords[i][0] = (String) accountInvestmentsNames[i][0];
            investmentsTableRecords[i][1] = (String) accountInvestmentsNames[i][1];
            investmentsTableRecords[i][2] = getInvestmentCategoryString(Integer.parseInt((String) investmentCategories[i][1]));
            investmentsTableRecords[i][3] = (String) investmentAmount[i][1];
        }

        return investmentsTableRecords;
        
    }
    
    // Changes ID to string (investment)
    public String getInvestmentCategoryString(int investmentCategoryID) {
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        return (String) databaseObject.getSpecificData("SELECT InvestmentCategoryName FROM InvestmentCategories WHERE InvestmentCategoryID = " + investmentCategoryID, "InvestmentCategoryName");

    }
    
    // actionPerformed method for event-driven programming
    @Override
    public void actionPerformed(ActionEvent e) {
        final String checkSpendingCategoriesQuery = "SELECT * FROM SpendingCategories WHERE AccountID = " + ACCOUNT_ID;
        final String checkInvestmentCategoriesQuery = "SELECT * FROM InvestmentCategories WHERE AccountID = " + ACCOUNT_ID;
        DatabaseAccess databaseObject;
        
        Object buttonPressed = e.getSource();
        
        if(buttonPressed == CURRENCY_CONVERSION_BUTTON) {
            CurrencyConversionFrame currencyConversionGUI = new CurrencyConversionFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == DEPRECIATION_BUTTON) {
            DepreciationFrame depreciationGUI = new DepreciationFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == ADD_SPENDING_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists(checkSpendingCategoriesQuery)) {
                AddSpendingFrame addSpendingGUI = new AddSpendingFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create a spending category before adding spendings.");
            }
            
        }
        if(buttonPressed == EDIT_SPENDING_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID)) {
                EditSpendingFrame editSpendingGUI = new EditSpendingFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create a spending before editing spendings.");
            }
        }
        if(buttonPressed == DELETE_SPENDING_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID)) {
                DeleteSpendingFrame deleteSpendingGUI = new DeleteSpendingFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create a spending before deleting spendings.");
            }
        }
        if(buttonPressed == ADD_SPENDING_CATEGORY_BUTTON) {
            AddSpendingCategoryFrame addSpendingCategoryGUI = new AddSpendingCategoryFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == EDIT_SPENDING_CATEGORY_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM SpendingCategories WHERE AccountID = " + ACCOUNT_ID)) {
                EditSpendingCategoryFrame editSpendingCategoryFrame = new EditSpendingCategoryFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create a spending category before editing categories.");
            }
        }
        if(buttonPressed == ADD_INVESTMENT_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM InvestmentCategories WHERE AccountID = " + ACCOUNT_ID)) {
                AddInvestmentFrame addInvestmentGUI = new AddInvestmentFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create an investment category before adding investments.");
            }
        }
        if(buttonPressed == EDIT_INVESTMENT_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM AccountInvestmentsNames WHERE AccountID = " + ACCOUNT_ID)) {
                EditInvestmentFrame editInvestmentGUI = new EditInvestmentFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create an investment before editing investments.");
            }
        }
        if(buttonPressed == DELETE_INVESTMENT_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM AccountInvestmentsNames WHERE AccountID = " + ACCOUNT_ID)) {
                DeleteInvestmentFrame deleteInvestmentGUI = new DeleteInvestmentFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create an investment before deleting investments.");
            }
        }
        if(buttonPressed == ADD_INVESTMENT_CATEGORY_BUTTON) {
            AddInvestmentCategoryFrame addInvestmentCategoryGUI = new AddInvestmentCategoryFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == EDIT_INVESTMENT_CATEGORY_BUTTON) {
            databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            if(databaseObject.dataExists("SELECT * FROM InvestmentCategories WHERE AccountID = " + ACCOUNT_ID)) {
                EditInvestmentCategoryFrame editInvestmentCategoryFrame = new EditInvestmentCategoryFrame(ACCOUNT_ID);
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Create an investment category before editing categories.");
            }
        }
        if(buttonPressed == SPENDINGS_EXTENSION_BUTTON) {
            SpendingsExtensionFrame spendingsExtensionGUI = new SpendingsExtensionFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == INVESTMENTS_EXTENSION_BUTTON) {
            InvestmentsExtensionFrame investmentsExtensionGUI = new InvestmentsExtensionFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == HELP_BUTTON) {
            HelpFrame helpGUI = new HelpFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == BACK_BUTTON) {
            HomeFrame homeGUI = new HomeFrame();
            this.dispose();
        }
        
    }
    
}
