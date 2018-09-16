package ru.sstu;

import org.jpl7.Query;

/**
 * Created by Alex on 16.09.2018.
 */
public class DifferenceTest {

    public void test(){
        Query q6 = new Query("matrix_diff([[-1,2],[-3,4]],[[1,2],[3,4]],M).");
        q6.open();
        System.out.println(q6.getSolution().get("M"));
        q6.close();
    }
}
