//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;   
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
    SpendingsExtensionFrame.java
    Frame to show the investments graph and table in more detail
*/

public class SpendingsExtensionFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel FILTER_PANEL;
    private final JPanel EXTENSION_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel SPENDINGS_EXTENSION_LABEL;
    private final JLabel EXTENSION_DESCRIPTION_LABEL;
    private final JLabel FILTER_BY_AMOUNT_LABEL;
    
    private final JTextField AMOUNT_FIELD;
    
    private final String[] SPENDINGS_TABLE_HEADERS;
    
    private JTable spendingsTable;
    
    private final JButton FILTER_BUTTON;
    private final JButton UNFILTER_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    // First constructor with the default table parameters
    public SpendingsExtensionFrame(int accountID) {
        
        // Setting the frame name
        super("Spendings Extension Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        FILTER_PANEL = new JPanel(new GridBagLayout());
        EXTENSION_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        SPENDINGS_EXTENSION_LABEL = new JLabel("Spendings Extension");
        EXTENSION_DESCRIPTION_LABEL = new JLabel("This window is an extension of spendings which shows a pie chart representing the percentage of each category.");
        FILTER_BY_AMOUNT_LABEL = new JLabel("Filter By Amount:");
        
        AMOUNT_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        SPENDINGS_TABLE_HEADERS = new String[] {"Spending ID","Spending Name","Spending Category","Spending Amount"};
        
        spendingsTable = new JTable(getSpendingsTableRecords(), SPENDINGS_TABLE_HEADERS);
        
        FILTER_BUTTON = new JButton("Filter by Amount");
        UNFILTER_BUTTON = new JButton("Remove Filter");
        BACK_BUTTON = new JButton("Back");
        
        WIDTH = 930;
        HEIGHT = 740;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        SPENDINGS_EXTENSION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        EXTENSION_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        FILTER_BY_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        AMOUNT_FIELD.setFont(Constants.MAIN_FONT);
        FILTER_BUTTON.setFont(Constants.MAIN_FONT);
        UNFILTER_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);

        spendingsTable.setEnabled(false);
        spendingsTable.setGridColor(Color.LIGHT_GRAY);
        
        FILTER_BUTTON.addActionListener(this);
        UNFILTER_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, SPENDINGS_EXTENSION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(FILTER_PANEL, FILTER_BY_AMOUNT_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(FILTER_PANEL, AMOUNT_FIELD, 1, 0, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(FILTER_PANEL, FILTER_BUTTON, 2, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(FILTER_PANEL, UNFILTER_BUTTON, 3, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, FILTER_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new JScrollPane(spendingsTable), 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new GraphPanel(), 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_PANEL, 0, 3, 1, 1, GridBagConstraints.CENTER);

        NAVIGATION_PANEL.add(BACK_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // Second constructor with filtered table parameters
    public SpendingsExtensionFrame(int accountID, JTable spendingsTableInput) {
        
        // Setting the frame name
        super("Spendings Extension Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        FILTER_PANEL = new JPanel(new GridBagLayout());
        EXTENSION_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        SPENDINGS_EXTENSION_LABEL = new JLabel("Spendings Extension");
        EXTENSION_DESCRIPTION_LABEL = new JLabel("This window is an extension of spendings which shows a pie chart representing the percentage of each category.");
        FILTER_BY_AMOUNT_LABEL = new JLabel("Filter By Amount:");
        
        AMOUNT_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        SPENDINGS_TABLE_HEADERS = new String[] {"Spending ID","Spending Name","Spending Category","Spending Amount"};
        
        spendingsTable = spendingsTableInput;
        
        FILTER_BUTTON = new JButton("Filter by Amount");
        UNFILTER_BUTTON = new JButton("Remove Filter");
        BACK_BUTTON = new JButton("Back");
        
        WIDTH = 930;
        HEIGHT = 740;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        SPENDINGS_EXTENSION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        EXTENSION_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        FILTER_BY_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        AMOUNT_FIELD.setFont(Constants.MAIN_FONT);
        FILTER_BUTTON.setFont(Constants.MAIN_FONT);
        UNFILTER_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);

        spendingsTable.setEnabled(false);
        spendingsTable.setGridColor(Color.LIGHT_GRAY);
        
        FILTER_BUTTON.addActionListener(this);
        UNFILTER_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, SPENDINGS_EXTENSION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(FILTER_PANEL, FILTER_BY_AMOUNT_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(FILTER_PANEL, AMOUNT_FIELD, 1, 0, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(FILTER_PANEL, FILTER_BUTTON, 2, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(FILTER_PANEL, UNFILTER_BUTTON, 3, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, FILTER_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new JScrollPane(spendingsTable), 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new GraphPanel(), 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_PANEL, 0, 3, 1, 1, GridBagConstraints.CENTER);

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
        
        if(buttonPressed == FILTER_BUTTON) {
            double amount;
            
            try {
                amount = Double.parseDouble(AMOUNT_FIELD.getText());
                
                spendingsTable = new JTable(getSpendingsTableRecords(amount), SPENDINGS_TABLE_HEADERS);
                
                SpendingsExtensionFrame spendingsExtensionGUI = new SpendingsExtensionFrame(ACCOUNT_ID, spendingsTable);
                this.dispose();
                
            } catch(NumberFormatException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please input an integer or decimal number!");
            }
        }
        if(buttonPressed == UNFILTER_BUTTON) {
            SpendingsExtensionFrame spendingsExtensionGUI = new SpendingsExtensionFrame(ACCOUNT_ID);
            this.dispose();
        }
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Sub class to graph the pie chart of the spending categories
    public class GraphPanel extends JPanel {
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            
            // Declaring variables and 2DGraphics object
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Object[][] categoriesWithAmounts = PieChartArcs.calculateAmountsPerCategory(ACCOUNT_ID);
            
            int amountOfCategories = categoriesWithAmounts.length;
            double totalAmount = 0;
            double percentOfTotal = 0;
            double doubleArcAngle;
            int arcAngle;
            int previousAngle = 0;
            Color randomColor;
            int displacementFromChart = 240;

            // Getting the total amount from categories
            for(int i = 0; i < amountOfCategories; i++) {
                totalAmount = totalAmount + Double.valueOf(categoriesWithAmounts[i][2].toString());
            }
            
            // Drawing the arcs for the categories
            for(int i = 0; i < amountOfCategories; i++) {
                randomColor = new Color(((int) (Math.random() * (255 - 0)) + 0), ((int) (Math.random() * (255 - 0)) + 0), ((int) (Math.random() * (255 - 0)) + 0));

                percentOfTotal = Double.valueOf(categoriesWithAmounts[i][2].toString()) / totalAmount;
                
                doubleArcAngle = (percentOfTotal * 360);
                
                doubleArcAngle = Math.round(doubleArcAngle * 1.0) / 1.0;
                
                arcAngle = (int) doubleArcAngle;
                g2d.setColor(randomColor);
                
                if(previousAngle == 0) {
                    g2d.fillArc(70, 0, 200, 200, 0, arcAngle);
                } else {
                    g2d.fillArc(70, 0, 200, 200, previousAngle, arcAngle);
                }
                previousAngle = previousAngle + arcAngle;
                
                percentOfTotal = Math.round(percentOfTotal * 10000.0) / 10000.0;
                
                if(percentOfTotal > 0) {
                    g2d.fillRect(50, displacementFromChart + (i * 30), 20, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(Constants.MAIN_FONT);
                    g2d.drawString(categoriesWithAmounts[i][1].toString() + " " + (Math.round((percentOfTotal * 100) * 100d) / 100d) + "%", 90, displacementFromChart + 15 + (i * 30));
                }
            }
            
        }
        
    }
    
    // Gets the spending table records for the JTable
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
    
    // Gets the spending table with a specific filter (amount)
    public String[][] getSpendingsTableRecords(double spendingAmountFilter) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[][] spendingsTableRecords;
        int numberOfSpendings;
        
        String[] tableHeaders1 = {"SpendingID", "SpendingName"};
        String query1 = "SELECT SpendingID, SpendingName FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders2 = {"SpendingID", "SpendingAmount"};
        String query2 = "SELECT SpendingID, SpendingAmount FROM SpendingAmount WHERE AccountID = " + ACCOUNT_ID + " AND SpendingAmount = " + spendingAmountFilter;
        String[] tableHeaders3 = {"SpendingID", "SpendingCategoryID"};
        String query3 = "SELECT SpendingID, SpendingCategoryID FROM SpendingCategoriesToSpendings WHERE AccountID = " + ACCOUNT_ID;
        
        Object[][] accountSpendingsNames = databaseObject.getData(tableHeaders1, query1);
        Object[][] spendingAmount = databaseObject.getData(tableHeaders2, query2);
        Object[][] spendingCategories = databaseObject.getData(tableHeaders3, query3);
        
        numberOfSpendings = accountSpendingsNames.length;
        
        spendingsTableRecords = new String[numberOfSpendings][4];
        
        for(int i = 0; i < 1; i++) {
            spendingsTableRecords[i][0] = (String) accountSpendingsNames[i][0];
            spendingsTableRecords[i][1] = (String) accountSpendingsNames[i][1];
            spendingsTableRecords[i][2] = getSpendingCategoryString(Integer.parseInt((String) spendingCategories[0][1]));
            spendingsTableRecords[i][3] = (String) spendingAmount[i][1];
        }

        return spendingsTableRecords;
        
    }
    
    // Gets the spending table with a specific filter (category)
    public String[][] getSpendingsTableRecords(String category) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[][] spendingsTableRecords;
        int numberOfSpendings;
        
        int categoryID = getCategoryID(category);
        
        String[] tableHeaders1 = {"SpendingID", "SpendingName"};
        String query1 = "SELECT SpendingID, SpendingName FROM AccountSpendingsNames WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders2 = {"SpendingID", "SpendingAmount"};
        String query2 = "SELECT SpendingID, SpendingAmount FROM SpendingAmount WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders3 = {"SpendingID", "SpendingCategoryID"};
        String query3 = "SELECT SpendingID, SpendingCategoryID FROM SpendingCategoriesToSpendings WHERE AccountID = " + ACCOUNT_ID + " AND SpendingCategoryID = " + categoryID;
        
        Object[][] accountSpendingsNames = databaseObject.getData(tableHeaders1, query1);
        Object[][] spendingAmount = databaseObject.getData(tableHeaders2, query2);
        Object[][] spendingCategories = databaseObject.getData(tableHeaders3, query3);
        
        numberOfSpendings = accountSpendingsNames.length;
        
        spendingsTableRecords = new String[numberOfSpendings][4];
        
        for(int i = 0; i < spendingCategories.length; i++) {
            spendingsTableRecords[i][0] = (String) accountSpendingsNames[i][0];
            spendingsTableRecords[i][1] = (String) accountSpendingsNames[i][1];
            spendingsTableRecords[i][2] = getSpendingCategoryString(Integer.parseInt((String) spendingCategories[i][1]));
            spendingsTableRecords[i][3] = (String) spendingAmount[i][1];
        }

        return spendingsTableRecords;
        
    }
    
    // Returns the spending category string with ID
    public String getSpendingCategoryString(int spendingCategoryID) {
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        return (String) databaseObject.getSpecificData("SELECT SpendingCategoryName FROM SpendingCategories WHERE SpendingCategoryID = " + spendingCategoryID, "SpendingCategoryName");

    }
    
    // Returns the categories for the spendings
    public String[] getCategories() {
        Object[][] categoriesWithAmounts = PieChartArcs.calculateAmountsPerCategory(ACCOUNT_ID);
        
        String[] categories = new String[categoriesWithAmounts.length];
        
        for(int i = 0; i < categoriesWithAmounts.length; i++) {
            categories[i] = categoriesWithAmounts[i][1].toString();
        }
        
        return categories;
    }
    
    // Returns the category ID for a certain spending category
    public int getCategoryID(String spendingCategory) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String spendingIDQuery = "SELECT SpendingCategoryID FROM SpendingCategories WHERE SpendingCategoryName = '" + spendingCategory + "'";
        String tableHeader = "SpendingCategoryID";
        
        int spendingCategoryID = (Integer) databaseObject.getSpecificData(spendingIDQuery, tableHeader);
        
        return spendingCategoryID;
    }
    
}
