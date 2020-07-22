//package FinanceManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;   
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
    CurrencyConversionFrame.java
    Frame that allows the user to convert different currencies
*/

public class CurrencyConversionFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel CURRENCY_CONVERSION_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel INITIAL_CURRENCY_LABEL;
    private final JLabel INITIAL_AMOUNT_LABEL;
    private final JLabel FINAL_CURRENCY_LABEL;
    private final JLabel FINAL_LABEL;
    private final JLabel FINAL_AMOUNT_LABEL;
    
    private final int AMOUNT_FIELD_COLUMNS;
    
    private final JTextField INITIAL_AMOUNT_FIELD;
    
    private final JButton CONVERT_CURRENCIES_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final JComboBox<String> INITIAL_CURRENCY_COMBO_BOX;
    private final JComboBox<String> FINAL_CURRENCY_COMBO_BOX;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    public CurrencyConversionFrame(int accountID) {
        
        // Setting the frame name
        super("Currency Conversion Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        CURRENCY_CONVERSION_LABEL = new JLabel("Currency Conversion");
        INPUT_DESCRIPTION_LABEL = new JLabel("Enter your initial currency and amount, as well as your desired final currency.");
        INITIAL_CURRENCY_LABEL = new JLabel("Initial Currency:");
        INITIAL_AMOUNT_LABEL = new JLabel("Initial Amount:");
        FINAL_CURRENCY_LABEL = new JLabel("Final Currency:");
        FINAL_LABEL = new JLabel("Final Amount:");
        FINAL_AMOUNT_LABEL = new JLabel();

        AMOUNT_FIELD_COLUMNS = 13;
        
        INITIAL_AMOUNT_FIELD = new JTextField(AMOUNT_FIELD_COLUMNS);
        
        CONVERT_CURRENCIES_BUTTON = new JButton("Convert Currencies");
        BACK_BUTTON = new JButton("Back");
        
        INITIAL_CURRENCY_COMBO_BOX = new JComboBox<>(Constants.getCurrencies());
        FINAL_CURRENCY_COMBO_BOX = new JComboBox<>(Constants.getCurrencies());
        
        WIDTH = 650;
        HEIGHT = 490;
        
        // Setting up the frame
        this.setBounds(Constants.HORIZONTAL_DISPLACEMENT, Constants.VERTICAL_DISPLACEMENT, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        HEADER_PANEL.setBackground(Constants.HEADER_BACKGROUND_COLOR);
        
        HEADER_PANEL.setBorder(Constants.HEADER_BORDER);
        NAVIGATION_PANEL.setBorder(Constants.NAVIGATION_BORDER);
        
        FINANCE_ICON_LABEL.setIcon(Constants.FINANCE_ICON);
        
        HEADER_LABEL.setFont(Constants.HEADER_FONT);
        CURRENCY_CONVERSION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INITIAL_CURRENCY_LABEL.setFont(Constants.MAIN_FONT);
        INITIAL_CURRENCY_COMBO_BOX.setFont(Constants.MAIN_FONT);
        INITIAL_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        INITIAL_AMOUNT_FIELD.setFont(Constants.MAIN_FONT);
        FINAL_CURRENCY_LABEL.setFont(Constants.MAIN_FONT);
        FINAL_CURRENCY_COMBO_BOX.setFont(Constants.MAIN_FONT);
        FINAL_LABEL.setFont(Constants.SUB_HEADER_FONT);
        FINAL_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        CONVERT_CURRENCIES_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        CONVERT_CURRENCIES_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, CURRENCY_CONVERSION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, INITIAL_CURRENCY_LABEL, 0, 0, 1, 1, GridBagConstraints.LAST_LINE_END);
        Constants.addComponent(INPUT_PANEL, INITIAL_CURRENCY_COMBO_BOX, 1, 0, 1, 1, GridBagConstraints.LAST_LINE_START);
        Constants.addComponent(INPUT_PANEL, INITIAL_AMOUNT_LABEL, 0, 2, 1, 1, GridBagConstraints.FIRST_LINE_END);
        Constants.addComponent(INPUT_PANEL, INITIAL_AMOUNT_FIELD, 1, 2, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, FINAL_CURRENCY_LABEL, 0, 3, 1, 1, GridBagConstraints.LAST_LINE_END);
        Constants.addComponent(INPUT_PANEL, FINAL_CURRENCY_COMBO_BOX, 1, 3, 1, 1, GridBagConstraints.LAST_LINE_START);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, FINAL_LABEL, 0, 3, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, FINAL_AMOUNT_LABEL, 0, 4, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(CONTENT_PANEL, CONVERT_CURRENCIES_BUTTON, 0, 5, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == CONVERT_CURRENCIES_BUTTON) {
            try {
                double initialAmount = Double.parseDouble(INITIAL_AMOUNT_FIELD.getText());
                String initialCurrency = (String) INITIAL_CURRENCY_COMBO_BOX.getSelectedItem();
                String finalCurrency = (String) FINAL_CURRENCY_COMBO_BOX.getSelectedItem();
                double finalAmount;
                
                if(initialAmount < 0) throw new NegativeNumberException();
                
                finalAmount = CurrencyConversion.convertToCurrency(initialCurrency, finalCurrency, initialAmount);
                
                FINAL_AMOUNT_LABEL.setText(finalAmount + " " + finalCurrency);
                
                this.revalidate();
                this.repaint();
                
            }
            catch(NumberFormatException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please enter a valid amount.");
                INITIAL_AMOUNT_FIELD.setText("");
            }
            catch(NegativeNumberException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please enter an amount greater than zero.");
            }
        }
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
}