# Java Calculator Application

A professional calculator application built with Java Swing, featuring a clean GUI interface and comprehensive arithmetic operations.

## Features

### Basic Operations
- Addition (+)
- Subtraction (-)
- Multiplication (×)
- Division (÷)

### Advanced Operations
- Square root (√)
- Percentage (%)
- Memory functions (M+, M-, MR, MC)

### User Interface
- Professional Swing GUI with GridBagLayout
- Keyboard support for all operations
- Clear display with proper number formatting
- Error handling for invalid operations

### Technical Features
- BigDecimal precision for accurate calculations
- Comprehensive unit testing
- MVC architecture pattern
- Proper input validation and error handling

## Requirements

- Java 8 or higher
- GUI environment (for Swing interface)

## Running the Application

### Option 1: Run the JAR file (Recommended)
```bash
java -jar calculator.jar
```

### Option 2: Compile and run from source
```bash
# Compile the source code
javac -d build -cp src/main/java src/main/java/com/calculator/*.java

# Run the GUI application
java -cp build com.calculator.CalculatorGUI
```

### Option 3: Run unit tests
```bash
# Compile tests
javac -d build -cp build:src/test/java src/test/java/com/calculator/CalculatorTest.java

# Run tests
java -cp build com.calculator.CalculatorTest
```

## Keyboard Shortcuts

- **Numbers**: 0-9
- **Operations**: +, -, *, /
- **Equals**: Enter or =
- **Clear**: C or Delete
- **Clear Entry**: Backspace
- **Decimal**: . (period)
- **Memory Add**: Ctrl+M
- **Memory Recall**: Ctrl+R
- **Memory Clear**: Ctrl+L

## Project Structure

```
src/
├── main/java/com/calculator/
│   ├── Calculator.java      # Core calculation logic
│   └── CalculatorGUI.java   # Swing user interface
└── test/java/com/calculator/
    └── CalculatorTest.java  # Unit tests

build/                       # Compiled classes
calculator.jar              # Executable JAR file
```

## Architecture

The application follows the Model-View-Controller (MVC) pattern:

- **Model**: `Calculator.java` - Contains all calculation logic and state management
- **View**: `CalculatorGUI.java` - Handles the Swing user interface and user interactions
- **Controller**: Integrated within the GUI class for event handling

## Testing

The application includes comprehensive unit tests covering:
- Basic arithmetic operations
- Decimal number calculations
- Memory functions
- Error handling (division by zero, negative square roots)
- Edge cases and boundary conditions

Run tests with: `java -cp build com.calculator.CalculatorTest`

## Error Handling

The calculator properly handles:
- Division by zero
- Square root of negative numbers
- Invalid input sequences
- Memory operations on empty memory

## Development

To modify or extend the calculator:

1. Edit the source files in `src/main/java/com/calculator/`
2. Add corresponding tests in `src/test/java/com/calculator/`
3. Recompile and test your changes
4. Rebuild the JAR file if needed

## License

This project is open source and available under the MIT License.