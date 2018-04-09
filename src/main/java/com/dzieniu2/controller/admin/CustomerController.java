package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.entity.Employee;
import com.dzieniu2.entity.Role;
import com.dzieniu2.repository.CustomerRepository;
import com.dzieniu2.repository.EmployeeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerController {

    @FXML
    TextField nameFieldCreate,surnameFieldCreate,cardCodeFieldCreate,
            nameFieldUpdate,surnameFieldUpdate,cardCodeFieldUpdate;

    @FXML
    private ChoiceBox idBox;

    private AdminController adminController;

    @FXML
    public void initialize(){
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.findAll().forEach(x -> idBox.getItems().add(x.getId()));
        idBox.getSelectionModel().selectFirst();

        readCustomer();
    }

    @FXML
    public void createCustomer() throws IOException {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer();
        customer.setName(nameFieldCreate.getText());
        customer.setSurname(surnameFieldCreate.getText());
        customer.setCardCode(cardCodeFieldCreate.getText());
        customerRepository.add(customer);
        adminController.switchTab();
    }

    @FXML
    void readCustomer(){
        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
        if(id==null) return;

        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findById(id);
        nameFieldUpdate.setText(customer.getName());
        surnameFieldUpdate.setText(customer.getSurname());
        cardCodeFieldUpdate.setText(customer.getCardCode());
    }

//    @FXML void updateCustomer() throws IOException {
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
//        Employee employee = employeeRepository.findById(id);
//        employee.setLogin(loginFieldUpdate.getText());
//        employee.setPassword(passwordFieldUpdate.getText());
//        employee.setRole((Role) roleBoxUpdate.getSelectionModel().getSelectedItem());
//        employeeRepository.update(employee);
//        adminController.switchTab();
//    }
//
//    @FXML void deleteCustomer() throws IOException {
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
//        employeeRepository.delete(id);
//        adminController.switchTab();
//    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}
