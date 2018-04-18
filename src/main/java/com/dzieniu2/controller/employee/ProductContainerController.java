package com.dzieniu2.controller.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductContainerController {

    @FXML
    Label nameLabel;

    @FXML
    ImageView imageView;

    @FXML
    TextField amountField;

    @FXML
    Button lessButton,moreButton,addButton;

    @FXML
    public void initialize(){

    }

    public void setName(String text){
        nameLabel.setText(text);
    }

    public void setImage(Image image){
        imageView.setImage(image);
    }
}
