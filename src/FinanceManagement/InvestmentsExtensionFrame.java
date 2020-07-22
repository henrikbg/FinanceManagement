//package FinanceManagement;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;   
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
    InvestmentsExtensionFrame.java
    Frame to show the investments graph and table in more detail
*/

public class InvestmentsExtensionFrame extends JFrame implements ActionListener {

    // Declaring constants and variables
    private final int ACCOUNT_ID;
    
    private final JPanel HEADER_PANEL;
    private final JPanel CONTENT_PANEL;
    private final JPanel FILTER_PANEL;
    private final JPanel EXTENSION_PANEL;
    private final JPanel NAVIGATION_PANEL;
    
    private final JLabel FINANCE_ICON_LABEL;
    private final JLabel HEADER_LABEL;
    private final JLabel INVESTMENTS_EXTENSION_LABEL;
    private final JLabel EXTENSION_DESCRIPTION_LABEL;
    private final JLabel FILTER_LABEL;
    
    private final JButton FILTER_BUTTON;
    private final JButton BACK_BUTTON;
    
    private final String[] INVESTMENTS_TABLE_HEADERS;
    
    private final JTable INVESTMENTS_TABLE;
    
    private final JComboBox FILTER_COMBO_BOX;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    private boolean investmentSelected;
    
    private int selectedInvestment;
    
