package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.Employee;
import com.dzieniu2.entity.enums.Role;
import com.dzieniu2.repository.EmployeeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeController{

    @FXML
    private TabPane employeePane;

    @FXML
    private TextField loginFieldCreate,passwordFieldCreate,loginFieldUpdate,passwordFieldUpdate;

    @FXML
    private ChoiceBox roleBoxCreate,roleBoxUpdate;

    @FXML
    private Label idBox;

    private AdminController adminController;
    private Employee selectedEmployee;

    @FXML
    public void createEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee();
        employee.setLogin(loginFieldCreate.getText());
        employee.setPassword(passwordFieldCreate.getText());
        employee.setRole((Role) roleBoxCreate.getSelectionModel().getSelectedItem());
        employeeRepository.add(employee);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void updateEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Long id = selectedEmployee.getId();
        Employee employee = employeeRepository.findById(id);
        employee.setLogin(loginFieldUpdate.getText());
        employee.setPassword(passwordFieldUpdate.getText());
        employee.setRole((Role) roleBoxUpdate.getSelectionModel().getSelectedItem());
        employeeRepository.update(employee);
        adminController.switchTab();
        closeWindow();
    }

    @FXML void deleteEmployee() throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Long id = selectedEmployee.getId();
        employeeRepository.delete(id);
        adminController.switchTab();
        closeWindow();
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;

        roleBoxCreate.getItems().addAll(Role.values());
        roleBoxUpdate.getItems().addAll(Role.values());

        idBox.setText(selectedEmployee.getId().toString());
        loginFieldUpdate.setText(selectedEmployee.getLogin());
        passwordFieldUpdate.setText(selectedEmployee.getPassword());
        roleBoxUpdate.getSelectionModel().select(selectedEmployee.getRole());
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) employeePane.getScene().getWindow();
        stage.close();
    }
}
