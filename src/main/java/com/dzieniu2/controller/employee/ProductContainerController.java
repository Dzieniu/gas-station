package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.ByteArrayInputStream;

public class ProductContainerController {

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label remainingLabel;

    private Product product;

    private int remaining;

    @FXML
    public void initialize(Product product){
        this.product = product;
        this.remaining = product.getRemaining();
        this.remainingLabel.setText("Remaining: "+remaining);
        nameLabel.setText(product.getName());
        Image image = new Image(new ByteArrayInputStream(product.getImage()));
        imageView.setImage(image);

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
