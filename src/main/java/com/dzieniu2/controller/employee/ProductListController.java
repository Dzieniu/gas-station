package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import com.dzieniu2.other.ShoppingCart;
import com.dzieniu2.repository.ProductRepository;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ProductListController {

    @FXML
    private GridPane productGrid;

    @FXML
    private JFXScrollPane scrollPane;

    private VBox productList;

    private ArrayList<ProductContainerController> shoppingCart;

    @FXML
    public void initialize() throws IOException {
        BorderPane borderPane = new BorderPane();

        JFXButton buttonBack = new JFXButton("<-");
        buttonBack.setButtonType(JFXButton.ButtonType.RAISED);
        buttonBack.setStyle("-fx-background-color: #ffffff");

        Label labelInfo = new Label("1. Wybierz produkty");
        labelInfo.setFont(Font.font(20));
        labelInfo.setTextFill(Paint.valueOf("#ffffff"));

        JFXButton buttonNext = new JFXButton("->");
        buttonNext.setButtonType(JFXButton.ButtonType.RAISED);
        buttonNext.setStyle("-fx-background-color: #ffffff");

        borderPane.setLeft(buttonBack);
        borderPane.setRight(buttonNext);
        borderPane.setCenter(labelInfo);
        borderPane.setPadding(new Insets(25,20,25,20));
        scrollPane.getTopBar().getChildren().add(borderPane);
        loadProducts();



        productList = new VBox();
        borderPane.setBottom(productList);
        borderPane.getBottom().setStyle("-fx-padding: 20,20,20,20");
        shoppingCart = new ArrayList<>();
    }

    public void loadProducts() throws IOException {

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size()/4; i++) {
            RowConstraints row = new RowConstraints();
            productGrid.getRowConstraints().add(row);
        }
        Iterator<Product> iterator = products.iterator();
        FXMLLoader loader;
        for (int i = 0; i < productGrid.getRowConstraints().size(); i++) {
            for (int j = 0; j < 4; j++) {
                if(iterator.hasNext()){
                    Product thisProduct = iterator.next();
                    loader = new FXMLLoader(getClass().getResource("/fxml/employee/ProductContainer.fxml"));
                    BorderPane pane = loader.load();
                    ProductContainerController productContainerController = loader.getController();
                    productContainerController.initialize(thisProduct);

                    productContainerController.getLessButton().setOnAction(event -> {
                        if(shoppingCart.contains(productContainerController)){
                            productContainerController.remove();
                            if(productContainerController.getAmount()==0)
                                shoppingCart.remove(productContainerController);
                        }
                        printShoppingCart();
                    });
                    productContainerController.getMoreButton().setOnAction(event -> {
                        if(shoppingCart.contains(productContainerController)){
                            productContainerController.add();
                        }else{
                            shoppingCart.add(productContainerController);
                            productContainerController.add();
                        }
                        printShoppingCart();
                    });

                    productGrid.add(pane,j,i);
                }
            }
        }
    }

    public void printShoppingCart(){

        shoppingCart.forEach(product -> System.out.println(product.getProduct().getName()+", "+product.getAmount()+"/"+product.getProduct().getRemaining()));
        System.out.println();

//        productList.getChildren().clear();
//        shoppingCart.forEach(product -> {
//            Label productLabel = new Label(product.getProduct().getName()+" x "+product.getAmount()+"/"+product.getProduct().getRemaining());
//            productLabel.setPadding(new Insets(10,0,0,0));
//            productLabel.setFont(Font.font(18));
//            productLabel.setTextFill(Paint.valueOf("#ffffff"));
//            productLabel.setStyle("-fx-background-color: rgba(8, 21, 32, 0.7);-fx-padding: 10,10,10,10");
//            productList.getChildren().add(productLabel);
//        });
    }
}