package ru.sstu;

import org.jpl7.Query;
import org.jpl7.Term;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiplicationTest {

    @Test
    public void test() {

        Query query = new Query("consult('matrix.pl')");

        if (query.hasSolution()) {
            Query q4 = new Query("matrix_multiply([[1,2],[3,4],[5,6]], [[1,1,1],[1,1,1]],R).");
            q4.open();
            Term[] terms = q4.getSolution().get("R").toTermArray();

            List<Integer> matrix = new ArrayList();

            for (int i = 0; i < terms.length; i++) {
                matrix.addAll(getValue(terms[i]));
            }
            Assert.assertEquals(matrix, Stream.of(3, 3, 3, 7, 7, 7, 11, 11, 11).collect(Collectors.toList()));
            q4.close();
        }
    }

    protected List<Integer> getValue(Term term) {
        List<Integer> list = new ArrayList<>();
        if (term.arg(2).isAtom()) {
            list.add(term.arg(1).intValue());
            return list;
        }
        list.add(term.arg(1).intValue());
        list.addAll(getValue(term.arg(2)));
        return list;
    }
}
