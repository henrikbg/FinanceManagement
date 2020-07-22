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
import javax.swing.JTextField;

/*
    DepreciationFrame.java
    Frame that allows the user to calculate depreciation
*/

public class DepreciationFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel INPUT_PANEL;
    private final JPanel DEPRECIATION_TIME_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel DEPRECIATION_LABEL;
    private final JLabel INPUT_DESCRIPTION_LABEL;
    private final JLabel INITIAL_AMOUNT_LABEL;
    private final JLabel DEPRECIATION_RATE_LABEL;
    private final JLabel DEPRECIATION_TIME_LABEL;
    private final JLabel DEPRECIATION_FREQUENCY_LABEL;
    private final JLabel FINAL_LABEL;
    private final JLabel FINAL_AMOUNT_LABEL;
    
    private final int AMOUNT_FIELD_COLUMNS;
    private final int RATE_COLUMNS;
    
    private final JTextField INITIAL_AMOUNT_FIELD;
    private final JTextField DEPRECIATION_RATE_FIELD;
    private final JTextField DEPRECIATION_TIME_FIELD;
    
    private final JButton CALCULATE_DEPRECIATION_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final String[] TIMES;
    private final String[] FREQUENCIES;
    
    private final JComboBox<String> INITIAL_AMOUNT_CURRENCY_COMBO_BOX;
    private final JComboBox<String> DEPRECIATION_TIME_COMBO_BOX;
    private final JComboBox<String> DEPRECIATION_FREQUENCY_COMBO_BOX;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    public DepreciationFrame(int accountID) {
        
        // Setting the frame name
        super("Depreciation Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        INPUT_PANEL = new JPanel(new GridBagLayout());
        DEPRECIATION_TIME_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        DEPRECIATION_LABEL = new JLabel("Depreciation");
        INPUT_DESCRIPTION_LABEL = new JLabel("Enter your initial amount, depreciation rate (as a percentage), and time.");
        INITIAL_AMOUNT_LABEL = new JLabel("Initial Amount:");
        DEPRECIATION_RATE_LABEL = new JLabel("Depreciation Rate:");
        DEPRECIATION_TIME_LABEL = new JLabel("Depreciation Time:");
        DEPRECIATION_FREQUENCY_LABEL = new JLabel("Frequency of Depreciation:");
        FINAL_LABEL = new JLabel("Final Amount:");
        FINAL_AMOUNT_LABEL = new JLabel();

        AMOUNT_FIELD_COLUMNS = 13;
        RATE_COLUMNS = 5;
        
        INITIAL_AMOUNT_FIELD = new JTextField(AMOUNT_FIELD_COLUMNS);
        DEPRECIATION_RATE_FIELD = new JTextField(RATE_COLUMNS);
        DEPRECIATION_TIME_FIELD = new JTextField(AMOUNT_FIELD_COLUMNS);
        
        CALCULATE_DEPRECIATION_BUTTON = new JButton("Calculate Depreciation");
        BACK_BUTTON = new JButton("Back");
        
        TIMES = new String[] {"Day(s)", "Week(s)", "Month(s)", "Year(s)"};
        FREQUENCIES = new String[] {"Every Day", "Every Week", "Every Month", "Every Year"};
        
        INITIAL_AMOUNT_CURRENCY_COMBO_BOX = new JComboBox<>(Constants.getCurrencies());
        DEPRECIATION_TIME_COMBO_BOX = new JComboBox<>(TIMES);
        DEPRECIATION_FREQUENCY_COMBO_BOX = new JComboBox<>(FREQUENCIES);
        
        WIDTH = 650;
        HEIGHT = 520;
        
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
        DEPRECIATION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        INPUT_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        INITIAL_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        INITIAL_AMOUNT_FIELD.setFont(Constants.MAIN_FONT);
        INITIAL_AMOUNT_CURRENCY_COMBO_BOX.setFont(Constants.MAIN_FONT);
        DEPRECIATION_RATE_LABEL.setFont(Constants.MAIN_FONT);
        DEPRECIATION_RATE_FIELD.setFont(Constants.MAIN_FONT);
        DEPRECIATION_TIME_LABEL.setFont(Constants.MAIN_FONT);
        DEPRECIATION_TIME_FIELD.setFont(Constants.MAIN_FONT);
        DEPRECIATION_TIME_COMBO_BOX.setFont(Constants.MAIN_FONT);
        DEPRECIATION_FREQUENCY_LABEL.setFont(Constants.MAIN_FONT);
        DEPRECIATION_FREQUENCY_COMBO_BOX.setFont(Constants.MAIN_FONT);
        FINAL_LABEL.setFont(Constants.SUB_HEADER_FONT);
        FINAL_AMOUNT_LABEL.setFont(Constants.MAIN_FONT);
        CALCULATE_DEPRECIATION_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);
        
        CALCULATE_DEPRECIATION_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);

        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, DEPRECIATION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INPUT_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(INPUT_PANEL, INITIAL_AMOUNT_LABEL, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_END);
        Constants.addComponent(INPUT_PANEL, INITIAL_AMOUNT_FIELD, 1, 0, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, INITIAL_AMOUNT_CURRENCY_COMBO_BOX, 2, 0, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_RATE_LABEL, 0, 1, 1, 1, GridBagConstraints.FIRST_LINE_END);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_RATE_FIELD, 1, 1, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_TIME_LABEL, 0, 2, 1, 1, GridBagConstraints.FIRST_LINE_END);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_TIME_FIELD, 1, 2, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_TIME_COMBO_BOX, 2, 2, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_FREQUENCY_LABEL, 0, 3, 1, 1, GridBagConstraints.FIRST_LINE_END);
        Constants.addComponent(INPUT_PANEL, DEPRECIATION_FREQUENCY_COMBO_BOX, 1, 3, 1, 1, GridBagConstraints.FIRST_LINE_START);
        Constants.addComponent(CONTENT_PANEL, INPUT_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, FINAL_LABEL, 0, 3, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(CONTENT_PANEL, FINAL_AMOUNT_LABEL, 0, 4, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(CONTENT_PANEL, CALCULATE_DEPRECIATION_BUTTON, 0, 5, 1, 1, GridBagConstraints.CENTER);
        
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
        if(buttonPressed == CALCULATE_DEPRECIATION_BUTTON) {
            try {
                double initialAmount = Double.parseDouble(INITIAL_AMOUNT_FIELD.getText());
                String currency = (String) INITIAL_AMOUNT_CURRENCY_COMBO_BOX.getSelectedItem();
                double depreciationRate = Double.parseDouble(DEPRECIATION_RATE_FIELD.getText());
                int depreciationTimeAmount = Integer.parseInt(DEPRECIATION_TIME_FIELD.getText());
                String depreciationTime = (String) DEPRECIATION_TIME_COMBO_BOX.getSelectedItem(); 
                String frequencyOfDepreciation = (String) DEPRECIATION_FREQUENCY_COMBO_BOX.getSelectedItem();
                double finalAmount;
                
                if(initialAmount < 0) throw new NegativeNumberException();
                if(depreciationRate < 0) throw new NegativeNumberException();
                if(depreciationRate > 100) throw new RateRangeException();
                if(depreciationTimeAmount < 0) throw new NegativeNumberException();
                
                finalAmount = Depreciation.calculateDepreciation(initialAmount, depreciationRate, depreciationTimeAmount, depreciationTime, frequencyOfDepreciation);
                
                FINAL_AMOUNT_LABEL.setText(finalAmount + " " + currency + " after " + depreciationTimeAmount + " " + depreciationTime);
                
                this.revalidate();
                this.repaint();
                
            }
            catch(NumberFormatException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please enter a valid amount, rate, or time.");
                INITIAL_AMOUNT_FIELD.setText("");
            }
            catch(NegativeNumberException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please enter an amount, rate, or time greater than zero.");
            }
            catch(RateRangeException error) {
                ErrorFrame errorGUI = new ErrorFrame("Please enter a depreciation rate less than 100%.");
            }
        }
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
    }
    
    // Getting the currencies so user can enter different currencies
    public String[] getCurrencies() {

        final String TABLE_NAME = "CurrencyConversion";
        final String[] TABLE_HEADERS = {"Currency", "DollarConversion"};
        Object[] currencyObjectArray;
        String[] currencyStringArray;
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        Object[][] tableData = databaseObject.getData(TABLE_NAME, TABLE_HEADERS);
        
        currencyObjectArray = new Object[tableData.length];
        
        for(int i = 0; i < tableData.length; i++) {
            currencyObjectArray[i] = tableData[i][0];
        }
        
        currencyStringArray = Arrays.copyOf(currencyObjectArray, currencyObjectArray.length, String[].class);
        
        return currencyStringArray;
    }
    
}