package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.entity.Product;
import com.dzieniu2.entity.ProductCategory;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.repository.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductController {

    @FXML
    private TabPane productPane;

    @FXML
    private TextField nameFieldCreate,priceFieldCreate,remainingFieldCreate,pathFieldCreate,nameFieldUpdate,priceFieldUpdate,
            remainingFieldUpdate,pathFieldUpdate;

    @FXML
    private Label idBox;

    @FXML
    private ChoiceBox categoryBoxCreate,categoryBoxUpdate;

    private AdminController adminController;

    private Product selectedProduct;

    @FXML
    public void createProduct(){
        ProductRepository productRepository = new ProductRepository();
        Product product = new Product();
        product.setName(nameFieldCreate.getText());
        product.setPrice(Double.parseDouble(priceFieldCreate.getText()));
        product.setRemaining(Integer.parseInt(remainingFieldCreate.getText()));
        product.setPath(pathFieldCreate.getText());
        product.setCategory((ProductCategory) categoryBoxCreate.getSelectionModel().getSelectedItem());
        productRepository.add(product);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateProduct(){
        ProductRepository productRepository = new ProductRepository();
        selectedProduct.setName(nameFieldUpdate.getText());
        selectedProduct.setPrice(Double.parseDouble(priceFieldUpdate.getText()));
        selectedProduct.setRemaining(Integer.parseInt(remainingFieldUpdate.getText()));
        selectedProduct.setPath(pathFieldUpdate.getText());
        selectedProduct.setCategory((ProductCategory) categoryBoxUpdate.getSelectionModel().getSelectedItem());
        productRepository.update(selectedProduct);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteProduct(){
        ProductRepository productRepository = new ProductRepository();
        productRepository.delete(selectedProduct.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;

        categoryBoxUpdate.getItems().addAll(ProductCategory.values());
        categoryBoxCreate.getItems().addAll(ProductCategory.values());

        idBox.setText(selectedProduct.getId().toString());
        nameFieldUpdate.setText(selectedProduct.getName());
        priceFieldUpdate.setText(selectedProduct.getPrice().toString());
        remainingFieldUpdate.setText(selectedProduct.getRemaining().toString());
        pathFieldUpdate.setText(selectedProduct.getPath());
        categoryBoxUpdate.getSelectionModel().select(selectedProduct.getCategory());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) productPane.getScene().getWindow();
        stage.close();
    }
}
