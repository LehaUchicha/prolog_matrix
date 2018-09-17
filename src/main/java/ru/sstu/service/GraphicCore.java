package ru.sstu.service;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ru.sstu.model.Matrix;

public class GraphicCore {

    public void drawMatrix(Pane panel, Matrix matrixData){
        panel.getChildren().clear();
        for(int i =0; i< matrixData.height(); i++){
            for (int j = 0; j<matrixData.width(); j++){
                TextField f = new TextField(matrixData.getElement(i, j)+"");

                f.setPrefSize(40, 40);
                f.setLayoutX(j*40 + 10);
                f.setLayoutY(i*40 + 10);
                panel.getChildren().add(f);
            }
        }
    }

    public void updateMatrix(Pane panel, Matrix matrixData){
//        for(int i =0; i< matrixData.height(); i++){
//            for (int j = 0; j<matrixData.width(); j++){
               panel.getChildren().stream().filter(el -> el instanceof TextField).forEach(el -> System.out.println(((TextField)el).getText()));
           // }
        //}
    }
}
