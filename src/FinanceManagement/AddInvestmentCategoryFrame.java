//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
    AddInvestmentCategoryFrame.java
    Frame that allows the user to add a investment category
*/

public class AddInvestmentCategoryFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel ADD_INVESTMENT_CATEGORY_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel INVESTMENT_CATEGORY_NAME_LABEL;
    
    private final JTextField INVESTMENT_CATEGORY_NAME_FIELD;
    
    private final JButton ADD_INVESTMENT_CATEGORY_BUTTON;
    private final JButton BACK_BUTTON;
    
    public AddInvestmentCategoryFrame(int accountID) {
        
        // Setting the frame name
        super("Add Investment Category Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        ADD_INVESTMENT_CATEGORY_LABEL = new JLabel("Add Investment Category");
        INPUT_DESCRIPTION_LABEL = new JLabel("When adding an investment category, enter characters only.");
        INVESTMENT_CATEGORY_NAME_LABEL = new JLabel("Investment Category Name:");
        
        INVESTMENT_CATEGORY_NAME_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        ADD_INVESTMENT_CATEGORY_BUTTON = new JButton("Add Investment Category");
        BACK_BUTTON = new JButton("Back");
        
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
        ADD_INVESTMENT_CATEGORY_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_CATEGORY_NAME_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_CATEGORY_NAME_FIELD.setFont(Constants.MAIN_FONT);
        ADD_INVESTMENT_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        ADD_INVESTMENT_CATEGORY_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_CATEGORY_NAME_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_CATEGORY_NAME_FIELD, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, ADD_INVESTMENT_CATEGORY_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, ADD_INVESTMENT_CATEGORY_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == ADD_INVESTMENT_CATEGORY_BUTTON) {
            
            String investmentCategoryName = INVESTMENT_CATEGORY_NAME_FIELD.getText();
            
            if(addInvestmentCategory(investmentCategoryName)) {
                SuccessFrame successGUI = new SuccessFrame("Investment category added.");
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Unable to add investment category");
            }
            
            INVESTMENT_CATEGORY_NAME_FIELD.setText("");

        }
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Method to add investment category to database
    public boolean addInvestmentCategory(String investmentCategoryName) {
        
        final String TABLE_NAME = "InvestmentCategories";
        final String[] TABLE_HEADERS = {"AccountID", "InvestmentCategoryID", "InvestmentCategoryName"};
        final int ID_COLUMN = 1;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Only one call to method is needed as primary key for account name and description will be the same
        int primaryKey = Constants.incrementPrimaryKey(TABLE_NAME, TABLE_HEADERS, ID_COLUMN);
        
        try {
            PreparedStatement ps;
        
            String addInvestmentCategoryQuery = "INSERT INTO InvestmentCategories VALUES (?, ?, ?)";
        
            ps = databaseObject.getDatabaseConnection().prepareStatement(addInvestmentCategoryQuery);
            ps.setInt(1, ACCOUNT_ID);
            ps.setInt(2, primaryKey);
            ps.setString(3, investmentCategoryName);
            ps.executeUpdate();

            return true;
        }
        catch(SQLException error) {
            return false;
        }
        
    }
    
}
