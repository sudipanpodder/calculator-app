package com.calculator;

import java.math.BigDecimal;

public class CalculatorTest {
    private Calculator calculator;
    
    public CalculatorTest() {
        calculator = new Calculator();
    }
    
    public void runAllTests() {
        System.out.println("Running Calculator Tests...\n");
        
        testBasicArithmetic();
        testDecimalOperations();
        testMemoryFunctions();
        testSpecialFunctions();
        testErrorHandling();
        
        System.out.println("All tests completed!");
    }
    
    private void testBasicArithmetic() {
        System.out.println("Testing Basic Arithmetic:");
        
        // Test addition
        calculator.reset();
        calculator.inputNumber("5");
        calculator.setOperation("+");
        calculator.inputNumber("3");
        BigDecimal result = calculator.calculate();
        assert result.equals(new BigDecimal("8")) : "Addition test failed";
        System.out.println("✓ Addition: 5 + 3 = " + result);
        
        // Test subtraction
        calculator.reset();
        calculator.inputNumber("10");
        calculator.setOperation("-");
        calculator.inputNumber("4");
        result = calculator.calculate();
        assert result.equals(new BigDecimal("6")) : "Subtraction test failed";
        System.out.println("✓ Subtraction: 10 - 4 = " + result);
        
        // Test multiplication
        calculator.reset();
        calculator.inputNumber("6");
        calculator.setOperation("*");
        calculator.inputNumber("7");
        result = calculator.calculate();
        assert result.equals(new BigDecimal("42")) : "Multiplication test failed";
        System.out.println("✓ Multiplication: 6 * 7 = " + result);
        
        // Test division
        calculator.reset();
        calculator.inputNumber("15");
        calculator.setOperation("/");
        calculator.inputNumber("3");
        result = calculator.calculate();
        assert result.equals(new BigDecimal("5")) : "Division test failed";
        System.out.println("✓ Division: 15 / 3 = " + result);
        
        System.out.println();
    }
    
    private void testDecimalOperations() {
        System.out.println("Testing Decimal Operations:");
        
        calculator.reset();
        calculator.inputNumber("2");
        calculator.inputNumber(".");
        calculator.inputNumber("5");
        calculator.setOperation("+");
        calculator.inputNumber("1");
        calculator.inputNumber(".");
        calculator.inputNumber("5");
        BigDecimal result = calculator.calculate();
        assert result.equals(new BigDecimal("4.0")) : "Decimal addition test failed";
        System.out.println("✓ Decimal Addition: 2.5 + 1.5 = " + result);
        
        System.out.println();
    }
    
    private void testMemoryFunctions() {
        System.out.println("Testing Memory Functions:");
        
        calculator.reset();
        calculator.inputNumber("10");
        calculator.memoryAdd();
        assert calculator.hasMemory() : "Memory should have value";
        System.out.println("✓ Memory Add: Stored 10");
        
        calculator.inputNumber("5");
        calculator.memoryAdd();
        BigDecimal memoryValue = calculator.memoryRecall();
        assert memoryValue.equals(new BigDecimal("15")) : "Memory recall test failed";
        System.out.println("✓ Memory Recall: " + memoryValue);
        
        calculator.memoryClear();
        assert !calculator.hasMemory() : "Memory should be cleared";
        System.out.println("✓ Memory Clear: Memory cleared");
        
        System.out.println();
    }
    
    private void testSpecialFunctions() {
        System.out.println("Testing Special Functions:");
        
        // Test square root
        calculator.reset();
        calculator.inputNumber("9");
        BigDecimal result = calculator.sqrt();
        assert result.equals(new BigDecimal("3")) : "Square root test failed";
        System.out.println("✓ Square Root: √9 = " + result);
        
        // Test percentage
        calculator.reset();
        calculator.inputNumber("50");
        result = calculator.percentage();
        assert result.equals(new BigDecimal("0.5")) : "Percentage test failed";
        System.out.println("✓ Percentage: 50% = " + result);
        
        System.out.println();
    }
    
    private void testErrorHandling() {
        System.out.println("Testing Error Handling:");
        
        // Test division by zero
        calculator.reset();
        calculator.inputNumber("5");
        calculator.setOperation("/");
        calculator.inputNumber("0");
        
        try {
            calculator.calculate();
            assert false : "Division by zero should throw exception";
        } catch (ArithmeticException e) {
            System.out.println("✓ Division by zero handled: " + e.getMessage());
        }
        
        // Test square root of negative number
        calculator.reset();
        calculator.inputNumber("-4");
        
        try {
            calculator.sqrt();
            assert false : "Square root of negative should throw exception";
        } catch (ArithmeticException e) {
            System.out.println("✓ Negative square root handled: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        CalculatorTest test = new CalculatorTest();
        test.runAllTests();
    }
}