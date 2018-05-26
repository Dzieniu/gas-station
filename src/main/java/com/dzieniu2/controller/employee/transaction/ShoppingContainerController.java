package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.ByteArrayInputStream;

public class ShoppingContainerController {

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
        amountField.setOnKeyPressed(key -> {
            if (key.getCode().equals(KeyCode.ENTER)) setAmount(Integer.parseInt(amountField.getText()));
        });
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
        if(amount<remaining) amount++;
        amountField.setText(amount+"");
    }

    public void setAmount(int value){
        if(value<=product.getRemaining()) amount = value;
        else amount = product.getRemaining();
        amountField.setText(amount+"");
    }

    public void remove(){
        if(amount>0) amount--;
        amountField.setText(amount+"");
    }

    public void removeAll(){
        amount = 0;
        amountField.setText(amount+"");
    }

    public int getRemaining() {
        return remaining;
    }

    public int getAmount() {
        return amount;
    }
}
