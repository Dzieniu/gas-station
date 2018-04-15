package com.dzieniu2.controller.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.dzieniu2.entity.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductContainerController {

    @FXML
    private Label productName;

    @FXML
    private ImageView productImg;

    @FXML
    private Button decreaseBtn;

    @FXML
    private TextField amountField;

    @FXML
    private Button increaseBtn;

    @FXML
    private Button addProductBtn;

    private Product ID;
    public ProductContainerController(Product ID)
    {
        this.ID = ID;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        productName.setText(ID.getName());
        //FileInputStream input = new FileInputStream("/product-images/"+ ID.getPath());
        //Image image = new Image(input);
        //productImg.imageProperty().setValue(image);

    }


}
