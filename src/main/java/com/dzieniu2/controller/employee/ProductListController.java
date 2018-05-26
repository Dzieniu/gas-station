package com.dzieniu2.controller.employee;

import com.dzieniu2.controller.employee.transaction.ShoppingContainerController;
import com.dzieniu2.entity.Product;
import com.dzieniu2.entity.enums.ProductCategory;
import com.dzieniu2.repository.ProductRepository;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListController {

    @FXML
    private GridPane productGrid;

    @FXML
    private TextField filterProductField;

    @FXML
    private ChoiceBox categoryChoiceBox;

    @FXML
    private JFXScrollPane scrollPane;

    private VBox productList;

    private ArrayList<ShoppingContainerController> shoppingCart;

    @FXML
    public void initialize() throws IOException {
        BorderPane borderPane = new BorderPane();

        for (ProductCategory productCategory : ProductCategory.values()) {
            categoryChoiceBox.getItems().add(productCategory);
        }
        categoryChoiceBox.getSelectionModel().select(ProductCategory.ALL);

        filterProductField.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            try {
                loadProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label labelInfo = new Label("Product list");
        labelInfo.setFont(Font.font(30));
        labelInfo.setTextFill(Paint.valueOf("#ffffff"));
        borderPane.setCenter(labelInfo);

        borderPane.setPadding(new Insets(25,40,25,40));
        scrollPane.getTopBar().getChildren().add(borderPane);
        scrollPane.getCondensedHeader().setStyle("-fx-background-color: rgba(64, 89, 169, 0.7)");
        scrollPane.getMainHeader().setStyle("-fx-background-color: rgba(64, 89, 169, 1.0)");
        loadProducts();
    }

    public void loadProducts() throws IOException {

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.findAll();
        products = products.stream().filter(product -> {
            if(product.getCategory().equals(categoryChoiceBox.getSelectionModel().getSelectedItem()) ||
                    categoryChoiceBox.getSelectionModel().getSelectedItem().equals(ProductCategory.ALL)) return true;
            return false;
        }).filter(product -> product.getName().toLowerCase().contains(filterProductField.getText().toLowerCase())).collect(Collectors.toList());
        productGrid.getChildren().clear();
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

                    productGrid.add(pane,j,i);
                }
            }
        }
    }
}