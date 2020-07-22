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
    CreateAccountFrame.java
    Displays a window to create a finance account
*/

public class CreateAccountFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel CREATE_ACCOUNT_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel ACCOUNT_NAME_LABEL;
    private final JLabel ACCOUNT_DESCRIPTION_LABEL;
    
    private final int ACCOUNT_COLUMNS;
    
    private final JTextField ACCOUNT_NAME_FIELD;
    private final JTextField ACCOUNT_DESCRIPTION_FIELD;
    
    private final JButton CREATE_ACCOUNT_BUTTON;
    private final JButton BACK_BUTTON;

    public CreateAccountFrame() {
        
        // Setting the frame name
        super("Create Account Window");
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        CREATE_ACCOUNT_LABEL = new JLabel("Create Account");
        INPUT_DESCRIPTION_LABEL = new JLabel("When creating an account, feel free to enter characters or numbers.");
        ACCOUNT_NAME_LABEL = new JLabel("Account Name:");
        ACCOUNT_DESCRIPTION_LABEL = new JLabel("Account Description:");
        
        ACCOUNT_COLUMNS = 30;
        
        ACCOUNT_NAME_FIELD = new JTextField(ACCOUNT_COLUMNS);
        ACCOUNT_DESCRIPTION_FIELD = new JTextField(ACCOUNT_COLUMNS);
        
        CREATE_ACCOUNT_BUTTON = new JButton("Create");
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
        CREATE_ACCOUNT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        ACCOUNT_NAME_LABEL.setFont(Constants.MAIN_FONT);
        ACCOUNT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        CREATE_ACCOUNT_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        CREATE_ACCOUNT_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, ACCOUNT_NAME_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, ACCOUNT_NAME_FIELD, 1, 0, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(INPUT_PANEL, ACCOUNT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(INPUT_PANEL, ACCOUNT_DESCRIPTION_FIELD, 1, 1, 1, 1, GridBagConstraints.LINE_START);
        Constants.addComponent(CONTENT_PANEL, CREATE_ACCOUNT_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, CREATE_ACCOUNT_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == CREATE_ACCOUNT_BUTTON) {

            String accountNameInput = ACCOUNT_NAME_FIELD.getText();
            String accountDescriptionInput = ACCOUNT_DESCRIPTION_FIELD.getText();

            if(insertAccount(accountNameInput, accountDescriptionInput)) {
                SuccessFrame successGUI = new SuccessFrame("Account added to the database.");
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Please input a valid name or description.");
            }
            
            ACCOUNT_NAME_FIELD.setText("");
            ACCOUNT_DESCRIPTION_FIELD.setText("");
            
        }
        if(buttonPressed == BACK_BUTTON) {
            HomeFrame homeGUI = new HomeFrame();
            this.dispose();
        }
        
    }
    
    // Method to insert account into the database
    public boolean insertAccount(String accountName, String accountDescription) {
        
        final String TABLE_NAME = "AccountNames";
        final String[] TABLE_HEADERS = {"AccountID", "AccountName"};
        final int ID_COLUMN = 0;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        // Only one call to method is needed as primary key for account name and description will be the same
        int primaryKey = Constants.incrementPrimaryKey(TABLE_NAME, TABLE_HEADERS, ID_COLUMN);
        
        try {
            PreparedStatement ps;
        
            String accountNameQuery = "INSERT INTO AccountNames VALUES (?, ?)";
        
            PreparedStatement ps1 = databaseObject.getDatabaseConnection().prepareStatement(accountNameQuery);
            ps1.setInt(1, primaryKey);
            ps1.setString(2, accountName);
            ps1.executeUpdate();
        
            String accountDescriptionQuery = "INSERT INTO AccountDescriptions VALUES (?, ?)";
        
            PreparedStatement ps2 = databaseObject.getDatabaseConnection().prepareStatement(accountDescriptionQuery);
            ps2.setInt(1, primaryKey);
            ps2.setString(2, accountDescription);
            ps2.executeUpdate();
            
            databaseObject.executeQuery("INSERT INTO NetWorth VALUES (" + primaryKey + ", 0)");
            
            return true;
        }
        catch(SQLException error) {
            return false;
        }
        
    }
    
}
