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
    SuccessFrame.java
    Displays a window that shows that an action in the application has been performed successfully
*/

public class SuccessFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final JPanel CONTENT_PANEL;
    private final JPanel NAVIGATION_PANEL;

    private final JLabel SUCCESS_LABEL;
    private final JLabel SUCCESS_DESCRIPTION_LABEL;
    
    private final JButton CLOSE_BUTTON;

    public SuccessFrame(String successDescription) {
        
        // Setting the frame name
        super("Success Window");
        
        // Initializing constants and variables
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        SUCCESS_LABEL = new JLabel("Success!");
        SUCCESS_DESCRIPTION_LABEL = new JLabel(successDescription);

        CLOSE_BUTTON = new JButton("Close");
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, Constants.EXTRA_WIDTH, Constants.EXTRA_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        SUCCESS_LABEL.setForeground(Constants.SUCCESS_COLOR);
        
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        SUCCESS_LABEL.setFont(Constants.SUCCESS_FONT);
        SUCCESS_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        CLOSE_BUTTON.setFont(Constants.MAIN_FONT);
        
        CLOSE_BUTTON.addActionListener(this);
        
        SUCCESS_LABEL.setHorizontalAlignment(JLabel.CENTER);
        SUCCESS_DESCRIPTION_LABEL.setHorizontalAlignment(JLabel.CENTER);
        
        Constants.addComponent(CONTENT_PANEL, SUCCESS_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, SUCCESS_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.PAGE_START);
        
        NAVIGATION_PANEL.add(CLOSE_BUTTON);
        
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // Allows for the closure of the window
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String buttonCommand = e.getActionCommand();
        
        if(buttonCommand.equals("Close")) {  
            this.dispose();
        }
        
    }
    
}