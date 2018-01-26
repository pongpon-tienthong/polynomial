# Instruction
You're an esteemed engineer at Math & Co. in their Algebraic Infrastructure team. The company is pivoting towards using this new technology that is based on polynomials and many of your peers are on the verge of creating servers that will crunch polynomials. You have been tasked with designing a class to optimally represent a polynomial for their use. This class would need to be high-performance and cater to the operations that can be performed on polynomials. Please provide commentary on your thought process in your design and the code for this class in a language of your choice.

# Description
I based my decision on three significant factors namely space complexity, time complexity, and maintainability. My concern is for those higher-degree polynomials since each operation which is done on smaller-degree polynomials would not yield much difference in term of the three aspects mentioned earlier. There are two properties which the class needs to have: the first being the degree of the polynomial and the second being the coefficient. The data structure implemented in the class need to facilitate storing the degree and the corresponding coefficient. I consider constructing my polynomial class using either an array, treemap, or linkedlist as the backbone of the class. 

With regards to an array, we can use the indexes of an array to represent the degrees and the values to represent the corresponding coefficients. It is undeniable that in the case of small-degree polynomials, this structure consumes less space compared to the linkedlist or treemap structure. However, this is not the case of large-sized polynomials. For instance, if we want to create a 7x^20 polynomial, we have to create an array with 21 slots; the index 20 holds the value 7 while others hold the value 0. Since we do not need the other terms after from the index 20 to display the data or do other operations, they are unnecessary. In addition, the array structure is not preferable when we perform multiplication on polynomials since we are required to create at least an identical-sized temp array. Thus, this structure consumes unnecessarily a considerable amount of space. We can resolve this issue by using either a linkedlist or treemap. We can easily add each polynomial to these data structure without having to store additional terms. Regarding space complexity, the linkedlist and treemap structure are preferable to the array one. 

Furthermore, both structures are superior to the array structure in terms of the time complexity. The linkedlist and treemap structure do not need to loop through unnecessary loops while the array structure is required to do so. All in all, we can conclude that the space and time complexity of both linkedlist and treemap structures increase in a smaller manner than the array structure does as the degree of the polynomials increases.

In order to display a polynomial in ascending order according to its degree, we need to be able to sort the data stored in the structure. It is extremely hard to sort a linkedlist since it has not been designed to do so. If we were to create a sorting function to sort a linkedlist, the function would be immensely complicated, impairing the maintainability of the code. As a result, sorting is not an issue for treemap since once the node is added to the treemap, it will be automatically sorted. We can ensure that the data in treemap remain sorted. Therefore, we can hugely reduce the complexity of the code.

All in all, due to the reasons mentioned in the preceding paragraphs, I will choose to implement a polynomial class using the treemap structure. I implemented all of the operations which can be performed on polynomials. In addition to that, I created unit tests to ensure that each method works correctly and  to demonstrate how each method can be used.

# Run
```
cd PROJECT_PATH
./gradlew clean build
./gradlew test # to run all tests
```

# Polynomial Class
```java
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
     * Example:
     *
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

    /**
     * Example:
     *
     * TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
     * tmp1.put(0, 7);
     * tmp1.put(1, -3);
     * tmp1.put(2, -4);
     *
     * TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
     * tmp2.put(0, 1);
     * tmp2.put(1, 4);
     * tmp2.put(2, -4);
     * tmp2.put(3, 4);
     *
     * Polynomial p1 = new Polynomial(tmp1); // p1 = 7 - 3x -4x^2
     * Polynomial p2 = new Polynomial(tmp2); // p2 = 1 + 4x -4x^2 + 4x^3
     *
     * System.out.println(p1.minus(p2)); // p1 - p2 = 6 - 7x - 4x^3
     */
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

    /**
     * Example:
     *
     * TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
     * tmp1.put(0, 1);
     * tmp1.put(1, 1);
     *
     * TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
     * tmp2.put(0, -1);
     * tmp2.put(1, 1);
     *
     * Polynomial p1 = new Polynomial(tmp1); // p1 = 1 + x
     * Polynomial p2 = new Polynomial(tmp2); // p2 = -1 + x
     *
     * System.out.println(p1.time(p2)); // p1 * p2 = -1 + x^2
     */
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

    /**
     * Example:
     *
     * TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
     * tmp1.put(0, 1);
     * tmp1.put(1, 1);
     * tmp1.put(3, 5);
     * tmp1.put(7, -2);
     *
     * Polynomial p1 = new Polynomial(tmp1); // p1 = 1 + x + 5x^3 - 2x^7
     *
     * System.out.println(p1.differentiate()); // p1ʹ = 1 + 15x^2 - 14x^6
     */
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
```

# Test
```java
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
```

# Example

### Initialize polynomial
```java

Polynomial p1 = new Polynomial(); // p1 = 0
Polynomial p2 = new Polynomial(2, 5); // p2 = 5x^2

TreeMap<Integer, Integer> tmp = new TreeMap<>();
tmp.put(1, 3);
tmp.put(3, 2);

Polynomial p3 = new Polynomial(tmp); // p3 = 3x + x^3
```

### Plus
```java
TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
tmp1.put(0, 1);
tmp1.put(1, -2);
tmp1.put(2, 1);

TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
tmp2.put(0, -1);
tmp2.put(1, 4);
tmp2.put(3, -4);

Polynomial p1 = new Polynomial(tmp1); // p1 = 1 - 2x + x^2
Polynomial p2 = new Polynomial(tmp2); // p2 = -1 + 4x - 4x^3

System.out.println(p1.plus(p2)); // p1 + p2 = 2x + x^2 - 4x^3
```

### Minus
```java
TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
tmp1.put(0, 7);
tmp1.put(1, -3);
tmp1.put(2, -4);

TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
tmp2.put(0, 1);
tmp2.put(1, 4);
tmp2.put(2, -4);
tmp2.put(3, 4);

Polynomial p1 = new Polynomial(tmp1); // p1 = 7 - 3x -4x^2
Polynomial p2 = new Polynomial(tmp2); // p2 = 1 + 4x -4x^2 + 4x^3 

System.out.println(p1.minus(p2)); // p1 - p2 = 6 - 7x - 4x^3
```

### Time
```java
TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
tmp1.put(0, 1);
tmp1.put(1, 1);

TreeMap<Integer, Integer> tmp2 = new TreeMap<>();
tmp2.put(0, -1);
tmp2.put(1, 1);

Polynomial p1 = new Polynomial(tmp1); // p1 = 1 + x
Polynomial p2 = new Polynomial(tmp2); // p2 = -1 + x

System.out.println(p1.time(p2)); // p1 * p2 = -1 + x^2
```

### Differentiate
```java
TreeMap<Integer, Integer> tmp1 = new TreeMap<>();
tmp1.put(0, 1);
tmp1.put(1, 1);
tmp1.put(3, 5);
tmp1.put(7, -2);

Polynomial p1 = new Polynomial(tmp1); // p1 = 1 + x + 5x^3 - 2x^7

System.out.println(p1.differentiate()); // p1ʹ = 1 + 15x^2 - 14x^6
```