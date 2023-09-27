import java.util.ArrayList;
import java.util.List;

public class Polynomial {

  // Create an empty polynomial
  private List<Term> terms;

  public Polynomial() {
    terms = new ArrayList<>();
  }

  // Create a single-term polynomial
  public Polynomial(int coef, int exp) {
    terms = new ArrayList<>();
    terms.add(new Term(coef, exp));
  }

  // Private inner class to represent a term
  private class Term {
    int coefficient;
    int exponent;

    public Term(int coef, int exp) {
      coefficient = coef;
      exponent = exp;
    }
  }

  // Add opnd to 'this' polynomial; 'this' is modified
  public Polynomial add(Polynomial opnd) {
    for (Term term : opnd.terms) {
      insertTerm(term);
    }
    return this;
  }

  // Subtract opnd from 'this' polynomial; 'this' is modified
  public Polynomial sub(Polynomial opnd) {
    for (Term term : opnd.terms) {
      insertTerm(new Term(-term.coefficient, term.exponent));
    }
    return this;
  }

  // Insert a term into the polynomial while maintaining the correct order
  private void insertTerm(Term term) {
    int i = 0;
    while (i < terms.size() && terms.get(i).exponent > term.exponent) {
      i++;
    }

    if (i < terms.size() && terms.get(i).exponent == term.exponent) {
      int sumCoef = terms.get(i).coefficient + term.coefficient;
      if (sumCoef != 0) {
        terms.set(i, new Term(sumCoef, term.exponent));
      } else {
        terms.remove(i);
      }
    } else {
      terms.add(i, term);
    }
  }

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
  public void print() {
    for (Term term : terms) {
      if(term.coefficient != 0){
        System.out.print(term.coefficient + " " + term.exponent + " ");
      }
    }
  }
}
