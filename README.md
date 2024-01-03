# Fun with Fractions

## Groups
```
Seunghyeon Kim
```

## Description
```
I created a program that can act as a calculator for fractions with its numerator
and denominator. The QuickCalculator is the one run by the command-line arguments
and the InteractiveCalculator takes in input from the terminal as a Scanner.
```

## Files Included
```
MP02/
    BFCalculator.java
    BigFraction.java
    CalculatorFunction.java
InteractiveCalculator.java
QuickCalculator.java
CHANGES.md
README.md
```

## How to Compile
```
javac -d bin/ *.java
```

## How to Run

### Quick Calculator
```
java -cp bin QuickCalculator <command 1> <command 2> ...

<command n>: These commands can be any valid commands to give the calculator. 
The valid options are any of the four basic mathematical operands that requires
evaluation, storing variables, "QUIT"/"EXIT", and "RESET."
```

### Interactive Calculator
```
java -cp bin InteractiveCalculator
```

## Acknowledgements
```
Professor Sam. Rebelsky
https://www.w3schools.com/java/java_user_input.asp
https://www.geeksforgeeks.org/check-if-a-value-is-present-in-an-array-in-java/ 
https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/
https://stackoverflow.com/questions/31095326/unhandled-exception-type-exception
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/mps/mp02.html
```