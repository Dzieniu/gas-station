package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.service.DateConverterService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ClientChooserController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane customerPane,centerPane;

    @FXML
    private Label infoLabel, customerNameLabel, customerRegisterDateLabel;

    @FXML
    private TextField cardCodeField;

    @FXML
    private Button findButton,registerButton;

    private TransactionController transactionController;

    private Customer customer;

    @FXML
    public void initialize(){
        customerPane.setVisible(false);
        customerPane.setOnMouseClicked(event -> {
            if(transactionController.getCustomer()==null && this.customer!=null){
                customerPane.setStyle("-fx-background-color: #4059a9;-fx-border-color: gray");
                customerNameLabel.setStyle("-fx-text-fill: white");
                customerRegisterDateLabel.setStyle("-fx-text-fill: white");
                transactionController.setCustomer(this.customer);
            }else if(transactionController.getCustomer()!=null){
                customerPane.setStyle("-fx-background-color: white;-fx-border-color: gray");
                customerNameLabel.setStyle("-fx-text-fill: black");
                customerRegisterDateLabel.setStyle("-fx-text-fill: black");
                transactionController.setCustomer(null);
            }
        });
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
        if(transactionController.getCustomer()!=null){
            setCustomer(transactionController.getCustomer());
            customerPane.setStyle("-fx-background-color: #4059a9;-fx-border-color: gray");
            customerNameLabel.setStyle("-fx-text-fill: white");
            customerRegisterDateLabel.setStyle("-fx-text-fill: white");
        }
    }

    @FXML
    public void toDispensers() throws IOException {
        transactionController.toDispensers();
    }

    @FXML
    public void toSummary() throws IOException {
        transactionController.toSummary();
    }

    @FXML
    public void loadCustomer(){
        setCustomer(findCustomer(cardCodeField.getText()));
        customerPane.setStyle("-fx-background-color: white;-fx-border-color: gray");
        customerNameLabel.setStyle("-fx-text-fill: black");
        customerRegisterDateLabel.setStyle("-fx-text-fill: black");
        transactionController.setCustomer(null);
    }

    public void setCustomer(Customer customer){

        if(customer!=null){
            customerNameLabel.setText(customer.getName()+" "+customer.getSurname());
            customerRegisterDateLabel.setText(DateConverterService.formatDate(customer.getRegisterDate()));
            infoLabel.setText("");
            customerPane.setVisible(true);
            this.customer = customer;
        }else {
            infoLabel.setText("Customer not found");
            customerPane.setVisible(false);
        }
    }

    private Customer findCustomer(String cardCode){
        CustomerRepository customerRepository = new CustomerRepository();
        return customerRepository.findByCardCode(cardCode);
    }

    @FXML
    public void openRegistrationWindow() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/transaction/ClientRegistration.fxml"));
        AnchorPane pane = loader.load();
        ClientRegistrationController clientRegistrationController = loader.<ClientRegistrationController>getController();
        clientRegistrationController.setClientChooserController(this);
        borderPane.setCenter(pane);
    }

    @FXML
    public void backToCustomer(){
        borderPane.setCenter(centerPane);
    }
}
