import java.io.PrintWriter;

/**
 * A calculator that takes in inputs from the command line.
 *
 * @author Seunghyeon (Hyeon) Kim
 */
public class QuickCalculator {
  public static void main(String[] args) throws Exception{
    PrintWriter pen = new PrintWriter(System.out, true);
    CalculatorFunctions calcFunc = new CalculatorFunctions(pen);
    for(int i = 0; i < args.length; i++){
      String currentObj = args[i];
      calcFunc.store(currentObj);
    }
  } // main (String[]) throws Exception
}
