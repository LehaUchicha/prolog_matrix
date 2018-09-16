package ru.sstu.service;

import org.jpl7.Query;
import org.jpl7.Term;
import ru.sstu.model.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Alex on 15.09.2018.
 */
public class MatrixService {

    public Matrix sum(Matrix firstMatrix, Matrix secondMatrix) {
        Query query = new Query("consult('matrix.pl')");
        Matrix resultMatrix = new Matrix();
        if (query.hasSolution()) {
            Query q5 = new Query("matrix_sum(" + firstMatrix.asString() + secondMatrix.asString() + "M).");
            q5.open();
            Stream.of(q5.getSolution().get("M").toTermArray()).forEach(term -> resultMatrix.fillRow(getValue(term)));
            q5.close();
        }
        return resultMatrix;
    }

    public Matrix difference(Matrix firstMatrix, Matrix secondMatrix) {
        return new Matrix();
    }

    public Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) {
        return new Matrix();
    }

    public void determinant(Matrix matrix) {

    }

    public void invert(Matrix matrix) {

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
