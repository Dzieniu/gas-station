package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;
import com.dzieniu2.repository.FuelTransactionRepository;
import com.dzieniu2.repository.ProductRepository;
import com.dzieniu2.repository.ProductTransactionRepository;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDateTime;

public class SummaryController {

    @FXML
    private AnchorPane dispenserPane,customerPane;

    @FXML
    private JFXListView productList;

    @FXML
    private Label nameLabel,litersLabel,costLabel,priceLabel,fuelLeftLabel,percentLeftLabel,customerNameLabel, customerRegisterDateLabel,
        dispenserInfoLabel,customerInfoLabel,productsInfoLabel,chosenProductsLabel,currentDateLabel,totalCostLabel;

    @FXML
    private Button finalizeButton;

    private TransactionController transactionController;

    @FXML
    public void initialize(){
        dispenserPane.setVisible(false);
        customerPane.setVisible(false);
    }

    @FXML
    public void toClientCheck() throws IOException {
        transactionController.toClientCheck();
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;

        double totalCost = 0.0;
        if(transactionController.getFuelDispenser()!=null){
            dispenserPane.setVisible(true);
            nameLabel.setText(transactionController.getFuelDispenser().getContainer().getId()+" - "+transactionController.getFuelDispenser().getContainer().getFuel().getName());
            litersLabel.setText((Math.round(transactionController.getFuelDispenser().getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            costLabel.setText((Math.round(transactionController.getFuelDispenser().getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            priceLabel.setText(transactionController.getFuelDispenser().getContainer().getFuel().getPrice()+"PLN/dm³");
            fuelLeftLabel.setText(transactionController.getFuelDispenser().getContainer().getFuelLeft()+"dm³");
            percentLeftLabel.setText("( "+(Math.round((transactionController.getFuelDispenser().getContainer().getFuelLeft()/transactionController.getFuelDispenser().getContainer().getMaxCapacity())*Math.pow(10, 2))/Math.pow(10, 2))+"% )");
            dispenserInfoLabel.setText("Fuel cost: "+(Math.round(transactionController.getFuelDispenser().getCost()*Math.pow(10, 2))/Math.pow(10, 2))+" PLN");
            totalCost += transactionController.getFuelDispenser().getCost();
        }
        if(transactionController.getCustomer()!=null){
            customerPane.setVisible(true);
            customerNameLabel.setText(transactionController.getCustomer().getName()+" "+transactionController.getCustomer().getSurname());
            customerRegisterDateLabel.setText(transactionController.getCustomer().getRegisterDate().toString());
            customerInfoLabel.setText("");
        }
        if(!transactionController.getShoppingCart().isEmpty()){
            transactionController.getShoppingCart().forEach(product -> {
                productList.getItems().add(product.getProduct().getName()+" x "+product.getAmount()+" x "+product.getProduct().getPrice()+"PLN");
            });
            chosenProductsLabel.setText("Chosen products");
            double cost = 0.0;
            for(ShoppingContainerController product :transactionController.getShoppingCart()){
                cost += product.getProduct().getPrice()*product.getAmount();
            }
            totalCost += cost;
            productsInfoLabel.setText("Total products cost: "+(Math.round(cost*Math.pow(10, 2))/Math.pow(10, 2)));
        }
        currentDateLabel.setText(LocalDateTime.now().toString());
        totalCostLabel.setText((Math.round(totalCost*Math.pow(10, 2))/Math.pow(10, 2))+" PLN");
    }

    @FXML
    public void finalizeTransaction(){

        if(transactionController.getFuelDispenser()!=null){
            FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
            TransactionFuel transactionFuel = new TransactionFuel();
            transactionFuel.setCustomer(transactionController.getCustomer());
            transactionFuel.setEmployee(transactionController.getEmployeeController().getMainController().getEmployee());
            transactionFuel.setFuel(transactionController.getFuelDispenser().getContainer().getFuel());
            transactionFuel.setFuelPrice(transactionController.getFuelDispenser().getContainer().getFuel().getPrice());
            transactionFuel.setFuelQuantity(transactionController.getFuelDispenser().getTanked());
            transactionFuel.setTotalPrice(transactionController.getFuelDispenser().getCost());
            fuelTransactionRepository.add(transactionFuel);
        }

        if(!transactionController.getShoppingCart().isEmpty()){
            ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();
            TransactionProduct transactionProduct = new TransactionProduct();
            transactionProduct.setEmployee(transactionController.getEmployeeController().getMainController().getEmployee());
            transactionProduct.setCustomer(transactionController.getCustomer());

            double cost = 0.0;
            for(ShoppingContainerController product :transactionController.getShoppingCart()){
                cost += product.getProduct().getPrice()*product.getAmount();
            }
            transactionProduct.setProductsPrice(cost);
            productTransactionRepository.add(transactionProduct);
        }

        transactionController.getFuelDispenser().pay();

        ProductRepository productRepository = new ProductRepository();
        for(ShoppingContainerController product :transactionController.getShoppingCart()){
            productRepository.findById(product.getProduct().getId()).setRemaining(
                    productRepository.findById(product.getProduct().getId()).getRemaining()-product.getAmount());
        }
        transactionController.getShoppingCart().clear();
        transactionController.getEmployeeController().toStart();
    }
}
