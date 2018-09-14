package ru.sstu.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {

    private Map<Integer, List<Integer>> matrix;

    public Matrix() {
        this.matrix = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < 3; i++) {
            fillRow(i, Stream.of(0, 0, 0).collect(Collectors.toList()));
        }
    }

    public void addRow() {
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i < matrix.get(0).size(); i++){
            row.add(0);
        }
        matrix.put(matrix.keySet().size(), row);
    }

    public void addColumn() {
        matrix.values().forEach(row -> row.add(0));
    }

    public void fillRow(Integer rowNumber, List<Integer> row) {
        matrix.put(rowNumber, row);
    }

    public List<Integer> getRow(Integer rowNumber) {
        return matrix.get(rowNumber);
    }

    public Integer getElement(Integer rowNumber, Integer columnNumber) {
        return matrix.get(rowNumber).get(columnNumber);
    }

    public Integer width() {
        return matrix.get(0).size();
    }

    public Integer height() {
        return matrix.keySet().size();
    }
}
