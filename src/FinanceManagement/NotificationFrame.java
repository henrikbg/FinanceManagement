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
    NotificationFrame.java
    Displays a window that shows that an error occurred when performing an action in the application
*/

public class NotificationFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final JPanel CONTENT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel NOTIFICATION_LABEL;
    private final JLabel NOTIFICATION_DESCRIPTION_LABEL;
    
    private final JButton CLOSE_BUTTON;

    private final int HORIZONTAL_DISPLACEMENT;
    private final int VERTICAL_DISPLACEMENT;
    
    public NotificationFrame(String notifificationDescription) {
        
        // Setting the frame name
        super("Notification Window");
        
        // Initializing constants and variables
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        NOTIFICATION_LABEL = new JLabel("Notification");
        NOTIFICATION_DESCRIPTION_LABEL = new JLabel(notifificationDescription);

        CLOSE_BUTTON = new JButton("Close");
        
        HORIZONTAL_DISPLACEMENT = 430;
        VERTICAL_DISPLACEMENT = 230;
        
        // Setting up the frame
        this.setBounds(HORIZONTAL_DISPLACEMENT, VERTICAL_DISPLACEMENT, Constants.EXTRA_WIDTH, Constants.EXTRA_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        NOTIFICATION_LABEL.setForeground(Constants.ERROR_COLOR);
        
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        NOTIFICATION_LABEL.setFont(Constants.ERROR_FONT);
        NOTIFICATION_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        CLOSE_BUTTON.setFont(Constants.MAIN_FONT);
        
        CLOSE_BUTTON.addActionListener(this);
        
        NOTIFICATION_LABEL.setHorizontalAlignment(JLabel.CENTER);
        NOTIFICATION_DESCRIPTION_LABEL.setHorizontalAlignment(JLabel.CENTER);

        Constants.addComponent(CONTENT_PANEL, NOTIFICATION_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, NOTIFICATION_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.PAGE_START);
        
        NAVIGATION_PANEL.add(CLOSE_BUTTON);
        
        this.add(CONTENT_PANEL, BorderLayout.CENTER);
        this.add(NAVIGATION_PANEL, BorderLayout.SOUTH);
        
        this.setVisible(true);

    }
    
    // Allows for the closure of the notification
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String buttonCommand = e.getActionCommand();
        
        if(buttonCommand.equals("Close")) {  
            this.dispose();
        }
        
    }
    
}