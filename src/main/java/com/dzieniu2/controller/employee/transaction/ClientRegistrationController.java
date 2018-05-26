package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.other.RandomString;
import com.dzieniu2.repository.CustomerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.util.concurrent.ThreadLocalRandom;

public class ClientRegistrationController {

    @FXML
    private TextField customerFirstNameField,customerLastNameField;

    @FXML
    private Button GenButton;

    @FXML
    private Label infoLabel;

    private String cardCode;

    private ClientChooserController clientChooserController;

    @FXML
    public void generateUID(ActionEvent event) {
        RandomString gen = new RandomString(16, ThreadLocalRandom.current());
        String gen2 = gen.toString();
        String substr = gen2.substring(gen2.length() - 16);
        cardCode = substr;
        GenButton.setText(substr);
    }

    @FXML
    public void addCustomer(){
        if(validate()) {
            CustomerRepository customerRepository = new CustomerRepository();
            Customer customer = new Customer();
            customer.setName(customerFirstNameField.getText());
            customer.setSurname(customerLastNameField.getText());
            customer.setCardCode(cardCode);
            customerRepository.add(customer);
            infoLabel.setTextFill(Paint.valueOf("#3FBF3F"));
            infoLabel.setText("Pomyślnie dodano klienta");
            backToCustomer();
        }
    }

    @FXML
    public void backToCustomer(){
        clientChooserController.backToCustomer();
    }

    private boolean validate(){

        if(customerFirstNameField.getText().length()<3){
            infoLabel.setTextFill(Paint.valueOf("#dd0909"));
            infoLabel.setText("Imię musi składać się z minimum trzech znaków");
            return false;
        }
        if(customerLastNameField.getText().length()<3){
            infoLabel.setTextFill(Paint.valueOf("#dd0909"));
            infoLabel.setText("Nazwisko musi składać się z minimum trzech znaków");
            return false;
        }
        if(cardCode==null){
            infoLabel.setTextFill(Paint.valueOf("#dd0909"));
            infoLabel.setText("Najpierw wygeneruj kod karty klienta!");
            return false;
        }
        return true;
    }

    public void setClientChooserController(ClientChooserController clientChooserController){
        this.clientChooserController = clientChooserController;
    }
}


