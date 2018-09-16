package ru.sstu;

import org.jpl7.Query;
import org.junit.Test;

/**
 * Created by Alex on 16.09.2018.
 */
public class SumTest {

    @Test
    public void sum() {
        Query query = new Query("consult('matrix.pl')");
        if (query.hasSolution()) {
            Query q5 = new Query("matrix_sum([[-1,2],[-3,4]],[[1,2],[3,4]],M).");
            q5.open();
            //System.out.println(q5.getSolution().get("M").toTermArray()[0].);
            q5.close();
        }
    }
}
