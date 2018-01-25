import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class PolynomialTest {

    @Test
    public void test_constructor_no_param_return_0() {
        Polynomial polynomial = new Polynomial();


        for (Map.Entry<Integer, Integer> polinomialEntry : polynomial.getPolynomialMap().entrySet()) {
            assertEquals((Integer) 0, polinomialEntry.getKey());
            assertEquals((Integer) 0, polinomialEntry.getValue());
        }

        System.out.println(polynomial);
    }

    @Test
    public void test_constructor_with_param_return_polynomial() {
        Polynomial polynomial = new Polynomial(2, 5);


        for (Map.Entry<Integer, Integer> polinomialEntry : polynomial.getPolynomialMap().entrySet()) {
            assertEquals((Integer) 2, polinomialEntry.getKey());
            assertEquals((Integer) 5, polinomialEntry.getValue());
        }

        System.out.println(polynomial);
    }

    @Test
    public void test_constructor_with_map_param_return_polynomial() {

        TreeMap<Integer, Integer> tmp = new TreeMap<>();
        tmp.put(1, 3);
        tmp.put(3, 2);

        Polynomial polynomial = new Polynomial(tmp);

        assertEquals(2, polynomial.getPolynomialMap().size());
        assertEquals((Integer) 3, polynomial.getPolynomialMap().get(1));
        assertEquals((Integer) 2, polynomial.getPolynomialMap().get(3));

        System.out.println(polynomial);
    }

//    @Test
//    public void test_plus_two_polynomials_return_valid_polynomial() {
//
//        TreeMap<Integer, Integer> tmp = new TreeMap<>();
//        tmp.put(0, 1);
//        tmp.put(1, 2);
//        tmp.put(2, 1);
//
//        Polynomial polynomial = new Polynomial(tmp);
//
//        assertEquals(2, polynomial.getPolynomialMap().size());
//        assertEquals((Integer) 3, polynomial.getPolynomialMap().get(1));
//        assertEquals((Integer) 2, polynomial.getPolynomialMap().get(3));
//
//        System.out.println(polynomial);
//    }
}