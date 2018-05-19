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
    private Label nameLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField amountField;

    @FXML
    private Button lessButton,moreButton;

    private Product product;

    private int remaining,amount;

    @FXML
    public void initialize(Product product){
        this.product = product;
        this.remaining = product.getRemaining();
        this.amount = 0;
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

    public Button getLessButton() {
        return lessButton;
    }

    public Button getMoreButton() {
        return moreButton;
    }

    public void add(){
        if(remaining>0) amount++;
    }

    public void setAmount(int value){
        if(value<=product.getRemaining()) amount = value;
        else amount = product.getRemaining();
    }

    public void remove(){
        if(amount>0) amount--;
    }

    public void removeAll(){
        amount = 0;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getAmount() {
        return amount;
    }
}
