package ru.sstu;

import org.jpl7.Query;
import org.jpl7.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * determinant for positive semi-definite matrices (using Cholesky decomposition)
 */
public class DeterminantTest {

    @Test
    public void test(){
        Query query = new Query("consult('matrix.pl')");

        if (query.hasSolution()) {

            Query q2 = new Query("determinant([[2,-1,0],[-1,2,-1],[0,-1,2]],D)");
            if (q2.hasMoreSolutions()) {
                Map<String, Term> sol = q2.nextSolution();
                Assert.assertEquals(4.0, sol.get("D").doubleValue(), 0.0000001);
            }
            q2.close();
        }
    }
}
