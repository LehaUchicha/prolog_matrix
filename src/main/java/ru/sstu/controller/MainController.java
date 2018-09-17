package ru.sstu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ru.sstu.model.Matrix;
import ru.sstu.service.GraphicCore;
import ru.sstu.service.MatrixService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane firstMatrix;

    @FXML
    private Pane secondMatrix;

    @FXML
    private Pane resultMatrix;

    @FXML
    private Label determinantLabel;

    private Matrix firstMatrixData;

    private Matrix secondMatrixData;

    private GraphicCore graphic;

    private MatrixService matrixService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstMatrixData = new Matrix(1);
        secondMatrixData = new Matrix(1);
        graphic = new GraphicCore();
        matrixService = new MatrixService();
        graphic.drawMatrix(firstMatrix, firstMatrixData);
        graphic.drawMatrix(secondMatrix, secondMatrixData);
    }

    public void addRow(ActionEvent actionEvent) {
        firstMatrixData.addRow();
        graphic.drawMatrix(firstMatrix, firstMatrixData);
        System.out.println(firstMatrixData);
    }

    public void addColumn(ActionEvent actionEvent) {
        firstMatrixData.addColumn();
        graphic.drawMatrix(firstMatrix, firstMatrixData);
        System.out.println(firstMatrixData);
    }

    public void addRowSec(ActionEvent actionEvent) {
        secondMatrixData.addRow();
        graphic.drawMatrix(secondMatrix, secondMatrixData);
        System.out.println(secondMatrixData);
    }

    public void addColumnSec(ActionEvent actionEvent) {
        secondMatrixData.addColumn();
        graphic.drawMatrix(secondMatrix, secondMatrixData);
        System.out.println(secondMatrixData);
    }

    public void sum(ActionEvent actionEvent) {
        updateData();
        Matrix m = matrixService.sum(firstMatrixData, secondMatrixData);
        graphic.drawMatrix(resultMatrix, m);
    }

    public void diff(ActionEvent actionEvent) {
        updateData();
        Matrix result = matrixService.difference(firstMatrixData, secondMatrixData);
        graphic.drawMatrix(resultMatrix, result);
    }

    public void mult(ActionEvent actionEvent) {
        updateData();
        Matrix result = matrixService.multiply(firstMatrixData, secondMatrixData);
        graphic.drawMatrix(resultMatrix, result);
    }

    public void determinant(ActionEvent actionEvent) {
        updateData();
        Double determinant = matrixService.determinant(firstMatrixData);
        determinantLabel.setText(" Determinant: " + determinant);
    }

    public void invert(ActionEvent actionEvent) {
        updateData();
        Matrix result = matrixService.invert(firstMatrixData);
        graphic.drawMatrix(resultMatrix, result);
    }

    public void choleskyDecomposition(ActionEvent actionEvent) {
        updateData();
        Matrix result = matrixService.choleskyDecomposition(firstMatrixData);
        graphic.drawMatrix(resultMatrix, result);
    }

    private void updateData(Pane pane, Matrix data) {
        Integer count = 0;
        for (int i = 0; i < data.height(); i++) {
            for (int j = 0; j < data.width(); j++) {
                TextField field = (TextField) pane.getChildren().get(count);
                data.setElement(i, j, Integer.parseInt(field.getText()));
                count++;
            }
        }
    }

    protected void updateData() {
        updateData(firstMatrix, firstMatrixData);
        updateData(secondMatrix, secondMatrixData);
    }
}
