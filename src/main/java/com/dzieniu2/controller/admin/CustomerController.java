package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.repository.CustomerRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerController {

    @FXML
    private TabPane customerPane;

    @FXML
    private TextField nameFieldCreate,surnameFieldCreate,cardCodeFieldCreate,
            nameFieldUpdate,surnameFieldUpdate,cardCodeFieldUpdate;

    @FXML
    private Label idBox;

    private AdminController adminController;

    private Customer selectedCustomer;

    @FXML
    public void createCustomer(){
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer();
        customer.setName(nameFieldCreate.getText());
        customer.setSurname(surnameFieldCreate.getText());
        customer.setCardCode(cardCodeFieldCreate.getText());
        customerRepository.add(customer);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateCustomer(){
        CustomerRepository customerRepository = new CustomerRepository();
        selectedCustomer.setName(nameFieldUpdate.getText());
        selectedCustomer.setSurname(surnameFieldUpdate.getText());
        selectedCustomer.setCardCode(cardCodeFieldUpdate.getText());
        customerRepository.update(selectedCustomer);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteCustomer(){
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.delete(selectedCustomer.getId());
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;

        idBox.setText(selectedCustomer.getId().toString());
        nameFieldUpdate.setText(selectedCustomer.getName());
        surnameFieldUpdate.setText(selectedCustomer.getSurname());
        cardCodeFieldUpdate.setText(selectedCustomer.getCardCode());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) customerPane.getScene().getWindow();
        stage.close();
    }
}
