package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculatorGUI extends JFrame implements ActionListener, KeyListener {
    private Calculator calculator;
    private JTextField display;
    private JLabel memoryIndicator;
    private boolean errorState = false;
    
    // Button references for styling
    private JButton[] numberButtons = new JButton[10];
    private JButton[] operationButtons = new JButton[4];
    private JButton equalsButton, clearButton, clearEntryButton, decimalButton;
    private JButton sqrtButton, percentButton;
    private JButton memoryAddButton, memorySubtractButton, memoryRecallButton, memoryClearButton;
    
    public CalculatorGUI() {
        calculator = new Calculator();
        initializeGUI();
        setupKeyListener();
    }
    
    private void initializeGUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create display panel
        JPanel displayPanel = createDisplayPanel();
        mainPanel.add(displayPanel, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        
        // Set focus to the frame for key listening
        setFocusable(true);
        requestFocus();
    }
    
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Memory indicator
        memoryIndicator = new JLabel("M");
        memoryIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        memoryIndicator.setForeground(Color.BLUE);
        memoryIndicator.setVisible(false);
        panel.add(memoryIndicator, BorderLayout.WEST);
        
        // Display field
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLoweredBevelBorder());
        display.setPreferredSize(new Dimension(300, 50));
        panel.add(display, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        
        // Row 0: Memory and function buttons
        gbc.gridy = 0;
        addButton(panel, gbc, 0, "MC", Color.LIGHT_GRAY);
        addButton(panel, gbc, 1, "MR", Color.LIGHT_GRAY);
        addButton(panel, gbc, 2, "M+", Color.LIGHT_GRAY);
        addButton(panel, gbc, 3, "M-", Color.LIGHT_GRAY);
        
        // Row 1: Clear and function buttons
        gbc.gridy = 1;
        addButton(panel, gbc, 0, "C", Color.RED);
        addButton(panel, gbc, 1, "CE", Color.ORANGE);
        addButton(panel, gbc, 2, "√", Color.LIGHT_GRAY);
        addButton(panel, gbc, 3, "%", Color.LIGHT_GRAY);
        
        // Row 2: Numbers 7, 8, 9 and division
        gbc.gridy = 2;
        addButton(panel, gbc, 0, "7", Color.WHITE);
        addButton(panel, gbc, 1, "8", Color.WHITE);
        addButton(panel, gbc, 2, "9", Color.WHITE);
        addButton(panel, gbc, 3, "/", Color.CYAN);
        
        // Row 3: Numbers 4, 5, 6 and multiplication
        gbc.gridy = 3;
        addButton(panel, gbc, 0, "4", Color.WHITE);
        addButton(panel, gbc, 1, "5", Color.WHITE);
        addButton(panel, gbc, 2, "6", Color.WHITE);
        addButton(panel, gbc, 3, "*", Color.CYAN);
        
        // Row 4: Numbers 1, 2, 3 and subtraction
        gbc.gridy = 4;
        addButton(panel, gbc, 0, "1", Color.WHITE);
        addButton(panel, gbc, 1, "2", Color.WHITE);
        addButton(panel, gbc, 2, "3", Color.WHITE);
        addButton(panel, gbc, 3, "-", Color.CYAN);
        
        // Row 5: 0, decimal, equals, addition
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        addButton(panel, gbc, 0, "0", Color.WHITE);
        gbc.gridwidth = 1;
        addButton(panel, gbc, 2, ".", Color.WHITE);
        addButton(panel, gbc, 3, "+", Color.CYAN);
        
        // Row 6: Equals button spanning 4 columns
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        addButton(panel, gbc, 0, "=", Color.GREEN);
        
        return panel;
    }
    
    private void addButton(JPanel panel, GridBagConstraints gbc, int x, String text, Color color) {
        gbc.gridx = x;
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(70, 50));
        button.setBackground(color);
        button.addActionListener(this);
        button.setFocusable(false);
        
        // Store button references for easy access
        switch (text) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                numberButtons[Integer.parseInt(text)] = button;
                break;
            case "+": operationButtons[0] = button; break;
            case "-": operationButtons[1] = button; break;
            case "*": operationButtons[2] = button; break;
            case "/": operationButtons[3] = button; break;
            case "=": equalsButton = button; break;
            case "C": clearButton = button; break;
            case "CE": clearEntryButton = button; break;
            case ".": decimalButton = button; break;
            case "√": sqrtButton = button; break;
            case "%": percentButton = button; break;
            case "M+": memoryAddButton = button; break;
            case "M-": memorySubtractButton = button; break;
            case "MR": memoryRecallButton = button; break;
            case "MC": memoryClearButton = button; break;
        }
        
        panel.add(button, gbc);
    }
    
    private void setupKeyListener() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (errorState) {
            calculator.reset();
            errorState = false;
        }
        
        String command = e.getActionCommand();
        
        try {
            switch (command) {
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                    calculator.inputNumber(command);
                    break;
                case ".":
                    calculator.inputNumber(".");
                    break;
                case "+": case "-": case "*": case "/":
                    calculator.setOperation(command);
                    break;
                case "=":
                    calculator.calculate();
                    break;
                case "C":
                    calculator.reset();
                    break;
                case "CE":
                    calculator.clearEntry();
                    break;
                case "√":
                    calculator.sqrt();
                    break;
                case "%":
                    calculator.percentage();
                    break;
                case "M+":
                    calculator.memoryAdd();
                    break;
                case "M-":
                    calculator.memorySubtract();
                    break;
                case "MR":
                    calculator.memoryRecall();
                    break;
                case "MC":
                    calculator.memoryClear();
                    break;
            }
            
            updateDisplay();
            updateMemoryIndicator();
            
        } catch (ArithmeticException ex) {
            display.setText(ex.getMessage());
            errorState = true;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        
        if (errorState) {
            calculator.reset();
            errorState = false;
        }
        
        try {
            if (Character.isDigit(key)) {
                calculator.inputNumber(String.valueOf(key));
                updateDisplay();
            } else {
                switch (key) {
                    case '.':
                        calculator.inputNumber(".");
                        updateDisplay();
                        break;
                    case '+':
                        calculator.setOperation("+");
                        updateDisplay();
                        break;
                    case '-':
                        calculator.setOperation("-");
                        updateDisplay();
                        break;
                    case '*':
                        calculator.setOperation("*");
                        updateDisplay();
                        break;
                    case '/':
                        calculator.setOperation("/");
                        updateDisplay();
                        break;
                    case '=':
                    case '\n':
                        calculator.calculate();
                        updateDisplay();
                        break;
                }
            }
            
            // Handle special keys
            switch (keyCode) {
                case KeyEvent.VK_ESCAPE:
                    calculator.reset();
                    updateDisplay();
                    break;
                case KeyEvent.VK_DELETE:
                case KeyEvent.VK_BACK_SPACE:
                    calculator.clearEntry();
                    updateDisplay();
                    break;
            }
            
            updateMemoryIndicator();
            
        } catch (ArithmeticException ex) {
            display.setText(ex.getMessage());
            errorState = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    private void updateDisplay() {
        display.setText(calculator.getDisplayValue());
    }
    
    private void updateMemoryIndicator() {
        memoryIndicator.setVisible(calculator.hasMemory());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorGUI().setVisible(true);
        });
    }
}