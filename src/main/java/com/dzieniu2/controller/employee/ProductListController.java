package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import com.dzieniu2.repository.ProductRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class ProductListController
{

    @FXML
    private GridPane productGrid;

    @FXML
    public void initialize() throws IOException
    {

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.findAll();
        int rowNumber = 0;

        for (Product product : products) {
            for (int j = 0; j < productGrid.getColumnConstraints().size(); j++) {
                ProductContainerController PCController = new ProductContainerController(product);
                System.out.println(product.getName());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/ProductContainer.fxml"));
                loader.setController(PCController);
                VBox pContainer = loader.load();
                productGrid.add(pContainer, j, rowNumber);

                if (j == 3) {
                    rowNumber++;
                    productGrid.addRow(rowNumber);
                    break;
                }
            }

        }
    }

}



