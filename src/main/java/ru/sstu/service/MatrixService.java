package ru.sstu.service;

import org.jpl7.Query;
import org.jpl7.Term;
import ru.sstu.model.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Alex on 15.09.2018.
 */
public class MatrixService {

    private static final String RESULT = "M";

    public Matrix sum(Matrix firstMatrix, Matrix secondMatrix) {
        return operation(firstMatrix, secondMatrix, "matrix_sum");
    }

    public Matrix difference(Matrix firstMatrix, Matrix secondMatrix) {
        return operation(firstMatrix, secondMatrix, "matrix_diff");
    }

    public Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) {
        return operation(firstMatrix, secondMatrix, "matrix_multiply");
    }

    /**
     * Determinant could be calculated only for square matrices
     */
    public Double determinant(Matrix matrix) {
        Query query = new Query("consult('matrix.pl')");

        if (query.hasSolution()) {
            Query q2 = new Query("determinant(" + matrix.asString() + RESULT + ")");
            if (q2.hasMoreSolutions()) {
                Map<String, Term> sol = q2.nextSolution();
                return sol.get(RESULT).doubleValue();
            }
            q2.close();
        }
        return null;
    }

    /**
     * positive semi-definite matrix have condition
     * xT * A * x > 0.
     * <p>
     * [2, -1, 0]      (x1)
     * (x1, x2, x3)  *  [-1, 2, -1]  *  (x2)  => 2 * (x1 - x2/2)^2 + 3/2*(x2-2*x3/3)^2 + 2/3*x3^2 > 0.
     * [0, -1, 2]      (x3)
     * <p>
     * Матрица положительно полуопределена, если выполняются условия:
     * 1) Все диагональные элементы неотрицательны;
     * 2) Все главные определители неотрицательны.
     */
    public Matrix invert(Matrix matrix) {
        return operation(matrix, "matrix_inversion");
    }

    public Matrix invertTriang(Matrix matrix) {
        return operation(matrix, "matrix_inv_triang");
    }

    public Matrix choleskyDecomposition(Matrix matrix) {
        return operation(matrix, "cholesky_decomposition");
    }

    protected Matrix operation(Matrix matrix, String operation) {
        Query query = new Query("consult('matrix.pl')");
        Matrix resultMatrix = new Matrix();
        if (query.hasSolution()) {
            Query q5 = new Query(operation + "(" + matrix.asString() + RESULT + ").");
            q5.open();
            Stream.of(q5.getSolution().get(RESULT).toTermArray()).forEach(term -> resultMatrix.fillRow(getValue(term)));
            q5.close();
        }
        return resultMatrix;
    }

    protected Matrix operation(Matrix firstMatrix, Matrix secondMatrix, String operation) {
        Query query = new Query("consult('matrix.pl')");
        Matrix resultMatrix = new Matrix();
        if (query.hasSolution()) {
            Query q5 = new Query(operation + "(" + firstMatrix.asString() + secondMatrix.asString() + RESULT + ").");
            q5.open();
            Stream.of(q5.getSolution().get(RESULT).toTermArray())
                    .forEach(term -> resultMatrix.fillRow(getValue(term)));
            q5.close();
        }
        return resultMatrix;
    }

    protected List<Double> getValue(Term term) {
        List<Double> list = new ArrayList<>();
        if (term.arg(2).isAtom()) {
            list.add(term.arg(1).doubleValue());
            return list;
        }
        list.add(term.arg(1).doubleValue());
        list.addAll(getValue(term.arg(2)));
        return list;
    }
}
