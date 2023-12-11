/**
 * A calculator with its functions for latter programs
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @version 2 of Dec. 2023
 */
public class BFCalculator {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  // A variable that stores registers
  char[] reg;
  // A variable that stores the according registers' fractions
  BigFraction[] reg_frac;
  /*
   * A variable that stores the previous calculation fraction so that
   * it can be stores as a register.
   */
  BigFraction prev;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor of the calculator. When the class is initialized, it
   * will initialize the arrays as null.
   */
  public BFCalculator() {
    this.reg = null; // initializing the arrays as null
    this.reg_frac = null;
    this.prev = new BigFraction("NaN"); // Invalid fraction always when the user tries to store variable
  } // BFCalculator()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * A function that returns whether the register is empty.
   */
  private boolean regIsEmpty() {
    /*
     * When just initialized, reg must be null which checks
     * that reg is empty.
     */
    return (reg == null);
  } // regIsEmpty()

  /**
   * A function that recombines the Strings present in strLs with
   * regex in between each of the elements.
   * 
   * @param strLs
   * @param regex
   * @return String
   */
  private String stringRecombine(String[] strLs, String regex) {
    // declaring an empty String
    String ret = "";
    // When the given list is null, return an empty String
    if (strLs == null) {
      return ret;
    } // if
    /**
     * When the given list is not empty, start merging them
     * into ret.
     */
    for (int i = 0; i < strLs.length; i++) {
      /**
       * When it is the last element in the list, do not add
       * any regex.
       */
      if (i == strLs.length - 1) {
        ret += strLs[i];
      } // if
      /**
       * Otherwise, add regexes for each and every end of the
       * String elements in the array.
       */
      else {
        ret += strLs[i];
        ret += regex;
      } // else
    } // for
    /* Return the result. */
    return ret;
  } // stringRecombine (String[], String)

  /**
   * A function that returns the String array where the elements are
   * the elements of arr from index start, and smaller than index end.
   * Similar to substring, but in terms of arrays.
   * 
   * @param arr
   * @param start
   * @param end
   * @return String[]
   */
  private String[] subArrStr(String[] arr, int start, int end) {
    /**
     * When invalid input is given for the indices, return an empty
     * array.
     */
    if (end <= start) {
      return null;
    } // if
    // Otherwise, initialize an array with length end-start
    String[] ret = new String[end - start];
    // Copy the elements from index start to end-1.
    for (int i = start; i < end; i++) {
      ret[i - start] = arr[i];
    } // for
    // Return the resultant array
    return ret;
  } // subArrStr (String[], int, int)

  /**
   * A function that replaces the register with specified fraction,
   * "Num." When there is no variable in the expression, it will
   * return teh expression itself.
   * 
   * @param exp
   * @param reg
   * @param Num
   * @return String
   */
  private String replaceReg(String exp, char reg, BigFraction Num) {
    // Checking wehther the string contains the variable this.reg.
    if (exp.contains(Character.toString(reg))) {
      /**
       * Declaring a temporary variable that takes the expression,
       * exp, as a char array.
       */
      char[] temp = exp.toCharArray();
      /**
       * Declaring an empty String that will be returned after
       * replacing the registers.
       */
      String ret = "";
      // Looping around to check where the register is.
      for (int i = 0; i < temp.length; i++) {
        /**
         * When register is found, replace the register with the
         * fraction that was stored. Otherwise, copy the original
         * expression.
         */
        if (temp[i] == reg) {
          ret += Num.toString();
        } else {
          ret += Character.toString(temp[i]);
        } // if/else
      } // for
      return ret;
    } // if
    /**
     * If there is no variable found, then it will return the original
     * expression.
     */
    return exp;
  } // replaceReg (String, char, BigFraction)

  /**
   * A function that returns the index of the register "ch." When non
   * existent, it will return -1.
   * 
   * @param ch
   * @return int
   */
  private int indexOfReg(char ch) {
    /**
     * When register is empty, return -1 as there are no register
     * existent
     */
    if (regIsEmpty()) {
      return -1;
    } // if
    /**
     * When register is not empty, search through the register array
     * to look for the index of the register.
     */
    for (int i = 0; i < this.reg.length; i++) {
      /**
       * When the register is found, return the index of the register
       */
      if (this.reg[i] == ch) {
        return i;
      } // if
    } // for
    // When register is not found, also return -1
    return -1;
  } // indexOfReg (char)

