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

    /**
     * example:
     * TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
     * tmp1.put(0, 1);
     * tmp1.put(1, -2);
     * tmp1.put(2, 1);
     *
     * TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
     * tmp2.put(0, -1);
     * tmp2.put(1, 4);
     * tmp2.put(3, -4);
     *
     * Polynomial p1 = new Polynomial(tmp1); // p1 = 1 - 2x + x^2
     * Polynomial p2 = new Polynomial(tmp2); // p1 = -1 + 4x - 4x^3
     *
     * System.out.println(p1.plus(p2)); // p1 + p2 = 2x + x^2 - 4x^3
     */
    public Polynomial plus(Polynomial polynomialIn) {

        for (Map.Entry<Integer, Integer> polynomial : polynomialIn.getPolynomialMap().entrySet()) {

            if (polynomialMap.containsKey(polynomial.getKey())) {

                // if p1 + p2 = 0, then eliminate that term
                if (polynomialMap.get(polynomial.getKey()) + polynomial.getValue() == 0) {
                    polynomialMap.remove(polynomial.getKey());
                } else {
                    polynomialMap.put(polynomial.getKey(), polynomialMap.get(polynomial.getKey()) + polynomial.getValue());
                }
            } else {

                polynomialMap.put(polynomial.getKey(), polynomial.getValue());
            }
        }

        return this;
    }

    // return p = p1 - p2
    public Polynomial minus(Polynomial polynomialIn) {

        for (Map.Entry<Integer, Integer> polynomial : polynomialIn.getPolynomialMap().entrySet()) {

            if (polynomialMap.containsKey(polynomial.getKey())) {

                // if p1 + p2 = 0, then eliminate that term
                if (polynomialMap.get(polynomial.getKey()) - polynomial.getValue() == 0) {
                    polynomialMap.remove(polynomial.getKey());
                } else {
                    polynomialMap.put(polynomial.getKey(), polynomialMap.get(polynomial.getKey()) - polynomial.getValue());
                }
            } else {

                polynomialMap.put(polynomial.getKey(), -polynomial.getValue());
            }
        }

        return this;
    }

    // return p = p1 * p2
    public Polynomial time(Polynomial polynomialIn) {

        Polynomial polynomialTmp = new Polynomial();
        TreeMap<Integer, Integer> tmp = polynomialTmp.getPolynomialMap();

        for (Map.Entry<Integer, Integer> p1 : this.getPolynomialMap().entrySet()) {
            for (Map.Entry<Integer, Integer> p2 : polynomialIn.getPolynomialMap().entrySet()) {

                Integer degree = p1.getKey() + p2.getKey();
                Integer coef = p1.getValue() * p2.getValue();

                if (tmp.containsKey(degree)) {
                    if (tmp.get(degree) + coef == 0 && degree != 0) {
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

    // return p = pʹ
    public Polynomial differentiate() {

        Polynomial polynomialTmp = new Polynomial();
        TreeMap<Integer, Integer> tmp = polynomialTmp.getPolynomialMap();

        if (this.getPolynomialMap().firstKey() != 0 || this.getPolynomialMap().size() != 1) {

            tmp.remove(0);

            for (Map.Entry<Integer, Integer> polynomial : this.getPolynomialMap().entrySet()) {

                Integer degree = polynomial.getKey() - 1;
                Integer coef = polynomial.getValue() * polynomial.getKey();

                if (degree > -1) {
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
            else if (polynomial.getValue() > 0)
                strBuilder.append(" + ").append(polynomial.getValue()).append("x^").append(polynomial.getKey());
            else if (polynomial.getValue() < 0)
                strBuilder.append(" - ").append(-polynomial.getValue()).append("x^").append(polynomial.getKey());
        }

        return strBuilder.toString();
    }

}
