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
                } else {
                    polynomialMap.put(polynomialIn.getKey(), polynomialMap.get(polynomialIn.getKey()) + polynomialIn.getValue());
                }
            } else {

                polynomialMap.put(polynomialIn.getKey(), polynomialIn.getValue());
            }
        }

        return this;
    }

    public Polynomial time(Polynomial in) {

        Polynomial polynomialTmp = new Polynomial();
        TreeMap<Integer, Integer> tmp = polynomialTmp.getPolynomialMap();

        for (Map.Entry<Integer, Integer> p1 : this.getPolynomialMap().entrySet()) {
            for (Map.Entry<Integer, Integer> p2 : in.getPolynomialMap().entrySet()) {

                Integer degree = p1.getKey() + p2.getKey();
                Integer coef = p1.getValue() * p2.getValue();

                if (tmp.containsKey(degree)) {
                    if(tmp.get(degree) + coef == 0 && degree != 0) {
                        tmp.remove(degree);
                    } else {
                        tmp.put(degree, tmp.get(degree) + coef);
                    }
                } else if (!tmp.containsKey(degree) && coef != 0) {
                    tmp.put(degree, coef);
                }
            }
        }

        this.polynomialMap = tmp;

        return this;
    }

    // convert polynomial to string
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