  /**
   * A function that stores the previous resultant fraction as variable
   * "register." The function will store the previous calculation
   * results to the reg_frac array within the class. In addition, it will
   * store the name of the registered fraction as <register> in the
   * this.reg array. When improper attempt to storage, it will throw an
   * IllegalArgumentException.
   * 
   * @param register
   * @throws IllegalArgumentException
   */
  public void store(char register) throws IllegalArgumentException {
    if (prev.toString().equals("NaN")) {
      throw new IllegalArgumentException(
          "Non-existent previous calculation or previous calculation NaN");
    } // if
    // Finding the index of register.
    int ind = indexOfReg(register);
    // If there exists an existing register.
    if (ind != -1) {
      // Overwrite the existent register
      this.reg_frac[ind] = prev;
      return;
    } // if
    // When the register is empty
    if (regIsEmpty()) {
      // Increase the this.reg's length to 1
      this.reg = new char[1];
      this.reg[0] = register;
      // Increase the this.reg_frac's length to 1
      this.reg_frac = new BigFraction[1];
      this.reg_frac[0] = prev;
      return;
    } // if
    char[] temp = this.reg;
    this.reg = new char[temp.length + 1];
    if (!regIsEmpty()) {
      for (int i = 0; i < temp.length; i++) {
        this.reg[i] = temp[i];
      } // for
    } // if
    BigFraction[] temp2 = this.reg_frac;
    this.reg_frac = new BigFraction[temp2.length + 1];
    if (!regIsEmpty()) {
      for (int i = 0; i < temp2.length; i++) {
        this.reg_frac[i] = temp2[i];
      } // for
    } // if
    this.reg_frac[temp2.length] = prev;
    this.reg[temp.length] = register;
  } // store (char) throws IllegalArgumentException

  /**
   * A helper function to evaluate the expression. Not fully
   * functional as it cannot store the previous calculation on its own
   * which may break the class when improper usage.
   * 
   * @param exp
   * @return BigFraction
   * @throws IllegalArgumentException
   */
  private BigFraction evaluate_helper(String exp) throws IllegalArgumentException {
    String regex = " ";
    /**
     * declaring tempExp as exp (the expression that will be evaluated)
     */
    String tempExp = exp;
    if (!regIsEmpty()) {
      /**
       * replacing the various registers in exp with the registered
       * fractions.
       */
      for (int i = 0; i < this.reg.length; i++) {
        tempExp = replaceReg(tempExp, this.reg[i], this.reg_frac[i]);
      } // for
    } // if
    String[] tempArr = tempExp.split(regex);
    // Initializing leftOver String as an empty String
    String leftOver = "";
    int count = 0;
    // It starts from the last index for the associativity problems
    for (int i = tempArr.length - 1; i >= 0; i--) {
      if (count > 2) {
        /**
         * Throwing error when there is no operational clause in
         * between numbers
         */
        throw new IllegalArgumentException("Two numbers in a row! Please split them.");
      } // if
      /**
       * Recombining the sub array containing from index 0 to i-1 of
       * tempArr for recursive processing.
       */
      leftOver = stringRecombine(subArrStr(tempArr, 0, i), regex);
      if (tempArr[i].equals("+")) {
        // In case when there is addition
        return (evaluate_helper(leftOver)).add(new BigFraction(tempArr[i + 1]));
      } else if (tempArr[i].equals("-")) {
        // In case when there is subtraction
        return (evaluate_helper(leftOver)).subtract(new BigFraction(tempArr[i + 1]));
      } else if (tempArr[i].equals("*")) {
        // In case when there is multiplication
        return (evaluate_helper(leftOver)).multiply(new BigFraction(tempArr[i + 1]));
      } else if (tempArr[i].equals("/")) {
        // In case when there is division
        return (evaluate_helper(leftOver)).divide(new BigFraction(tempArr[i + 1]));
      } // if/else
      // For latter error catches.
      if (tempArr.length != 1) {
        count++;
      } // if
      // In case the fraction expression is improper
      if (((tempArr[i].charAt(0)) == '/' ||
          (tempArr[i].charAt(tempArr[i].length() - 1) == '/'))) {
        throw new IllegalArgumentException("Invalid format!");
      } // if
    } // for
    /**
     * When there are two numbers in a row, the count will be 1 instead
     * of 0.
     */
    if (count == 1) {
      throw new IllegalArgumentException("Two numbers in a row! Please split them.");
    } // if
    // Default case when there is no other expression left to evaluate
    return new BigFraction(tempArr[0]);
  } // evaluate_helper (String) throws IllegalArgumentException

  /**
   * A function to evaluate the expression. It also stores the
   * previous calculations as this.prev.
   * 
   * @param exp
   * @return BigFraction
   */
  public BigFraction evaluate(String exp) {
    try {
      // Storing the value of output of evaluate_helper(exp)
      this.prev = evaluate_helper(exp);
      // Returning the value
      return this.prev;
    } catch (IndexOutOfBoundsException ex) {
      throw new Error("Input correct expression please!");
    } catch (Exception e) {
      // When there is an error, return an error message.
      throw new Error(e.getMessage());
    } // try/catch
  } // evaluate(String)

  public String getReg(char register) throws Exception {
    int regIndex = indexOfReg(register);
    if (regIndex == -1) {
      throw new Exception("Register not found", null);
    } // if
    return reg_frac[regIndex].toString();
  } // getReg (char)
} // class BFCalculator