package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Employee;
import com.dzieniu2.entity.Role;
import com.dzieniu2.repository.EmployeeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EmployeeController{

    @FXML
    TextField loginFieldCreate,passwordFieldCreate,loginFieldUpdate,passwordFieldUpdate;

    @FXML
    private ChoiceBox roleBoxCreate,roleBoxUpdate,idBox;

    private AdminController adminController;

    @FXML
    public void initialize(){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.findAll().forEach(x -> idBox.getItems().add(x.getId()));
        idBox.getSelectionModel().selectFirst();

        readEmployee();

        roleBoxCreate.getItems().addAll(Role.values());
        roleBoxUpdate.getItems().addAll(Role.values());
    }

    @FXML
    public void createEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee();
        employee.setLogin(loginFieldCreate.getText());
        employee.setPassword(passwordFieldCreate.getText());
        employee.setRole((Role) roleBoxCreate.getSelectionModel().getSelectedItem());
        employeeRepository.add(employee);
        adminController.switchTab();
    }

    @FXML void readEmployee(){
        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
        if(id==null) return;

        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = employeeRepository.findById(id);
        loginFieldUpdate.setText(employee.getLogin());
        passwordFieldUpdate.setText(employee.getPassword());
        roleBoxUpdate.getSelectionModel().select(employee.getRole());
    }

    @FXML void updateEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
        Employee employee = employeeRepository.findById(id);
        employee.setLogin(loginFieldUpdate.getText());
        employee.setPassword(passwordFieldUpdate.getText());
        employee.setRole((Role) roleBoxUpdate.getSelectionModel().getSelectedItem());
        employeeRepository.update(employee);
        adminController.switchTab();
    }

    @FXML void deleteEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Long id = (Long) idBox.getSelectionModel().getSelectedItem();
        employeeRepository.delete(id);
        adminController.switchTab();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}
