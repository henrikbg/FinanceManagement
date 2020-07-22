//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
    HomeFrame.java
    JFrame that displays a menu that allows the user to create a new account or select an existing account and proceed
*/

public class HomeFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel HOME_LABEL;
    
    private final JTextArea HOME_DESCRIPTION_TEXT;
    private final JTextArea ACCOUNTS_DESCRIPTION_TEXT;
    
    private final JButton CREATE_ACCOUNT_BUTTON;
    private final JButton GO_BUTTON;
    private final JButton BACK_BUTTON;
    private final JButton EXIT_BUTTON;

    private final JComboBox<String> ACCOUNT_COMBO_BOX;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    private final int TEXT_COLUMNS;

    public HomeFrame() {
        
        // Setting the frame name
        super("Home Window");
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        HOME_LABEL = new JLabel("Home");
        
        HOME_DESCRIPTION_TEXT = new JTextArea("Welcome! To create a new account, press the create account button below.");
        ACCOUNTS_DESCRIPTION_TEXT = new JTextArea("If you have an existing account, select one from the list below and press go.");
        
        CREATE_ACCOUNT_BUTTON = new JButton("Create Account");
        GO_BUTTON = new JButton("Go");
        BACK_BUTTON = new JButton("Back");
        EXIT_BUTTON = new JButton("Exit");

        ACCOUNT_COMBO_BOX = new JComboBox<>(getAccounts());
        
        WIDTH = 650;
        HEIGHT = 410;
        
        TEXT_COLUMNS = 30;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        HOME_DESCRIPTION_TEXT.setBackground(this.getBackground());
        ACCOUNTS_DESCRIPTION_TEXT.setBackground(this.getBackground());
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        HOME_LABEL.setFont(Constants.SUB_HEADER_FONT);
        HOME_DESCRIPTION_TEXT.setFont(Constants.MAIN_FONT);
        ACCOUNTS_DESCRIPTION_TEXT.setFont(Constants.MAIN_FONT);
        ACCOUNT_COMBO_BOX.setFont(Constants.MAIN_FONT);
        CREATE_ACCOUNT_BUTTON.setFont(Constants.MAIN_FONT);
        GO_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        EXIT_BUTTON.setFont(Constants.MAIN_FONT);
        
        HOME_DESCRIPTION_TEXT.setColumns(TEXT_COLUMNS);
        ACCOUNTS_DESCRIPTION_TEXT.setColumns(TEXT_COLUMNS);
        HOME_DESCRIPTION_TEXT.setEditable(false);
        ACCOUNTS_DESCRIPTION_TEXT.setEditable(false);
        HOME_DESCRIPTION_TEXT.setLineWrap(true);
        ACCOUNTS_DESCRIPTION_TEXT.setLineWrap(true);
        HOME_DESCRIPTION_TEXT.setWrapStyleWord(true);
        ACCOUNTS_DESCRIPTION_TEXT.setWrapStyleWord(true);
        
        CREATE_ACCOUNT_BUTTON.addActionListener(this);
        GO_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        EXIT_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, HOME_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, HOME_DESCRIPTION_TEXT, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, CREATE_ACCOUNT_BUTTON, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, ACCOUNTS_DESCRIPTION_TEXT, 0, 3, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, ACCOUNT_COMBO_BOX, 0, 4, 1, 1, GridBagConstraints.CENTER);

        NAVIGATION_PANEL.add(GO_BUTTON);
        NAVIGATION_PANEL.add(BACK_BUTTON);
        NAVIGATION_PANEL.add(EXIT_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);
        
    }
    
    // Receiving accounts from database
    public String[] getAccounts() {

        final String ACCOUNT_TABLE_NAME = "AccountNames";
        final String[] ACCOUNT_TABLE_HEADERS = {"AccountID", "AccountName"};
        Object[] objectAccountsArray;
        String[] stringAccountsArray;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(ACCOUNT_TABLE_NAME, ACCOUNT_TABLE_HEADERS);
        
        objectAccountsArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            objectAccountsArray[i] = tableData[i][1];
        }
        
        stringAccountsArray = Arrays.copyOf(objectAccountsArray, objectAccountsArray.length, String[].class);
        
        return stringAccountsArray;
        
    }
    
    // Returns the account ID for the home frame
    public int getAccountID() {
        
        final String TABLE_HEADER = "AccountID";
        String selectedAccount = String.valueOf(ACCOUNT_COMBO_BOX.getSelectedItem());
        int accountID;
        String accountIDQuery = "SELECT " + TABLE_HEADER + " FROM AccountNames WHERE AccountName = '" + selectedAccount + "'";
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        accountID = (Integer) databaseObject.getSpecificData(accountIDQuery, TABLE_HEADER);
        
        return accountID;
        
    }
    
    // Actionperformed to see button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Getting the identifier of the button pressed
        Object buttonPressed = e.getSource();
        
        // Checking various button calls
        if(buttonPressed == CREATE_ACCOUNT_BUTTON) {
            CreateAccountFrame createAccountGUI = new CreateAccountFrame();
            this.dispose();
        }
        if(buttonPressed == GO_BUTTON) {
            DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
            String accountExistsQuery = "SELECT * FROM AccountNames";

            if(databaseObject.dataExists(accountExistsQuery)) {
                MainFrame mainGUI = new MainFrame(getAccountID());
                this.dispose();
            } else {
                ErrorFrame errorGUI = new ErrorFrame("Please create an account before continuing!");
            }
        }
        if(buttonPressed == BACK_BUTTON) {
            WelcomeFrame welcomeGUI = new WelcomeFrame();
            this.dispose();
        }
        if(buttonPressed == EXIT_BUTTON) {  
            System.exit(0); 
        }
        
    }
    
}