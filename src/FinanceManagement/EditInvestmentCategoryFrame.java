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
    EditInvestmentCategoryFrame.java
    Frame that allows the user to edit a spending category
*/

public class EditInvestmentCategoryFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel EDIT_INVESTMENT_CATEGORY_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel OLD_INVESTMENT_CATEGORY_ID_LABEL;
    private final JLabel NEW_INVESTMENT_CATEGORY_NAME_LABEL;
    
    private final JComboBox<String> OLD_INVESTMENT_CATEGORY_ID_COMBO_BOX;
    
    private final JTextField NEW_INVESTMENT_CATEGORY_NAME_FIELD;
    
    private final JButton EDIT_INVESTMENT_CATEGORY_BUTTON;
    private final JButton BACK_BUTTON;
    
    public EditInvestmentCategoryFrame(int accountID) {
        
        // Setting the frame name
        super("Edit Investment Category Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        EDIT_INVESTMENT_CATEGORY_LABEL = new JLabel("Edit Investment Category");
        INPUT_DESCRIPTION_LABEL = new JLabel("When editing an investment category, enter characters only.");
        OLD_INVESTMENT_CATEGORY_ID_LABEL = new JLabel("Old Investment Category ID:");
        NEW_INVESTMENT_CATEGORY_NAME_LABEL = new JLabel("New Investment Category Name:");
        
        OLD_INVESTMENT_CATEGORY_ID_COMBO_BOX = new JComboBox<>(getInvestmentCategoryIDs());
        
        NEW_INVESTMENT_CATEGORY_NAME_FIELD = new JTextField(Constants.INPUT_COLUMNS);
        
        EDIT_INVESTMENT_CATEGORY_BUTTON = new JButton("Edit Investment Category");
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
        EDIT_INVESTMENT_CATEGORY_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        OLD_INVESTMENT_CATEGORY_ID_LABEL.setFont(Constants.MAIN_FONT);
        OLD_INVESTMENT_CATEGORY_ID_COMBO_BOX.setFont(Constants.MAIN_FONT);
        NEW_INVESTMENT_CATEGORY_NAME_LABEL.setFont(Constants.MAIN_FONT);
        NEW_INVESTMENT_CATEGORY_NAME_FIELD.setFont(Constants.MAIN_FONT);
        EDIT_INVESTMENT_CATEGORY_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        EDIT_INVESTMENT_CATEGORY_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, OLD_INVESTMENT_CATEGORY_ID_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, OLD_INVESTMENT_CATEGORY_ID_COMBO_BOX, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, NEW_INVESTMENT_CATEGORY_NAME_LABEL, 0, 1, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, NEW_INVESTMENT_CATEGORY_NAME_FIELD, 1, 1, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, EDIT_INVESTMENT_CATEGORY_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EDIT_INVESTMENT_CATEGORY_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
        NAVIGATION_PANEL.add(BACK_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // Returns the investment category IDs for easier editing of investments
    public String[] getInvestmentCategoryIDs() {

        final String ACCOUNT_TABLE_NAME = "InvestmentCategories";
        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "InvestmentCategoryID", "InvestmentCategoryName"};
        Object[] objectCategoryIDsArray;
        String[] stringCategoryIDsArray;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_NAME, ACCOUNT_TABLE_HEADERS);
        
        objectCategoryIDsArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectCategoryIDsArray[i] = tableData[i][1];
        }
        
        stringCategoryIDsArray = Arrays.copyOf(objectCategoryIDsArray, objectCategoryIDsArray.length, String[].class);
        
        return stringCategoryIDsArray;
    }
    
    // actionPerformed method for event-driven programming
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object buttonPressed = e.getSource();
        
        // Checking various button calls
        if(buttonPressed == EDIT_INVESTMENT_CATEGORY_BUTTON) {
            
            int oldInvestmentCategoryID = Integer.parseInt((String) OLD_INVESTMENT_CATEGORY_ID_COMBO_BOX.getSelectedItem());
            String newInvestmentCategoryName = NEW_INVESTMENT_CATEGORY_NAME_FIELD.getText();
            
            if(editInvestmentCategory(oldInvestmentCategoryID, newInvestmentCategoryName)) {
                SuccessFrame successGUI = new SuccessFrame("Investment category edited.");
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Unable to edit investment category");
            }
            
            NEW_INVESTMENT_CATEGORY_NAME_FIELD.setText("");

        }
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    public boolean editInvestmentCategory(int oldInvestmentCategoryID, String newInvestmentCategoryName) {

        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        try {
            PreparedStatement ps;
        
            String editSpendingCategoryQuery = "UPDATE InvestmentCategories SET InvestmentCategoryName = ? WHERE AccountID = ? AND InvestmentCategoryID = ?";
        
            ps = databaseObject.getDatabaseConnection().prepareStatement(editSpendingCategoryQuery);
            ps.setString(1, newInvestmentCategoryName);
            ps.setInt(2, ACCOUNT_ID);
            ps.setInt(3, oldInvestmentCategoryID);
            ps.executeUpdate();

            return true;
        }
        catch(SQLException error) {
            error.printStackTrace();
            return false;
        }
        
    }
    
}
