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
import javax.swing.JTextArea;

/*
    HelpFrame.java
    Displays a window with information on how the application is used
*/

public class HelpFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel GENERAL_PANEL;
    private final JPanel LAYOUT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel HELP_LABEL;
    private final JLabel GENERAL_LABEL;
    private final JLabel LAYOUT_LABEL;
    private final JLabel INPUT_LABEL;
    
    private final JTextArea GENERAL_TEXT;
    private final JTextArea LAYOUT_TEXT;
    private final JTextArea INPUT_TEXT;
    
    private final JButton BACK_BUTTON;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    private final int TEXT_COLUMNS;

    public HelpFrame(int accountID) {
        
        // Setting the frame name
        super("Help Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        GENERAL_PANEL = new JPanel(new GridBagLayout());
        LAYOUT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        HELP_LABEL = new JLabel("Help");
        GENERAL_LABEL = new JLabel("General");
        LAYOUT_LABEL = new JLabel("Layout");
        INPUT_LABEL = new JLabel("Input");
        
        GENERAL_TEXT = new JTextArea("This application was created in order to easier interpret personal financials. This was achieved through the use of tables and graphs that visually show where money is being put towards.");
        LAYOUT_TEXT = new JTextArea("Every window has a navigation bar at the bottom which allows you to easily transition between windows. The overall layout of the application has been designed to easily view all the financials.");
        INPUT_TEXT = new JTextArea("The application was created with robustness in mind, meaning that all invalid inputs will result in an error window rather than an application crash.");
        
        BACK_BUTTON = new JButton("Back");
        
        WIDTH = 930;
        HEIGHT = 390;
        
        TEXT_COLUMNS = 20;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        GENERAL_TEXT.setBackground(this.getBackground());
        LAYOUT_TEXT.setBackground(this.getBackground());
        INPUT_TEXT.setBackground(this.getBackground());
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        HELP_LABEL.setFont(Constants.SUB_HEADER_FONT);
        GENERAL_LABEL.setFont(Constants.SUB_HEADER_FONT);
        LAYOUT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_LABEL.setFont(Constants.SUB_HEADER_FONT);
        GENERAL_TEXT.setFont(Constants.MAIN_FONT);
        LAYOUT_TEXT.setFont(Constants.MAIN_FONT);
        INPUT_TEXT.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        GENERAL_TEXT.setColumns(TEXT_COLUMNS);
        LAYOUT_TEXT.setColumns(TEXT_COLUMNS);
        INPUT_TEXT.setColumns(TEXT_COLUMNS);
        GENERAL_TEXT.setEditable(false);
        LAYOUT_TEXT.setEditable(false);
        INPUT_TEXT.setEditable(false);
        GENERAL_TEXT.setLineWrap(true);
        LAYOUT_TEXT.setLineWrap(true);
        INPUT_TEXT.setLineWrap(true);
        GENERAL_TEXT.setWrapStyleWord(true);
        LAYOUT_TEXT.setWrapStyleWord(true);
        INPUT_TEXT.setWrapStyleWord(true);
        
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, HELP_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(GENERAL_PANEL, GENERAL_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(GENERAL_PANEL, GENERAL_TEXT, 0, 1, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(LAYOUT_PANEL, LAYOUT_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(LAYOUT_PANEL, LAYOUT_TEXT, 0, 1, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(INPUT_PANEL, INPUT_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(INPUT_PANEL, INPUT_TEXT, 0, 1, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(CONTENT_PANEL, GENERAL_PANEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, LAYOUT_PANEL, 1, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 2, 1, 1, 1, GridBagConstraints.CENTER);

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
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
}
