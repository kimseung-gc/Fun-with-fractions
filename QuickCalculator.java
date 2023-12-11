import java.io.PrintWriter;

/**
 * A calculator that takes in inputs from the command line.
 *
 * @author Seunghyeon (Hyeon) Kim
 * @version 2 of Dec. 2023
 */
public class QuickCalculator {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    CalculatorFunctions calcFunc = new CalculatorFunctions(pen);
    for (int i = 0; i < args.length; i++) {
      String currentObj = args[i];
      calcFunc.store(currentObj);
    } // for
  } // main (String[]) throws Exception
} // class QuickCalculator
