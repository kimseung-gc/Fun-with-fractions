import java.util.Scanner;
import java.io.PrintWriter;

/**
 * A calculator interactively taking in inputs.
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @version 2 of Dec. 2023
 */

public class InteractiveCalculator {
  public static void main(String[] args) throws Exception {
    // Initialization of variables and classes
    PrintWriter pen = new PrintWriter(System.out, true);
    // Similar to scanf in C
    Scanner inpScanner = new Scanner(System.in);
    pen.println("What is the expression you would like to evaluate?");
    // Open a scanner
    String inpObj = inpScanner.nextLine();
    // Initialize the calculator from the factored out codes.
    CalculatorFunctions calcFunc = new CalculatorFunctions(pen);
    // In case of QUIT or EXIT, the loop will end.
    while (!inpObj.equals("QUIT") && !inpObj.equals("EXIT")) {
      try {
        calcFunc.store(inpObj);
      } catch (Exception e) {
        inpScanner.close();
        throw new Exception(e.getMessage(), e.getCause());
      } // try/catch
      // Reading the input from next line.
      inpObj = inpScanner.nextLine();
    } // while
    // Have to close the stream when opened
    inpScanner.close();
    pen.close();
  } // main (String[]) throws Exception
} // class InteractiveCalculator
