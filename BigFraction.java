import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Seunghyeon (Hyeon) Kim
 * @version 2 of Dec. 2023
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are
   * represented
   * with a negative numerator. Similarly, if a fraction has a negative numerator,
   * it
   * is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a
   * fraction
   * in simplified form, one must call the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * @pre The class constructor can take in (BigInteger num, BigInteger denom),
   *      (int num, int denom), or (String str). In case of str, it has to be in
   *      the format
   *      "a/b" where a, b ∈ ℤ; when correctly initialized, the constructor will
   *      declare num
   *      = a, denom = b.
   * @comments This class, when called, initializes the num and denom variable as
   *           stated.
   *           It also simplifies the initialized fractions automatically.
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    BigInteger tempGcd = num.gcd(denom); // GCD of numerator and denominator
    if (denom.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO; // edge case: when the given input has zero as a divisor or numerator as 0
      this.denom = BigInteger.ZERO;
    } else if (num.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ONE;
    } else { // If not edge case, simplification of numerator and denominator
      this.num = num.divide(tempGcd);
      this.denom = denom.divide(tempGcd);
    }
  } // Fraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * @pre The class constructor can take in (BigInteger num, BigInteger denom),
   *      (int num, int denom), or (String str). In case of str, it has to be in
   *      the format
   *      "a/b" where a, b ∈ ℤ; when correctly initialized, the constructor will
   *      declare num
   *      = a, denom = b.
   * @comments This class, when called, initializes the num and denom variable as
   *           stated.
   *           It also simplifies the initialized fractions automatically.
   */
  public BigFraction(int num, int denom) {
    BigInteger numP = BigInteger.valueOf(num); // Casting integer num as BigInteger
    BigInteger denomP = BigInteger.valueOf(denom); // Casting integer denom as BigInteger
    BigInteger tempGcd = numP.gcd(denomP); // GCD of numerator and denominator
    if (denomP.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO; // edge case: when the given input has zero as a divisor or numerator as 0
      this.denom = BigInteger.ZERO;
    } else if (numP.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ONE;
    } else { // If not edge case, simplification of numerator and denominator
      this.num = numP.divide(tempGcd);
      this.denom = denomP.divide(tempGcd);
    }
  } // Fraction(int, int)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * @pre The class constructor can take in (BigInteger num, BigInteger denom),
   *      (int num, int denom), or (String str). In case of str, it has to be in
   *      the format
   *      "a/b" where a, b ∈ ℤ; when correctly initialized, the constructor will
   *      declare num
   *      = a, denom = b.
   * @comments This class, when called, initializes the num and denom variable as
   *           stated.
   *           It also simplifies the initialized fractions automatically.
   */
  public BigFraction(String str) throws IllegalArgumentException {
    str = remove(str, "--");
    if (str.equals("NaN")) {
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ZERO;
      return;
    }
    if (!str.contains("/")) {
      try {
        BigInteger tempNum = BigInteger.valueOf(Integer.parseInt(str.trim()));
        this.num = tempNum;
        this.denom = BigInteger.ONE;
        return;
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid input!");
      }
    }
    String[] tempVar = str.split("/");
    if (tempVar.length < 2) {
      try {
        this.num = BigInteger.valueOf(Integer.parseInt(tempVar[0].trim())); // edge case: when not enough input was
                                                                            // given.
        return;
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid input!");
      }
    } else if (tempVar.length > 2) {
      throw new IllegalArgumentException("Invalid fraction input");
    }
    BigInteger tempNum = BigInteger.valueOf(Integer.parseInt(tempVar[0].trim()));
    BigInteger tempDenom = BigInteger.valueOf(Integer.parseInt(tempVar[1].trim()));
    BigInteger tempGcd = tempNum.gcd(tempDenom); // GCD of numerator and denominator
    if (tempDenom.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO; // edge case: when the given input has zero as a divisor or numerator as 0
      this.denom = BigInteger.ZERO;
    } else if (tempNum.equals(BigInteger.valueOf(0))) {
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ONE;
    } else { // If not edge case, simplification of numerator and denominator
      this.num = tempNum.divide(tempGcd);
      this.denom = tempDenom.divide(tempGcd);
    }
  } // Fraction using string

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   * 
   * @pre The function does not require any inputs.
   * @post The function returns the double value of the fraction by evaluating
   *       the fraction as int divided by another int.
   * @comments The class must be initialized.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   * 
   * @pre The function takes in another fraction that will be added to the
   *      current fraction.
   * @post The output will be the resultant summed fraction between this and
   *       addMe.
   * @comments The class must be initialized.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  }// add(Fraction)

  /**
   * subtracts the fraction `subMe` to this fraction.
   * 
   * @pre The function takes in another fraction that will be subtracted to the
   *      current fraction.
   * @post The output will be the resultant subtracted fraction between this and
   *       subMe.
   * @comments The class must be initialized.
   */
  public BigFraction subtract(BigFraction subMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and subMe's denominator
    resultDenominator = this.denom.multiply(subMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(subMe.denom)).subtract(subMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  }// add(Fraction)

  /**
   * Multiply the fraction `multiplyMe` to this fraction.
   * 
   * @pre The function requires another variable under the BigFraction class type
   *      to be multiplied to the current variable with BigFraction type.
   * @post This function will return a BigFraction class type of variable which
   *       will contain this * multiplyMe value.
   * @comments The edge cases are not specified as an if statement as the
   *           constructors do
   *           the job for us. Also, the class must be initialized.
   */
  public BigFraction multiply(BigFraction multiplyMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the
    // product of this object's denominator
    // and multiplyMe's denominator
    resultDenominator = this.denom.multiply(multiplyMe.denom);
    // The numerator is also similar
    resultNumerator = this.num.multiply(multiplyMe.num);
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  }// multiply(Fraction)

  /**
   * Leave the fractional part, and throws away the integer part.
   * 
   * @pre None
   * @post This function will return the fractional part of the current fraction
   *       while leaving the integer part out.
   * @comments The class must be initialized.
   */
  public BigFraction fractional() {
    BigInteger resultNumerator;

    resultNumerator = this.num.mod(this.denom);

    // Return the computed value
    return new BigFraction(resultNumerator, this.denom);
  }// fractional()

  /**
   * Flip the numerator and denominator of this and returns it
   * 
   * @pre None
   * @post This function will return the reciprocal of "this."
   * @comments Becareful when flipping a 0 variable. Also, this function is used
   *           as a helper
   *           function for divide. Also, the class must be initialized.
   */
  public BigFraction reciprocal() {
    return new BigFraction(this.denom, this.num);
  } // reciprocal()

  /**
   * @param divMe
   * @pre The input must also be the type BigFraction to be divided.
   * @post This function will return this/divMe which is the same logic as
   *       this*1/divMe, so the reciprocal can be used.
   * @comments The class must be initialized.
   */
  public BigFraction divide(BigFraction divMe) {
    return multiply(divMe.reciprocal());
  } // divide (Fraction)

  /**
   * Get the denominator of this fraction.
   * 
   * @pre None
   * @post It returns the denominator of the fraction.
   * @comments The class must be initialized
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   * 
   * @pre None
   * @post It returns the numerator of the fraction.
   * @comments The class must be initialized.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   * 
   * @pre None
   * @post It returns the fraction changed into string type.
   * @comments It will return 0 when the fraction is of the form "0/a." Also, it
   *           will return
   *           NaN when there was a interaction that made the variable become
   *           "0/0." In addition, the
   *           class must be initialized.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO) && this.denom.equals(BigInteger.ZERO)) {
      return "NaN";
    } else if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    }
    return this.num + "/" + this.denom;
  } // toString()

  /**
   * @pre inp, regex
   * @post it returns string as comment expresses
   * @comments It removes the regex from the input string
   */
  private String remove(String inp, String regex) {
    String ret = "";
    String[] temp = inp.split(regex);
    for (String each : temp) {
      ret += each;
    }
    return ret;
  } // remove(String, String)
} // class Fraction