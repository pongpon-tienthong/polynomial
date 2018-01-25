import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class PolynomialTest {

    @Test
    public void test_constructor_no_param_return_0() {
        Polynomial polynomial = new Polynomial();

        System.out.println(polynomial);

        for (Map.Entry<Integer, Integer> polinomialEntry : polynomial.getPolynomialMap().entrySet()) {
            assertEquals((Integer) 0, polinomialEntry.getKey());
            assertEquals((Integer) 0, polinomialEntry.getValue());
        }
    }

    @Test
    public void test_constructor_with_param_return_polynomial() {
        Polynomial polynomial = new Polynomial(2, 5);

        System.out.println(polynomial);

        for (Map.Entry<Integer, Integer> polinomialEntry : polynomial.getPolynomialMap().entrySet()) {
            assertEquals(2, (int) polinomialEntry.getKey());
            assertEquals(5, (int) polinomialEntry.getValue());
        }
    }

    @Test
    public void test_constructor_with_map_param_return_polynomial() {

        TreeMap<Integer, Integer> tmp = new TreeMap<>();
        tmp.put(1, 3);
        tmp.put(3, 2);

        Polynomial polynomial = new Polynomial(tmp);

        System.out.println(polynomial);

        assertEquals(2, polynomial.getPolynomialMap().size());
        assertEquals(3, (int) polynomial.getPolynomialMap().get(1));
        assertEquals(2, (int) polynomial.getPolynomialMap().get(3));
    }

    @Test
    public void test_plus_two_polynomials_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, 2);
        tmp1.put(2, 1);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, 1);
        tmp2.put(1, 4);
        tmp2.put(3, 4);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);

        System.out.println(p1.plus(p2));

        assertEquals(4, p1.getPolynomialMap().size());
        assertEquals(2, (int) p1.getPolynomialMap().get(0));
        assertEquals(6, (int) p1.getPolynomialMap().get(1));
        assertEquals(1, (int) p1.getPolynomialMap().get(2));
        assertEquals(4, (int) p1.getPolynomialMap().get(3));
    }

    @Test
    public void test_plus_two_polynomials_some_negative_terms_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, -2);
        tmp1.put(2, 1);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, -1);
        tmp2.put(1, 4);
        tmp2.put(3, -4);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);

        System.out.println(p1.plus(p2));

        assertEquals(3, p1.getPolynomialMap().size());
        assertEquals(2, (int) p1.getPolynomialMap().get(1));
        assertEquals(1, (int) p1.getPolynomialMap().get(2));
        assertEquals(-4, (int) p1.getPolynomialMap().get(3));
    }
}