    public InvestmentsExtensionFrame(int accountID) {
        
        // Setting the frame name
        super("Investments Extension Window");
        
        ACCOUNT_ID = accountID;
        
        // Initializing constants and variables
        HEADER_PANEL = new JPanel(new GridBagLayout());
        CONTENT_PANEL = new JPanel(new GridBagLayout());
        FILTER_PANEL = new JPanel(new GridBagLayout());
        EXTENSION_PANEL = new JPanel(new GridBagLayout());
        NAVIGATION_PANEL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        FINANCE_ICON_LABEL = new JLabel();
        HEADER_LABEL = new JLabel("Finance Management");
        INVESTMENTS_EXTENSION_LABEL = new JLabel("Investments Extension");
        EXTENSION_DESCRIPTION_LABEL = new JLabel("Below is a scatter plot with the investment trend of a certain investment.");
        FILTER_LABEL = new JLabel("Filter by Investment:");
        
        FILTER_BUTTON = new JButton("Filter");
        BACK_BUTTON = new JButton("Back");
        
        INVESTMENTS_TABLE_HEADERS = new String[] {"Investment ID","Investment Name","Investment Category","Investment Amount"};

        INVESTMENTS_TABLE = new JTable(getInvestmentsTableRecords(), INVESTMENTS_TABLE_HEADERS);
        
        FILTER_COMBO_BOX = new JComboBox<>(getOldInvestmentIDs());
        
        WIDTH = 1100;
        HEIGHT = 800;
        
        investmentSelected = false;
        
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
        INVESTMENTS_EXTENSION_LABEL.setFont(Constants.SUB_HEADER_FONT);
        EXTENSION_DESCRIPTION_LABEL.setFont(Constants.MAIN_FONT);
        FILTER_LABEL.setFont(Constants.MAIN_FONT);
        FILTER_COMBO_BOX.setFont(Constants.MAIN_FONT);
        FILTER_BUTTON.setFont(Constants.MAIN_FONT);
        BACK_BUTTON.setFont(Constants.MAIN_FONT);

        INVESTMENTS_TABLE.setEnabled(false);
        INVESTMENTS_TABLE.setGridColor(Color.LIGHT_GRAY);
        
        FILTER_BUTTON.addActionListener(this);
        BACK_BUTTON.addActionListener(this);
        
        Constants.addComponent(HEADER_PANEL, FINANCE_ICON_LABEL, 0, 0, 1, 1, GridBagConstraints.LINE_END);
        Constants.addComponent(HEADER_PANEL, HEADER_LABEL, 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 3, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 4, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 5, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 6, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(HEADER_PANEL, new JLabel(" "), 7, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, INVESTMENTS_EXTENSION_LABEL, 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_DESCRIPTION_LABEL, 0, 1, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(FILTER_PANEL, FILTER_LABEL, 0, 0, 1, 1, GridBagConstraints.PAGE_END);
        Constants.addComponent(FILTER_PANEL, FILTER_COMBO_BOX, 1, 0, 1, 1, GridBagConstraints.PAGE_START);
        Constants.addComponent(FILTER_PANEL, FILTER_BUTTON, 2, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, FILTER_PANEL, 0, 2, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new JScrollPane(INVESTMENTS_TABLE), 0, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(EXTENSION_PANEL, new GraphPanel(), 1, 0, 1, 1, GridBagConstraints.CENTER);
        Constants.addComponent(CONTENT_PANEL, EXTENSION_PANEL, 0, 3, 1, 1, GridBagConstraints.CENTER);

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
        
        if(buttonPressed == BACK_BUTTON) {
            MainFrame mainGUI = new MainFrame(ACCOUNT_ID);
            this.dispose();
        }
        
        if(buttonPressed == FILTER_BUTTON) {
            
            int idSelected = Integer.parseInt(FILTER_COMBO_BOX.getSelectedItem().toString());
            
            investmentSelected = true;
            selectedInvestment = idSelected;
            
            this.repaint();
        }
        
    }
    
    // For combo box to edit the old IDs
    public String[] getOldInvestmentIDs() {

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
    
    // Subclass for the graph panel that includes the scatter plot graphics
    public class GraphPanel extends JPanel {
        
        // Overriding the dimensions of the JPanel
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }
        
        // Paint component to allow for 2DGraphics
        @Override
        public void paintComponent(Graphics g) {
            
            // Delcaring variables and creating 2DGraphics object
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            
            final int GRAPH_HEIGHT = 400;
            final int GRAPH_WIDTH = 400;
            final int POINT_HEIGHT = 10;
            final int POINT_WIDTH = 10;
            final int PADDING = 50;
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            final Stroke DEFAULT_STROKE = g2d.getStroke();
            final float[] DASH = {4f, 4f, 4f};
            final BasicStroke DASH_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, DASH, 2f );
            final Font DEFAULT_FONT = g2d.getFont();
            
            final Color BACKGROUND_COLOR = new Color(255, 255, 255);
            final Color BORDER_COLOR = new Color(0, 0, 0);
            final Color GRID_COLOR = new Color(192, 192, 192);
            final Color POINT_COLOR = new Color(0, 0, 0);
            final Color LINE_COLOR = new Color(0, 0, 0);
            final Color STRING_COLOR = new Color(0, 0, 0);
            
            int verticalLineSeparation;
            int maximumY = 0;
            int middleY = 0;
            int minimumY = 2147483647;
            
            // Drawing graph background
            g2d.setColor(BACKGROUND_COLOR);
            g2d.fillRect(PADDING, PADDING, GRAPH_HEIGHT, GRAPH_WIDTH);
            
            // Drawing graph border
            g2d.setColor(BORDER_COLOR);
            g2d.setStroke(DEFAULT_STROKE);
            g2d.setColor(BORDER_COLOR);
            g2d.drawRect(PADDING, PADDING, GRAPH_HEIGHT, GRAPH_WIDTH);
            
            if(investmentSelected) {
                
                ArrayList<Point> graphPoints = ScatterPlotPoints.calculateGraphPoints(ACCOUNT_ID, selectedInvestment);
                
                if(graphPoints.size() == 1) {
                    g2d.setColor(Color.RED);
                    g2d.drawString("Please update your investment to add a trend!", PADDING + 20, PADDING + 150);
                } else {
                    if(graphPoints.size() == 2) {
                        g2d.setColor(STRING_COLOR);
                        g2d.drawString("Most Recent", PADDING - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Current", PADDING + GRAPH_WIDTH - 25, PADDING + GRAPH_HEIGHT + 15);
                    }
                    if(graphPoints.size() == 3) {
                        g2d.setColor(GRID_COLOR);
                        g2d.setStroke(DASH_STROKE);
                        verticalLineSeparation = GRAPH_WIDTH / 2;
                        g2d.drawLine(PADDING + verticalLineSeparation, PADDING, PADDING + verticalLineSeparation, PADDING + GRAPH_HEIGHT);
                        g2d.setColor(STRING_COLOR);
                        g2d.drawString("2nd Most Recent", PADDING - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Most Recent", PADDING + verticalLineSeparation - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Current", PADDING + GRAPH_WIDTH - 25, PADDING + GRAPH_HEIGHT + 15);
                    }
                    if(graphPoints.size() == 4) {
                        g2d.setColor(GRID_COLOR);
                        g2d.setStroke(DASH_STROKE);
                        verticalLineSeparation = GRAPH_WIDTH / 3;
                        g2d.drawLine(PADDING + verticalLineSeparation, PADDING, PADDING + verticalLineSeparation, PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), PADDING, PADDING + (verticalLineSeparation * 2), PADDING + GRAPH_HEIGHT);
                        g2d.setColor(STRING_COLOR);
                        g2d.drawString("3rd Most Recent", PADDING - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("2nd Most Recent", PADDING + verticalLineSeparation - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Most Recent", PADDING + (verticalLineSeparation * 2) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Current", PADDING + GRAPH_WIDTH - 25, PADDING + GRAPH_HEIGHT + 15);
                    }
                    if(graphPoints.size() == 5) {
                        g2d.setColor(GRID_COLOR);
                        g2d.setStroke(DASH_STROKE);
                        verticalLineSeparation = GRAPH_WIDTH / 4;
                        g2d.drawLine(PADDING + verticalLineSeparation, PADDING, PADDING + verticalLineSeparation, PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), PADDING, PADDING + (verticalLineSeparation * 2), PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 3), PADDING, PADDING + (verticalLineSeparation * 3), PADDING + GRAPH_HEIGHT);
                        g2d.setColor(STRING_COLOR);
                        g2d.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
                        g2d.drawString("4th Most Recent", PADDING - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("3rd Most Recent", PADDING + verticalLineSeparation - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("2nd Most Recent", PADDING + (verticalLineSeparation * 2) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Most Recent", PADDING + (verticalLineSeparation * 3) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Current", PADDING + GRAPH_WIDTH - 25, PADDING + GRAPH_HEIGHT + 15);
                    }
                    if(graphPoints.size() == 6) {
                        g2d.setColor(GRID_COLOR);
                        g2d.setStroke(DASH_STROKE);
                        verticalLineSeparation = GRAPH_WIDTH / 5;
                        g2d.drawLine(PADDING + verticalLineSeparation, PADDING, PADDING + verticalLineSeparation, PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), PADDING, PADDING + (verticalLineSeparation * 2), PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 3), PADDING, PADDING + (verticalLineSeparation * 3), PADDING + GRAPH_HEIGHT);
                        g2d.drawLine(PADDING + (verticalLineSeparation * 4), PADDING, PADDING + (verticalLineSeparation * 4), PADDING + GRAPH_HEIGHT);
                        g2d.setColor(STRING_COLOR);
                        g2d.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
                        g2d.drawString("5th Most Recent", PADDING - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("4th Most Recent", PADDING + verticalLineSeparation - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("3rd Most Recent", PADDING + (verticalLineSeparation * 2) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("2nd Most Recent", PADDING + (verticalLineSeparation * 3) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Most Recent", PADDING + (verticalLineSeparation * 4) - 25, PADDING + GRAPH_HEIGHT + 15);
                        g2d.drawString("Current", PADDING + GRAPH_WIDTH - 25, PADDING + GRAPH_HEIGHT + 15);
                    }
                    
                    g2d.setColor(GRID_COLOR);
                    g2d.setStroke(DASH_STROKE);
                    g2d.drawLine(PADDING, PADDING + (GRAPH_HEIGHT / 2), PADDING + GRAPH_WIDTH, PADDING + (GRAPH_HEIGHT / 2));
                    
                    for(int i = 0; i < graphPoints.size(); i++) {
                        if(graphPoints.get(i).y > maximumY) {
                            maximumY = graphPoints.get(i).y;
                        }
                        if(graphPoints.get(i).y < minimumY) {
                            minimumY = graphPoints.get(i).y;
                        }
                    }
                    
                    middleY = (maximumY + minimumY) / 2;
                    
                    g2d.setFont(DEFAULT_FONT);
                    System.out.println(DEFAULT_FONT);
                    g2d.setColor(STRING_COLOR);
                    g2d.drawString("$" + maximumY, 0, PADDING);
                    g2d.drawString("$" + middleY, 0, PADDING + (GRAPH_HEIGHT / 2));
                    g2d.drawString("$" + minimumY, 0, PADDING + GRAPH_HEIGHT);
                    
                    System.out.println(graphPoints);
                    
                    g2d.setStroke(DEFAULT_STROKE);
                    
                    if(graphPoints.size() == 2) {
                        g2d.fillOval(PADDING - 5, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + GRAPH_WIDTH - 5, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        
                        g2d.drawLine(PADDING, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY), PADDING + GRAPH_WIDTH, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY));
                    }
                    if(graphPoints.size() == 3) {
                        verticalLineSeparation = GRAPH_WIDTH / 2;
                        g2d.fillOval(PADDING - 5, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + verticalLineSeparation - 5, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + GRAPH_WIDTH - 5, calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        
                        g2d.drawLine(PADDING, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY), PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY), PADDING + GRAPH_WIDTH, calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY));
                    }
                    if(graphPoints.size() == 4) {
                        verticalLineSeparation = GRAPH_WIDTH / 3;
                        g2d.fillOval(PADDING - 5, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + verticalLineSeparation - 5, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 2) - 5, calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + GRAPH_WIDTH - 5, calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        
                        g2d.drawLine(PADDING, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY), PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY), PADDING + GRAPH_WIDTH, calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY));
                    }
                    if(graphPoints.size() == 5) {
                        verticalLineSeparation = GRAPH_WIDTH / 4;
                        g2d.fillOval(PADDING - 5, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + verticalLineSeparation - 5, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 2) - 5, calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 3) - 5, calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + GRAPH_WIDTH - 5, calculatePointPercent(graphPoints.get(4).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        
                        g2d.drawLine(PADDING, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY), PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 3), calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 3), calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY), PADDING + GRAPH_WIDTH, calculatePointPercent(graphPoints.get(4).y, maximumY, minimumY));
                    }
                    if(graphPoints.size() == 6) {
                        verticalLineSeparation = GRAPH_WIDTH / 5;
                        g2d.fillOval(PADDING - 5, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + verticalLineSeparation - 5, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 2) - 5, calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 3) - 5, calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + (verticalLineSeparation * 4) - 5, calculatePointPercent(graphPoints.get(4).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        g2d.fillOval(PADDING + GRAPH_WIDTH - 5, calculatePointPercent(graphPoints.get(5).y, maximumY, minimumY) - 5, POINT_WIDTH, POINT_HEIGHT);
                        
                        g2d.drawLine(PADDING, calculatePointPercent(graphPoints.get(0).y, maximumY, minimumY), PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + verticalLineSeparation, calculatePointPercent(graphPoints.get(1).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 2), calculatePointPercent(graphPoints.get(2).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 3), calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 3), calculatePointPercent(graphPoints.get(3).y, maximumY, minimumY), PADDING + (verticalLineSeparation * 4), calculatePointPercent(graphPoints.get(4).y, maximumY, minimumY));
                        g2d.drawLine(PADDING + (verticalLineSeparation * 4), calculatePointPercent(graphPoints.get(4).y, maximumY, minimumY), PADDING + GRAPH_WIDTH, calculatePointPercent(graphPoints.get(5).y, maximumY, minimumY));
                    }
                    
                }
                
            } else {
                g2d.setColor(Color.RED);
                g2d.drawString("Please select an investment to calculate trend graph!", PADDING + 20, PADDING + 150);
            }
            
        }
        
    }
    
    // Returns the investment table records for the JTable
    public String[][] getInvestmentsTableRecords() {
        
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        
        String[][] investmentsTableRecords;
        int numberOfInvestments;
        
        String[] tableHeaders1 = {"InvestmentID", "InvestmentName"};
        String query1 = "SELECT InvestmentID, InvestmentName FROM AccountInvestmentsNames WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders2 = {"InvestmentID", "InvestmentAmount"};
        String query2 = "SELECT InvestmentID, InvestmentAmount FROM InvestmentAmount WHERE AccountID = " + ACCOUNT_ID;
        String[] tableHeaders3 = {"InvestmentID", "InvestmentCategoryID"};
        String query3 = "SELECT InvestmentID, InvestmentCategoryID FROM InvestmentCategoriesToInvestments WHERE AccountID = " + ACCOUNT_ID;
        
        Object[][] accountInvestmentsNames = databaseObject.getData(tableHeaders1, query1);
        Object[][] investmentAmount = databaseObject.getData(tableHeaders2, query2);
        Object[][] investmentCategories = databaseObject.getData(tableHeaders3, query3);
        
        numberOfInvestments = accountInvestmentsNames.length;
        
        investmentsTableRecords = new String[numberOfInvestments][4];
        
        for(int i = 0; i < numberOfInvestments; i++) {
            investmentsTableRecords[i][0] = (String) accountInvestmentsNames[i][0];
            investmentsTableRecords[i][1] = (String) accountInvestmentsNames[i][1];
            investmentsTableRecords[i][2] = getInvestmentCategoryString(Integer.parseInt((String) investmentCategories[i][1]));
            investmentsTableRecords[i][3] = (String) investmentAmount[i][1];
        }

        return investmentsTableRecords;
        
    }
    
    // Switches category ID to string
    public String getInvestmentCategoryString(int investmentCategoryID) {
        DatabaseAccess databaseObject = new DatabaseAccess(Constants.DATABASE_NAME);
        return (String) databaseObject.getSpecificData("SELECT InvestmentCategoryName FROM InvestmentCategories WHERE InvestmentCategoryID = " + investmentCategoryID, "InvestmentCategoryName");

    }
    
    public int calculatePointPercent(int value, int maximum, int minimum) {
        
        double minimumDouble = (double) minimum;
        double valueDouble = (double) value - minimum;
        double maximumDouble = (double) maximum - minimum;

        double pointY = (1 - (valueDouble / maximumDouble)) * 400;
        
        return (int) pointY + 50;
    }
    
}
