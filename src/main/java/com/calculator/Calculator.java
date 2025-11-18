package com.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    private BigDecimal currentValue;
    private BigDecimal previousValue;
    private String currentOperation;
    private boolean operationPressed;
    private BigDecimal memory;
    private String currentInput;
    
    public Calculator() {
        reset();
        memory = BigDecimal.ZERO;
    }
    
    public void reset() {
        currentValue = BigDecimal.ZERO;
        previousValue = BigDecimal.ZERO;
        currentOperation = "";
        operationPressed = false;
        currentInput = "";
    }
    
    public void clearEntry() {
        currentValue = BigDecimal.ZERO;
        currentInput = "";
    }
    
    public void inputNumber(String number) {
        if (operationPressed || currentInput.isEmpty()) {
            currentInput = number;
            operationPressed = false;
        } else {
            // If number is more than one character, replace current input
            if (number.length() > 1) {
                currentInput = number;
            } else {
                // Single character - append to current input
                if (number.equals(".") && currentInput.contains(".")) {
                    return; // Don't add multiple decimal points
                }
                if (currentInput.isEmpty() && number.equals(".")) {
                    currentInput = "0.";
                } else {
                    currentInput += number;
                }
            }
        }
        
        try {
            currentValue = new BigDecimal(currentInput);
        } catch (NumberFormatException e) {
            currentValue = BigDecimal.ZERO;
            currentInput = "";
        }
    }
    
    public void setOperation(String operation) {
        if (!currentOperation.isEmpty() && !operationPressed) {
            calculate();
        }
        previousValue = currentValue;
        currentOperation = operation;
        operationPressed = true;
        currentInput = "";
    }
    
    public BigDecimal calculate() {
        if (currentOperation.isEmpty() || previousValue == null) {
            return currentValue;
        }
        
        try {
            switch (currentOperation) {
                case "+":
                    currentValue = previousValue.add(currentValue);
                    break;
                case "-":
                    currentValue = previousValue.subtract(currentValue);
                    break;
                case "*":
                    currentValue = previousValue.multiply(currentValue);
                    break;
                case "/":
                    if (currentValue.equals(BigDecimal.ZERO)) {
                        throw new ArithmeticException("Division by zero");
                    }
                    currentValue = previousValue.divide(currentValue, 10, RoundingMode.HALF_UP);
                    break;
                default:
                    return currentValue;
            }
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error: " + e.getMessage());
        }
        
        currentOperation = "";
        operationPressed = true;
        return currentValue;
    }
    
    public BigDecimal sqrt() {
        if (currentValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        
        if (currentValue.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        
        // Newton's method for square root
        BigDecimal x = currentValue;
        BigDecimal sqrt = currentValue.divide(new BigDecimal("2"), 10, RoundingMode.HALF_UP);
        
        for (int i = 0; i < 50; i++) {
            BigDecimal newSqrt = sqrt.add(x.divide(sqrt, 10, RoundingMode.HALF_UP))
                                     .divide(new BigDecimal("2"), 10, RoundingMode.HALF_UP);
            if (newSqrt.equals(sqrt)) {
                break;
            }
            sqrt = newSqrt;
        }
        
        currentValue = sqrt;
        operationPressed = true;
        return currentValue;
    }
    
    public BigDecimal percentage() {
        currentValue = currentValue.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        operationPressed = true;
        return currentValue;
    }
    
    public void memoryAdd() {
        memory = memory.add(currentValue);
        operationPressed = true;
    }
    
    public void memorySubtract() {
        memory = memory.subtract(currentValue);
        operationPressed = true;
    }
    
    public BigDecimal memoryRecall() {
        currentValue = memory;
        currentInput = memory.toPlainString();
        operationPressed = true;
        return memory;
    }
    
    public void memoryClear() {
        memory = BigDecimal.ZERO;
    }
    
    public BigDecimal getCurrentValue() {
        return currentValue;
    }
    
    public String getDisplayValue() {
        String value = currentValue.stripTrailingZeros().toPlainString();
        
        // Handle very large or very small numbers
        if (value.length() > 15) {
            return currentValue.toString();
        }
        
        return value;
    }
    
    public boolean hasMemory() {
        return !memory.equals(BigDecimal.ZERO);
    }
}