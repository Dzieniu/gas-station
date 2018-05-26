package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.service.DateConverterService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Data;

import java.io.IOException;

@Data
public class CustomerController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane customerPane,centerPane;

    @FXML
    private Label infoLabel, customerNameLabel, customerRegisterDateLabel;

    @FXML
    private TextField cardCodeField;

    private TransactionController transactionController;

    private Customer customer,searchCustomer;

    @FXML
    public void initialize(){
        customerPane.setVisible(false);
        customerPane.setOnMouseClicked(event -> {
            if(customer==null){
                setWhiteStyle();
                customer = searchCustomer;
            }else {
                setBlackStyle();
                customer = null;
            }
        });
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    @FXML
    public void openDispenserWindow() {
        transactionController.openDispenserWindow();
    }

    @FXML
    public void openSummaryWindow() throws IOException {
        transactionController.openSummaryWindow();
    }

    @FXML
    public void openRegistrationWindow() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ClientRegistration.fxml"));
        AnchorPane pane = loader.load();
        ClientRegistrationController clientRegistrationController = loader.<ClientRegistrationController>getController();
        clientRegistrationController.setCustomerController(this);
        borderPane.setCenter(pane);
    }

    @FXML
    public void openCustomerWindow(){
        borderPane.setCenter(centerPane);
    }

    @FXML
    public void loadCustomer(){
        setCustomer(findCustomer(cardCodeField.getText()));
        setBlackStyle();
        customer = null;
    }

    public void setCustomer(Customer customer){

        if(customer!=null){
            customerNameLabel.setText(customer.getName()+" "+customer.getSurname());
            customerRegisterDateLabel.setText(DateConverterService.formatDate(customer.getRegisterDate()));
            infoLabel.setText("");
            customerPane.setVisible(true);
            searchCustomer = customer;
        }else {
            infoLabel.setText("Customer not found");
            customerPane.setVisible(false);
            searchCustomer = null;
        }
    }

    private Customer findCustomer(String cardCode){
        CustomerRepository customerRepository = new CustomerRepository();
        return customerRepository.findByCardCode(cardCode);
    }

    public void setWhiteStyle(){

        customerPane.setStyle("-fx-background-color: #4059a9;-fx-border-color: gray");
        customerNameLabel.setStyle("-fx-text-fill: white");
        customerRegisterDateLabel.setStyle("-fx-text-fill: white");
    }

    public void setBlackStyle(){

        customerPane.setStyle("-fx-background-color: white;-fx-border-color: gray");
        customerNameLabel.setStyle("-fx-text-fill: black");
        customerRegisterDateLabel.setStyle("-fx-text-fill: black");
    }
}
