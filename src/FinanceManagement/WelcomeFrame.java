//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

/*
    WelcomeFrame.java
    Initial frame the user is directed to when the application has started
*/

public class WelcomeFrame extends JFrame implements ActionListener {

    // declaring all the variables
    private final JPanel TITLE_PANEL;
    private final JPanel BUTTON_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel TITLE_LABEL;
    private final JLabel DESCRIPTION_LABEL;
    
    private final JButton ENTER_BUTTON;
    private final JButton EXIT_BUTTON;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    public WelcomeFrame() {
        
        // initializing the variables
        super("Welcome Window");
        
        TITLE_PANEL = new JPanel(new GridBagLayout());
        BUTTON_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        TITLE_LABEL = new JLabel("Finance Management");
        DESCRIPTION_LABEL = new JLabel("Welcome to my finance management application!");
        
        ENTER_BUTTON = new JButton("Enter");
        EXIT_BUTTON = new JButton("Exit");
        
        WIDTH = 470;
        HEIGHT = 280;
        
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        TITLE_LABEL.setFont(Constants.HEADER_FONT);
        DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        ENTER_BUTTON.setFont(Constants.MAIN_FONT);
        EXIT_BUTTON.setFont(Constants.MAIN_FONT);
        
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        ENTER_BUTTON.addActionListener(this);
        EXIT_BUTTON.addActionListener(this);
        
        Constants.addComponent(TITLE_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(TITLE_PANEL, TITLE_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(TITLE_PANEL, DESCRIPTION_LABEL, 0, 2, 1, 1, GridBagConstraints.PAGE_END);

        BUTTON_PANEL.add(ENTER_BUTTON);
        
        NAVIGATION_PANEL.add(ENTER_BUTTON);
        NAVIGATION_PANEL.add(EXIT_BUTTON);

        this.add(TITLE_PANEL, BorderLayout.NORTH);
        this.add(BUTTON_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);
        
    }
    
    // actionperformed method to proceed onto the next window
    @Override
    public void actionPerformed(ActionEvent e) {
       
        Object buttonPressed = e.getSource();
        
        if(buttonPressed == ENTER_BUTTON) {
            DatabaseInstallationFrame databaseInstallationGUI = new DatabaseInstallationFrame();
            this.dispose();
        }
        if(buttonPressed == EXIT_BUTTON) {  
            System.exit(0); 
        }
        
    }
    
    // main method to start application
    public static void main(String[] args) {
        WelcomeFrame welcomeGUI = new WelcomeFrame();
    }
    
}
