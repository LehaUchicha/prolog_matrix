package ru.sstu;

import org.jpl7.Query;
import org.jpl7.Term;
import org.junit.Test;

/**
 * Created by Alex on 16.09.2018.
 */
public class CholseskyDecompositionTest {

    @Test
    public void test(){
        Query q7 = new Query("cholesky_decomposition([[25, 15, -5], [15, 18,  0], [-5,  0, 11]],L).");
        q7.open();
        Term term = q7.getSolution().get("L");
        System.out.println(q7.getSolution().get("L"));
        q7.close();
    }
}
