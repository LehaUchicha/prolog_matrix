package ru.sstu.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {

    private Map<Integer, List<Double>> matrix;

    public Matrix(int rows) {
        this.matrix = new HashMap<Integer, List<Double>>();
        for (int i = 0; i < rows; i++) {
            List<Double> list = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                list.add(0.0);
            }
            matrix.put(i, list);
        }
    }

    public Matrix() {
        this.matrix = new HashMap<Integer, List<Double>>();
    }

    public void addRow() {
        if (matrix.size() == 0)
            matrix.put(0, Stream.of(0.0).collect(Collectors.toList()));
        else {
            List<Double> row = new ArrayList<>();
            for (int i = 0; i < matrix.get(0).size(); i++) {
                row.add(0.0);
            }
            matrix.put(matrix.keySet().size(), row);
        }
    }

    public void addColumn() {
        if (matrix.size() == 0)
            addRow();
        else
            matrix.values().forEach(row -> row.add(0.0));
    }

    public void fillRow(Integer rowNumber, List<Double> row) {
        matrix.put(rowNumber, row);
    }

    public void fillRow(List<Double> row) {
        matrix.put(matrix.size(), row);
    }

    public List<Double> getRow(Integer rowNumber) {
        return matrix.get(rowNumber);
    }

    public Double getElement(Integer rowNumber, Integer columnNumber) {
        return matrix.get(rowNumber).get(columnNumber);
    }

    public Double setElement(Integer rowNumber, Integer columnNumber, Double value) {
        return matrix.get(rowNumber).set(columnNumber, value);
    }

    public Integer width() {
        return matrix.get(0).size();
    }

    public Integer height() {
        return matrix.keySet().size();
    }

    public String asString() {
        StringBuilder string = new StringBuilder("[");
        for (int i = 0; i < matrix.size(); i++) {
            string.append("[");
            for (int j = 0; j < matrix.get(0).size(); j++) {
                string.append(matrix.get(i).get(j));
                string.append(",");
            }
            string.replace(string.length() - 1, string.length(), "],");
        }
        string.replace(string.length() - 1, string.length(), "],");
        return string.toString();
    }
}
