import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void test_minus_two_polynomials_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 7);
        tmp1.put(1, -3);
        tmp1.put(2, -4);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, 1);
        tmp2.put(1, 4);
        tmp2.put(2, -4);
        tmp2.put(3, 4);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);

        System.out.println(p1.minus(p2));

        assertEquals(3, p1.getPolynomialMap().size());
        assertEquals(6, (int) p1.getPolynomialMap().get(0));
        assertEquals(-7, (int) p1.getPolynomialMap().get(1));
        assertEquals(-4, (int) p1.getPolynomialMap().get(3));
    }

    @Test
    public void test_time_two_polynomials_terms_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, 1);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, -1);
        tmp2.put(1, 1);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);

        System.out.println(p1.time(p2));

        assertEquals(2, p1.getPolynomialMap().size());
        assertEquals(-1, (int) p1.getPolynomialMap().get(0));
        assertEquals(1, (int) p1.getPolynomialMap().get(2));
    }

    @Test
    public void test_time_three_multiples_polynomials_terms_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, 1);
        tmp1.put(3, 5);
        tmp1.put(7, -2);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, -1);
        tmp2.put(1, 1);
        tmp2.put(2, -6);
        tmp2.put(3, -7);

        TreeMap<Integer, Integer> tmp3 = new TreeMap<>();
        tmp3.put(0, 7);
        tmp3.put(3, 8);
        tmp3.put(5, 12);
        tmp3.put(6, -1);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);
        Polynomial p3 = new Polynomial(tmp3);

        System.out.println(p1.time(p2).time(p3));

        assertEquals(16, p1.getPolynomialMap().size());
        assertEquals(-7, (int) p1.getPolynomialMap().get(0));
        assertEquals(-35, (int) p1.getPolynomialMap().get(2));
        assertEquals(-134, (int) p1.getPolynomialMap().get(3));
        assertEquals(-14, (int) p1.getPolynomialMap().get(4));
        assertEquals(-262, (int) p1.getPolynomialMap().get(5));
        assertEquals(-388, (int) p1.getPolynomialMap().get(6));
        assertEquals(-62, (int) p1.getPolynomialMap().get(7));
        assertEquals(-465, (int) p1.getPolynomialMap().get(8));
        assertEquals(-202, (int) p1.getPolynomialMap().get(9));
        assertEquals(-244, (int) p1.getPolynomialMap().get(10));
        assertEquals(-406, (int) p1.getPolynomialMap().get(11));
        assertEquals(155, (int) p1.getPolynomialMap().get(12));
        assertEquals(86, (int) p1.getPolynomialMap().get(13));
        assertEquals(146, (int) p1.getPolynomialMap().get(14));
        assertEquals(156, (int) p1.getPolynomialMap().get(15));
        assertEquals(-14, (int) p1.getPolynomialMap().get(16));
    }

    @Test
    public void test_time_zero_and_polynomial_return_zero() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 0);

        TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
        tmp2.put(0, 1);
        tmp2.put(1, 2);
        tmp2.put(2, 1);

        Polynomial p1 = new Polynomial(tmp1);
        Polynomial p2 = new Polynomial(tmp2);

        System.out.println(p1.time(p2));

        assertEquals(1, p1.getPolynomialMap().size());
        assertEquals(0, (int) p1.getPolynomialMap().get(0));
    }

    @Test
    public void test_differentiate_constant_return_zero() {

        Polynomial p1 = new Polynomial(0, 7);

        System.out.println(p1.differentiate());

        assertEquals(1, p1.getPolynomialMap().size());
        assertEquals(0, (int) p1.getPolynomialMap().get(0));
    }

    @Test
    public void test_differentiate_polynomial_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, 1);
        tmp1.put(3, 5);
        tmp1.put(7, -2);

        Polynomial p1 = new Polynomial(tmp1);

        System.out.println(p1.differentiate());

        assertEquals(3, p1.getPolynomialMap().size());
        assertEquals(1, (int) p1.getPolynomialMap().get(0));
        assertEquals(15, (int) p1.getPolynomialMap().get(2));
        assertEquals(-14, (int) p1.getPolynomialMap().get(6));
    }

    @Test
    public void test_differentiate_polynomial_two_times_return_valid_polynomial() {

        TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
        tmp1.put(0, 1);
        tmp1.put(1, 1);
        tmp1.put(3, 5);
        tmp1.put(7, -2);

        Polynomial p1 = new Polynomial(tmp1);

        System.out.println(p1.differentiate().differentiate());

        assertEquals(2, p1.getPolynomialMap().size());
        assertEquals(30, (int) p1.getPolynomialMap().get(1));
        assertEquals(-84, (int) p1.getPolynomialMap().get(5));
    }
}