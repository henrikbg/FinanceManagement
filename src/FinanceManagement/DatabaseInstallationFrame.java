//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
    HomeFrame.java
    JFrame that displays a menu that allows the user to create a new account or select an existing account and proceed
*/

public class DatabaseInstallationFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel DATABASE_INSTALLATION_LABEL;
    private final JLabel STATUS_LABEL;
    private final JLabel STATUS_DESCRIPTION_LABEL;
    
    private final JButton INSTALL_DATABASE_BUTTON;
    private final JButton CONTINUE_BUTTON;
    private final JButton BACK_BUTTON;

    private final int WIDTH;
    private final int HEIGHT;

    public DatabaseInstallationFrame() {
        
        // Setting the frame name
        super("Database Installation Window");
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        DATABASE_INSTALLATION_LABEL = new JLabel("Database Installation");
        STATUS_LABEL = new JLabel();
        STATUS_DESCRIPTION_LABEL = new JLabel();
        
        INSTALL_DATABASE_BUTTON = new JButton("Install Database");
        CONTINUE_BUTTON = new JButton("Continue");
        BACK_BUTTON = new JButton("Back");

        WIDTH = 650;
        HEIGHT = 410;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        // Checking if the status for the database is installed or not installed
        if(getStatus()) {
            STATUS_LABEL.setText("Database already installed!");
            STATUS_DESCRIPTION_LABEL.setText("Press continue to enter the application.");
            STATUS_LABEL.setForeground(Constants.SUCCESS_COLOR);
            STATUS_LABEL.setFont(Constants.SUCCESS_FONT);
            INSTALL_DATABASE_BUTTON.setEnabled(false);
        } else {
            STATUS_LABEL.setText("Database not installed!");
            STATUS_DESCRIPTION_LABEL.setText("Press the button below to install the database.");
            STATUS_LABEL.setForeground(Constants.ERROR_COLOR);
            STATUS_LABEL.setFont(Constants.ERROR_FONT);
            CONTINUE_BUTTON.setEnabled(false);
        }
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        DATABASE_INSTALLATION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        STATUS_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INSTALL_DATABASE_BUTTON.setFont(Constants.MAIN_FONT);
        CONTINUE_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);

        INSTALL_DATABASE_BUTTON.addActionListener(this);
        CONTINUE_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, DATABASE_INSTALLATION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, STATUS_LABEL, 0, 1, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, STATUS_DESCRIPTION_LABEL, 0, 2, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(CONTENT_PANEL, INSTALL_DATABASE_BUTTON, 0, 3, 1, 1, GridBagConstraints.CENTER);

        NAVIGATION_PANEL.add(CONTINUE_BUTTON);
        NAVIGATION_PANEL.add(BACK_BUTTON);
        
        this.add(HEADER_PANEL, BorderLayout.NORTH);
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);
        
    }
    
    // Returns if the user has database installed or not
    public boolean getStatus() {
        DatabaseAccess databaseObject = new DatabaseAccess();
        return databaseObject.databaseExists(Constants.DATABASE_NAME);
    }
    
    // actionPerformed to receive button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Getting the identifier of the button pressed
        Object buttonPressed = e.getSource();
        
        // Checking various button calls
        if(buttonPressed == INSTALL_DATABASE_BUTTON) {
            DatabaseInstallation installDatabaseObject = new DatabaseInstallation();
            HomeFrame homeGUI = new HomeFrame();
            SuccessFrame successGUI = new SuccessFrame("Database created.");
            this.dispose();
        }
        if(buttonPressed == CONTINUE_BUTTON) {
            HomeFrame homeGUI = new HomeFrame();
            this.dispose();
        }
        if(buttonPressed == BACK_BUTTON) {
            WelcomeFrame welcomeGUI = new WelcomeFrame();
            this.dispose();
        }
        
    }
    
}