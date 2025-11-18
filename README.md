# Java Calculator Application

A professional Java calculator application with Swing GUI, featuring basic and advanced arithmetic operations, memory functions, and comprehensive testing.

## 🚀 Features

### Core Functionality
- **Basic Arithmetic**: Addition (+), Subtraction (-), Multiplication (×), Division (÷)
- **Advanced Operations**: Square root (√), Percentage (%)
- **Memory Functions**: Memory Add (M+), Memory Subtract (M-), Memory Recall (MR), Memory Clear (MC)
- **BigDecimal Precision**: Accurate decimal calculations without floating-point errors

### User Interface
- **Professional Swing GUI** with GridBagLayout for optimal component arrangement
- **Full Keyboard Support** for all calculator operations
- **Clean Display** with proper number formatting
- **Error Handling** with user-friendly error messages

### Technical Excellence
- **MVC Architecture**: Clean separation of calculation logic and UI
- **Comprehensive Testing**: Unit tests covering all operations and edge cases
- **Executable JAR**: Ready-to-run application package

## 📁 Project Structure

```
src/
├── main/java/com/calculator/
│   ├── Calculator.java      # Core calculation logic with BigDecimal precision
│   └── CalculatorGUI.java   # Professional Swing GUI with keyboard support
└── test/java/com/calculator/
    └── CalculatorTest.java  # Comprehensive unit tests (all passing)

README.md                    # Complete documentation
MANIFEST.MF                  # JAR manifest for executable packaging
.gitignore                   # Java project ignore patterns
```

## 🎯 Usage

### Run the Application

#### Option 1: Compile and Run from Source
```bash
# Compile the source code
javac -d build -cp src/main/java src/main/java/com/calculator/*.java

# Run the calculator GUI
java -cp build com.calculator.CalculatorGUI
```

#### Option 2: Create and Run JAR File
```bash
# Create the JAR file
jar cfm calculator.jar MANIFEST.MF -C build .

# Run the JAR file
java -jar calculator.jar
```

### Run Tests
```bash
# Compile and run tests
javac -d build -cp build:src/test/java src/test/java/com/calculator/CalculatorTest.java
java -cp build com.calculator.CalculatorTest
```

## 🔧 Technical Details

- **Java Version**: Compatible with Java 8+
- **GUI Framework**: Swing with GridBagLayout
- **Precision**: BigDecimal for accurate decimal arithmetic
- **Architecture**: Model-View-Controller pattern
- **Testing**: Comprehensive unit test coverage

## 🎨 UI Features

- Professional button layout with proper spacing
- Keyboard shortcuts for all operations
- Clear visual feedback for user actions
- Error messages for invalid operations
- Memory indicator for stored values

## ✅ Testing

All unit tests pass successfully:
- ✓ Basic arithmetic operations
- ✓ Decimal number calculations  
- ✓ Memory functions
- ✓ Error handling (division by zero, negative square roots)
- ✓ Edge cases and boundary conditions

## 🚀 Future Enhancements

This implementation provides a solid foundation for a calculator application with room for future enhancements such as:
- Scientific functions (sin, cos, tan, log, etc.)
- Calculation history tracking
- Custom themes and UI customization
- Expression parsing for complex calculations
- Unit conversions