package ru.sstu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import ru.sstu.model.Matrix;
import ru.sstu.service.GraphicCore;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private Pane matrix;

    private Matrix matrixData;

    private GraphicCore graphic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matrixData = new Matrix();
        graphic = new GraphicCore();
        graphic.drawMatrix(matrix, matrixData);
    }

    public void addRow(ActionEvent actionEvent) {
        matrixData.addRow();
        graphic.drawMatrix(matrix, matrixData);
        System.out.println(matrixData);
    }

    public void addColumn(ActionEvent actionEvent) {
        matrixData.addColumn();
        graphic.drawMatrix(matrix, matrixData);
        System.out.println(matrixData);
    }


    public void update(ActionEvent actionEvent) {
        graphic.updateMatrix(matrix, matrixData);
    }
}
