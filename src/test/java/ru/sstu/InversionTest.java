package ru.sstu;

import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Map;

/**
 *  - inversion for positive semi-definite matrices (using Cholesky decomposition).
 *  - inversion for lower triangular matrices.
 */
public class InversionTest {

    public void test(){
        Query q3 = new Query("matrix_inversion([[2,-1,0],[-1,2,-1],[1,-1,2]],L).");
        q3.open();
        Map<String, Term> sol = q3.getSolution();
        System.out.println(sol.get("L"));
        q3.close();
    }
}
