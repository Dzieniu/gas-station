package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;
import com.dzieniu2.repository.FuelTransactionRepository;
import com.dzieniu2.repository.ProductRepository;
import com.dzieniu2.repository.ProductTransactionRepository;
import com.dzieniu2.service.DateConverterService;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SummaryController {

    @FXML
    private AnchorPane dispenserPane,customerPane;

    @FXML
    private JFXListView productList;

    @FXML
    private Label nameLabel,litersLabel,costLabel,priceLabel,fuelLeftLabel,percentLeftLabel,customerNameLabel, customerRegisterDateLabel,
        dispenserInfoLabel,customerInfoLabel,productsInfoLabel,chosenProductsLabel,currentDateLabel,totalCostLabel;

    private TransactionController transactionController;

    @FXML
    public void initialize(){
        dispenserPane.setVisible(false);
        customerPane.setVisible(false);
    }

    @FXML
    public void toClientCheck() throws IOException {
        transactionController.openCustomerWindow();
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;

        refreshData();
    }

    public void refreshData(){
        double totalCost = 0.0;
        if(transactionController.getDispenserController().getFuelDispenser()!=null){
            dispenserPane.setVisible(true);
            nameLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getId()+" - "+transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel().getName());
            litersLabel.setText((Math.round(transactionController.getDispenserController().getFuelDispenser().getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            costLabel.setText((Math.round(transactionController.getDispenserController().getFuelDispenser().getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            priceLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel().getPrice()+"PLN/dm³");
            fuelLeftLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuelLeft()+"dm³");
            percentLeftLabel.setText("( "+(Math.round((transactionController.getDispenserController().getFuelDispenser().getContainer().getFuelLeft()/transactionController.getDispenserController().getFuelDispenser().getContainer().getMaxCapacity())*Math.pow(10, 2))/Math.pow(10, 2))+"% )");
            dispenserInfoLabel.setText("Fuel cost: "+(Math.round(transactionController.getDispenserController().getFuelDispenser().getCost()*Math.pow(10, 2))/Math.pow(10, 2))+" PLN");
            totalCost += transactionController.getDispenserController().getFuelDispenser().getCost();
        }
        if(transactionController.getCustomerController().getCustomer()!=null){
            customerPane.setVisible(true);
            customerNameLabel.setText(transactionController.getCustomerController().getCustomer().getName()+" "+transactionController.getCustomerController().getCustomer().getSurname());
            customerRegisterDateLabel.setText(transactionController.getCustomerController().getCustomer().getRegisterDate().toString());
            customerInfoLabel.setText("");
        }
        if(!transactionController.getChosenProducts().isEmpty()){
            productList.getItems().clear();
            transactionController.getChosenProducts().forEach(product -> {
                productList.getItems().add(product.getProduct().getName()+" x "+product.getAmount()+" x "+product.getProduct().getPrice()+"PLN");
            });
            chosenProductsLabel.setText("Chosen products");
            double cost = 0.0;
            for(ShoppingContainerController product :transactionController.getChosenProducts()){
                cost += product.getProduct().getPrice()*product.getAmount();
            }
            totalCost += cost;
            productsInfoLabel.setText("Total products cost: "+(Math.round(cost*Math.pow(10, 2))/Math.pow(10, 2)));
        }
        currentDateLabel.setText(DateConverterService.getCurrentDate());
        totalCostLabel.setText((Math.round(totalCost*Math.pow(10, 2))/Math.pow(10, 2))+" PLN");
    }

    @FXML
    public void finalizeTransaction() throws IOException {

        if(transactionController.getDispenserController().getFuelDispenser()==null &&
                transactionController.getChosenProducts().isEmpty()){
            return;
        }

        if(transactionController.getDispenserController().getFuelDispenser()!=null){
            FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
            TransactionFuel transactionFuel = new TransactionFuel();
            if(transactionController.getCustomerController().getCustomer()!=null) {
                transactionFuel.setCustomer(transactionController.getCustomerController().getCustomer());
            }else transactionFuel.setCustomer(null);
            transactionFuel.setEmployee(transactionController.getEmployeeController().getMainController().getEmployee());
            transactionFuel.setFuel(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel());
            transactionFuel.setFuelPrice(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel().getPrice());
            transactionFuel.setFuelQuantity(transactionController.getDispenserController().getFuelDispenser().getTanked());
            transactionFuel.setTotalPrice(transactionController.getDispenserController().getFuelDispenser().getCost());
            fuelTransactionRepository.add(transactionFuel);
            transactionController.getDispenserController().getFuelDispenser().pay();
        }

        if(!transactionController.getChosenProducts().isEmpty()){
            ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();
            TransactionProduct transactionProduct = new TransactionProduct();
            transactionProduct.setEmployee(transactionController.getEmployeeController().getMainController().getEmployee());
            if(transactionController.getCustomerController().getCustomer()!=null) {
                transactionProduct.setCustomer(transactionController.getCustomerController().getCustomer());
            }else transactionProduct.setCustomer(null);

            double cost = 0.0;
            for(ShoppingContainerController product :transactionController.getChosenProducts()){
                cost += product.getProduct().getPrice()*product.getAmount();
            }
            transactionProduct.setProductsPrice(cost);
            productTransactionRepository.add(transactionProduct);

            ProductRepository productRepository = new ProductRepository();
            for(ShoppingContainerController product : transactionController.getChosenProducts()){
                productRepository.findById(product.getProduct().getId()).setRemaining(
                        productRepository.findById(product.getProduct().getId()).getRemaining()-product.getAmount());
            }
            transactionController.getChosenProducts().clear();
        }

        transactionController.getEmployeeController().openDefaultWindow();
        transactionController.getEmployeeController().initTransaction();
    }
}
