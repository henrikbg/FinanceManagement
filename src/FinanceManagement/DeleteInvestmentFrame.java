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

/*
    DeleteInvestmentFrame.java
    Frame that allows the user to delete an investment
*/

public class DeleteInvestmentFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel DELETE_INVESTMENT_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel INVESTMENT_ID_LABEL;
    
    private final JButton DELETE_INVESTMENT_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final JComboBox<String> INVESTMENT_ID_COMBO_BOX;
    
    public DeleteInvestmentFrame(int accountID) {
        
        // Setting the frame name
        super("Delete Investment Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        DELETE_INVESTMENT_LABEL = new JLabel("Delete Investment");
        INPUT_DESCRIPTION_LABEL = new JLabel("When deleting an investment, select an ID.");
        INVESTMENT_ID_LABEL = new JLabel("Investment ID:");

        DELETE_INVESTMENT_BUTTON = new JButton("Delete Investment");
        BACK_BUTTON = new JButton("Back");
        
        INVESTMENT_ID_COMBO_BOX = new JComboBox<>(getInvestmentIDs());
        
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
        DELETE_INVESTMENT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_ID_LABEL.setFont(Constants.MAIN_FONT);
        INVESTMENT_ID_COMBO_BOX.setFont(Constants.MAIN_FONT);
        DELETE_INVESTMENT_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        DELETE_INVESTMENT_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_ID_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, INVESTMENT_ID_COMBO_BOX, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, DELETE_INVESTMENT_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, DELETE_INVESTMENT_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == DELETE_INVESTMENT_BUTTON) {
            int investmentID = Integer.parseInt((String) INVESTMENT_ID_COMBO_BOX.getSelectedItem());
            
            deleteInvestment(investmentID);
                
            NetWorth netWorthObject = new NetWorth();
                
            netWorthObject.calculateNetWorth(ACCOUNT_ID);
                
            SuccessFrame successGUI = new SuccessFrame("Investment edited in database.");
        }
        
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Returns the investment ids for combo box
    public String[] getInvestmentIDs() {

        final String ACCOUNT_TABLE_NAME = "AccountInvestmentsNames";
        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "InvestmentID", "InvestmentName"};
        Object[] objectCategoryIDsArray;
        String[] stringCategoryIDsArray;
        
        String dbQuery = "SELECT * FROM AccountInvestmentsNames WHERE AccountID = " + ACCOUNT_ID;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_HEADERS, dbQuery);
        
        objectCategoryIDsArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectCategoryIDsArray[i] = tableData[i][1];
        }
        
        stringCategoryIDsArray = Arrays.copyOf(objectCategoryIDsArray, objectCategoryIDsArray.length, String[].class);
        
        return stringCategoryIDsArray;
    }
    
    // Deletes the investment from the database
    public boolean deleteInvestment(int investmentID) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        try {
            // Prepared statements to delete investment from all the tables
            String accountInvestmentsNamesQuery = "DELETE FROM AccountInvestmentsNames WHERE AccountID = ? AND InvestmentID = ?";
        
            PreparedStatement ps1 = databaseObject.getDatabaseConnection().prepareStatement(accountInvestmentsNamesQuery);
            ps1.setInt(1, ACCOUNT_ID);
            ps1.setInt(2, investmentID);
            ps1.executeUpdate();

            String investmentAmountQuery = "DELETE FROM InvestmentAmount WHERE AccountID = ? AND InvestmentID = ?";
            
            PreparedStatement ps2 = databaseObject.getDatabaseConnection().prepareStatement(investmentAmountQuery);
            ps2.setInt(1, ACCOUNT_ID);
            ps2.setInt(2, investmentID);
            ps2.executeUpdate();
            
            String investmentCategoriesToInvestmentsQuery = "DELETE FROM InvestmentCategoriesToInvestments WHERE AccountID = ? AND InvestmentID = ?";
        
            PreparedStatement ps3 = databaseObject.getDatabaseConnection().prepareStatement(investmentCategoriesToInvestmentsQuery);
            ps3.setInt(1, ACCOUNT_ID);
            ps3.setInt(2, investmentID);
            ps3.executeUpdate();
            
            deleteDate(investmentID);
            
            return true;
        }
        catch(SQLException error) {
            System.out.println("Error in deleteInvestment()!");
            error.printStackTrace();
            return false;
        }
        
    }
    
    // Adding a date to the investment trends table
    public void deleteDate(int investmentID) {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);

        String deleteDateQuery = "DELETE FROM InvestmentTrends WHERE AccountID = " + ACCOUNT_ID + " AND InvestmentID = " + investmentID;
        
        databaseObject.executeQuery(deleteDateQuery);
            
        System.out.println("Date has been deleted");
        
    }
    
}
