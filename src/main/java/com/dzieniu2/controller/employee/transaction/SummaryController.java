package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;
import com.dzieniu2.repository.CustomerRepository;
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
        dispenserInfoLabel,customerInfoLabel,productsInfoLabel,chosenProductsLabel,currentDateLabel,totalCostLabel,discountInfoLabel;

    private TransactionController transactionController;
    private double fuelDiscount = 1.0;
    private double totalCost = 0.0;


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

        if(transactionController.getDispenserController().getFuelDispenser()!=null){
            dispenserPane.setVisible(true);
            nameLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getId()+" - "+transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel().getName());
            litersLabel.setText((Math.round(transactionController.getDispenserController().getFuelDispenser().getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            costLabel.setText((Math.round(transactionController.getDispenserController().getFuelDispenser().getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
            priceLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuel().getPrice()+"PLN/dm³");
            fuelLeftLabel.setText(transactionController.getDispenserController().getFuelDispenser().getContainer().getFuelLeft()+"dm³");
            percentLeftLabel.setText("( "+(Math.round((transactionController.getDispenserController().getFuelDispenser().getContainer().getFuelLeft()/transactionController.getDispenserController().getFuelDispenser().getContainer().getMaxCapacity())*Math.pow(10, 2))/Math.pow(10, 2))+"% )");
            if (transactionController.getCustomerController().getCustomer() != null)
                fuelDiscount = calculateDiscount(transactionController.getCustomerController().getCustomer());

            String displayedDiscount = String.valueOf(Math.round((1.0 - fuelDiscount)*Math.pow(10,2)*100)/Math.pow(10,2));
            int index = displayedDiscount.indexOf('.');
            displayedDiscount = displayedDiscount.substring(0,index);
            discountInfoLabel.setText("Discount: " + displayedDiscount + " %");
            dispenserInfoLabel.setText("Fuel cost: "+(Math.round(transactionController.getDispenserController().getFuelDispenser().getCost()*fuelDiscount*Math.pow(10, 2))/Math.pow(10, 2))+" PLN");
            totalCost += transactionController.getDispenserController().getFuelDispenser().getCost()*fuelDiscount;
        }
        if(transactionController.getCustomerController().getCustomer()!=null){
            customerPane.setVisible(true);
            customerNameLabel.setText(transactionController.getCustomerController().getCustomer().getName()+" "+transactionController.getCustomerController().getCustomer().getSurname());
            customerRegisterDateLabel.setText(DateConverterService.formatDate(transactionController.getCustomerController().getCustomer().getRegisterDate()));
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


        addCustomerPoints(transactionController.getCustomerController().getCustomer());
        transactionController.getEmployeeController().openDefaultWindow();
        transactionController.getEmployeeController().initTransaction();
    }

    private void addCustomerPoints(Customer customer) {
        CustomerRepository cr = new CustomerRepository();
        System.out.println(customer);
        if (customer != null) {
            double points = totalCost / 2;
            points += customer.getPoints();
            customer.setPoints(points);
            System.out.println(customer);
            cr.update(customer);
        }
    }

    private double calculateDiscount(Customer customer) {
        if (customer.getPoints() >= 1000 && customer.getPoints() < 2000)
            return 0.95;
        else if (customer.getPoints() >= 2000 && customer.getPoints() < 3500)
            return 0.90;
        else if (customer.getPoints() >= 3500)
            return 0.85;

        return 1.0;
    }
}
