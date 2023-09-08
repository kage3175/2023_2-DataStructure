// Skeleton of the Polynomial ADT

public class Polynomial {

  // Create an empty polynomial
  public Polynomial() {
    int[] formula = {0, 0};
  }

  // Create a single-term polynomial
  public Polynomial(int coef, int exp) {
    int[] formula = {coef, exp};
  }

  public Polynomial mergeSort(Polynomial opnd) {
    int length = opnd.formula.length;
    for(int i = 0;i < length / 2;i++){
      
    }

  }

  // Add opnd to 'this' polynomial; 'this' is returned
  public Polynomial add(Polynomial opnd) {
    Polynomial added;

  }

  // Subtract opnd from 'this' polynomial; 'this' is returned
  public Polynomial sub(Polynomial opnd) {}

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
  public void print() {}
}
