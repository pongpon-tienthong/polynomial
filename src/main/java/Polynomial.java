import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {

    // The key represent a degree
    // The value represent a coefficient
    private TreeMap<Integer, Integer> polynomialMap = new TreeMap<>();

    // 0
    public Polynomial() {
        polynomialMap.put(0, 0);
    }

    // p = coef * x^degree
    public Polynomial(int degree, int coef) {
        polynomialMap.put(degree, coef);
    }

    // p = coef * x^degree
    public Polynomial(TreeMap<Integer, Integer> polynomial) {
        this.polynomialMap = polynomial;
    }

    public TreeMap<Integer, Integer> getPolynomialMap() {
        return polynomialMap;
    }

    // return p = p1 + p2
    public Polynomial plus(Polynomial in) {

        for(Map.Entry<Integer, Integer> polynomialIn : in.polynomialMap.entrySet()) {

            if(polynomialMap.containsKey(polynomialIn.getKey())) {

                // if p1 + p2 = 0, then eliminate that term
                if(polynomialMap.get(polynomialIn.getKey()) + polynomialIn.getValue() == 0) {
                    polynomialMap.remove(polynomialIn.getKey());
                }

                polynomialMap.put(polynomialIn.getKey(), polynomialMap.get(polynomialIn.getKey()) + polynomialIn.getValue());
            } else {

                polynomialMap.put(polynomialIn.getKey(), polynomialIn.getValue());
            }
        }

        return this;
    }

    // convert to string representation
    public String toString() {

        StringBuilder strBuilder = new StringBuilder("");
        for (Map.Entry<Integer, Integer> polynomial : polynomialMap.entrySet()) {
            if (polynomial.getKey() == 0) strBuilder.append(polynomial.getValue());
            else if (polynomial.getValue() > 0) strBuilder.append(" + ").append(polynomial.getValue()).append("x^").append(polynomial.getKey());
            else if (polynomial.getValue() < 0) strBuilder.append(" - ").append(-polynomial.getValue()).append("x^").append(polynomial.getKey());
        }

        return strBuilder.toString();
    }

}
