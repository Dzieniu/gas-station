package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Product;
import com.dzieniu2.entity.enums.ProductCategory;
import com.dzieniu2.repository.ProductRepository;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListController {

    @FXML
    private GridPane productGrid;

    @FXML
    private TextField filterProductField;

    @FXML
    private ChoiceBox categoryChoiceBox;

    @FXML
    private JFXScrollPane scrollPane;

    private TransactionController transactionController;

    private VBox productList;

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

        Label labelInfo = new Label("1. Choose products");
        labelInfo.setFont(Font.font(30));
        labelInfo.setTextFill(Paint.valueOf("#ffffff"));

        JFXButton buttonPrevious = new JFXButton("<-");
        buttonPrevious.setFont(Font.font(15));
        buttonPrevious.setButtonType(JFXButton.ButtonType.RAISED);
        buttonPrevious.setStyle("-fx-background-color: #ffffff;-fx-pref-width: 60;-fx-pref-height: 50");
        buttonPrevious.setVisible(false);

        JFXButton buttonNext = new JFXButton("->");
        buttonNext.setFont(Font.font(15));
        buttonNext.setButtonType(JFXButton.ButtonType.RAISED);
        buttonNext.setStyle("-fx-background-color: #ffffff;-fx-pref-width: 60;-fx-pref-height: 50");
        buttonNext.setOnMouseClicked(event -> toDispensers());

        borderPane.setLeft(buttonPrevious);
        borderPane.setRight(buttonNext);
        borderPane.setCenter(labelInfo);
        borderPane.setPadding(new Insets(25,20,20,40));
        scrollPane.getTopBar().getChildren().add(borderPane);
        scrollPane.getMainHeader().setStyle("-fx-pref-height: 600");
        scrollPane.getCondensedHeader().setStyle("-fx-background-color: rgba(64, 89, 169, 0.7)");
        scrollPane.getMainHeader().setStyle("-fx-background-color: rgba(64, 89, 169, 1.0)");
        loadProducts();

        productList = new VBox();
        borderPane.setBottom(productList);
        borderPane.getBottom().setStyle("-fx-padding: 20,20,20,20");
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
                    loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ShoppingContainer.fxml"));
                    BorderPane pane = loader.load();
                    ShoppingContainerController shoppingContainerController = loader.getController();
                    shoppingContainerController.initialize(thisProduct);

                    shoppingContainerController.getLessButton().setOnAction(event -> {
                        if(transactionController.getChosenProducts().contains(shoppingContainerController)){
                            shoppingContainerController.remove();
                            if(shoppingContainerController.getAmount()==0)
                                transactionController.getChosenProducts().remove(shoppingContainerController);
                        }
                        printShoppingCart();
                    });
                    shoppingContainerController.getMoreButton().setOnAction(event -> {
                        if(transactionController.getChosenProducts().contains(shoppingContainerController)){
                            shoppingContainerController.add();
                        }else{
                            transactionController.getChosenProducts().add(shoppingContainerController);
                            shoppingContainerController.add();
                        }
                        printShoppingCart();
                    });

                    productGrid.add(pane,j,i);
                }
            }
        }
    }

    public void setTransactionController(TransactionController transactionController){
        this.transactionController = transactionController;
    }

    public void printShoppingCart(){

        transactionController.getChosenProducts().forEach(product -> System.out.println(product.getProduct().getName()+", "+product.getAmount()+"/"+product.getProduct().getRemaining()));
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

    public void toDispensers() {
        transactionController.openDispenserWindow();
    }
}