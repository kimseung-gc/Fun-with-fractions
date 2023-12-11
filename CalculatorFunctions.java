
import java.io.PrintWriter;
/**
 * A factored out class of functions for the calculators.
 * 
 * @author Seunghyeon (Hyeon) Kim
 */
public class CalculatorFunctions {

  /**
   * PrintWriter for the calculators to display
   */
  PrintWriter pen;
  /**
   * Calculator
   */
  BFCalculator calc;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for CalculatorFunctions. Factored out common codes of the calculators.
   * @param pen
   */
  public CalculatorFunctions(PrintWriter pen){
    this.calc = new BFCalculator();
    this.pen = pen;
  } // CalculatorFunctions(PrintWriter)

  // +----------------+-----------------------------------------------
  // | Public Methods |
  // +----------------+
  /**
   * This is a storing method that is factored out from InteractiveCalculator.java, and 
   * QuickCalculator.java.
   * @param inpObj
   * @param calc
   * @param pen
   * @throws Exception
   */
  public void store(String inpObj) throws Exception{
    if(inpObj.contains("STORE")){
      /* As register's name has to be split with a regex " " */
      String[] tempArr = inpObj.split(" ");
      if(tempArr.length == 2){
        char[] reg = tempArr[1].toCharArray();
        /* When the register's name is correct with only 1 letter long variable name */
        if(reg.length == 1){
          calc.store(reg[0]);
          pen.println("Variable \'" + reg[0] + "\' successfully stored as " + calc.getReg(reg[0]));
        } // if
        /* When the register's name is invalid due to multiple characters */
        else{
          throw new Exception("Invalid storage (variable name can be only a single letter)");
        } // else
      } // if
      /* When there too many/little inputs*/
      else{
        throw new Exception("Invalid storage (too many/little inputs)");
      } // else
    } // if
    /* When RESET, reinitialize the BFCalculator class which will wipe out the stored arrays */
    else if(inpObj.contains("RESET")){
      this.calc = new BFCalculator();
    }else{
      /* When it is not STORE or RESET, evaluate the expresssion. */
      BigFraction num = calc.evaluate(inpObj);
      pen.println(num);
    } // if/else
  }// store(String, BFCalculator, PrintWriter)
} // class CalculatorFunctions
