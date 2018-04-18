package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import com.dzieniu2.repository.ProductRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ProductListController {

    @FXML
    private GridPane productGrid;

    @FXML
    public void initialize() throws IOException {
        loadProducts();
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
                    AnchorPane pane = loader.load();
                    ProductContainerController productContainerController = loader.getController();
                    productContainerController.setName(thisProduct.getName());
                    Image image = new Image("/product-images/harnas.png");
                    productContainerController.setImage(image);
                    productGrid.add(pane,j,i);
                }
            }
        }
    }
}