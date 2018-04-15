package com.dzieniu2.controller.employee;

import com.dzieniu2.entity.Product;
import com.dzieniu2.repository.ProductRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Iterator;
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

        for (int i = 0; i < products.size(); i++)
        {
                for(int j = 0; j < productGrid.getColumnConstraints().size(); j++)
                {
                    ProductContainerController PCController = new ProductContainerController(products.get(i));
                    System.out.println(products.get(i).getName().toString());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/ProductContainer.fxml"));
                    loader.setController(PCController);
                    VBox pContainer = loader.load();
                    productGrid.add(pContainer,j,rowNumber);

                    if(j == 3)
                        {
                            rowNumber++;
                            productGrid.addRow(rowNumber);
                            break;
                        }
                }

        }
    }

}

//        for (int i = 0; i < products.size()/4; i++) {
//            RowConstraints row = new RowConstraints();
//            row.setMinHeight(200);
//            productGrid.getRowConstraints().add(row);
//        }
//        Iterator<Product> iterator = products.iterator();
//
//        for (int i = 0; i < productGrid.getRowConstraints().size(); i++) {
//            for (int j= 0; j < 3; j++) {
//                if(iterator.hasNext()){
//                    ProductContainerController PCController = new ProductContainerController(products.get(i));
//
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/ProductContainer.fxml"));
//                    loader.setController(PCController);
//                    VBox pContainer = loader.load();
//                    productGrid.add(pContainer,i,j);
//                }
//            }
//
//        }


