package com.dzieniu2.controller;

import com.dzieniu2.entity.Product;
import com.dzieniu2.repository.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Iterator;
import java.util.List;

public class ProductListController {

    @FXML
    private GridPane productGrid;

    @FXML
    public void initialize(){

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size()/4; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(200);
            productGrid.getRowConstraints().add(row);
        }
        Iterator<Product> iterator = products.iterator();
        Button button;
        for (int i = 0; i < productGrid.getRowConstraints().size(); i++) {
            for (int j = 0; j < 4; j++) {
                if(iterator.hasNext()){
                    button = new Button(iterator.next().getName());
                    button.setPrefWidth(355);
                    button.setPrefHeight(355);
                    productGrid.add(button,j,i);
                }
            }
        }
    }
}
