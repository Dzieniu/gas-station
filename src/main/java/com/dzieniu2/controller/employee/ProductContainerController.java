package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class ProductContainerController {

    @FXML
    Label nameLabel;

    @FXML
    ImageView imageView;

    @FXML
    TextField amountField;

    @FXML
    Button lessButton,moreButton;

    private Product product;

    @FXML
    public void initialize(Product product){
        this.product = product;
        nameLabel.setText(product.getName());
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        imageView.setImage(image);
    }

    public void setAmount(){

    }

    public Button getLessButton() {
        return lessButton;
    }

    public Button getMoreButton() {
        return moreButton;
    }

    public TextField getAmountField() {
        return amountField;
    }
}
