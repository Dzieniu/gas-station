package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.*;
import com.dzieniu2.repository.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private TableView employeesTable,customersTable,productsTable,containersTable,fuelsTable,transactionsTable;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TabPane tableTabPane;

    @FXML
    public void initialize(){
        adjustColumnProperties();
        employeesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                    Employee employee = (Employee) employeesTable.getSelectionModel().getSelectedItem();
                    System.out.println(employee.getLogin());
                }
            }
        });
    }

    @FXML
    public void switchTab() throws IOException {

        clearTables();

        int selectedTab = tableTabPane.getSelectionModel().getSelectedIndex();

        switch (selectedTab){
            case 0:
                EmployeeRepository employeeRepository = new EmployeeRepository();
                List<Employee> employees = employeeRepository.findAll();
                employeesTable.getItems().addAll(employees);
                adjustTools(selectedTab);
                break;
            case 1:
                CustomerRepository customerRepository = new CustomerRepository();
                List<Customer> customers = customerRepository.findAll();
                customersTable.getItems().addAll(customers);
                adjustTools(selectedTab);
                break;
            case 2:
                ProductRepository productRepository = new ProductRepository();
                List<Product> products = productRepository.findAll();
                productsTable.getItems().addAll(products);
                adjustTools(selectedTab);
                break;
            case 3:
                ContainerRepository containerRepository = new ContainerRepository();
                List<Container> containers = containerRepository.findAll();
                containersTable.getItems().addAll(containers);
                adjustTools(selectedTab);
                break;
            case 4:
                FuelRepository fuelRepository = new FuelRepository();
                List<Fuel> fuels = fuelRepository.findAll();
                fuelsTable.getItems().addAll(fuels);
                adjustTools(selectedTab);
                break;
            case 5:
                TransactionRepository transactionRepository = new TransactionRepository();
                List<Transaction> transactions = transactionRepository.findAll();
                transactionsTable.getItems().addAll(transactions);
                adjustTools(selectedTab);
                break;
        }
    }

    private void clearTables(){

        employeesTable.getItems().clear();
        if(customersTable!=null) customersTable.getItems().clear();
        if(productsTable!=null) productsTable.getItems().clear();
        if(containersTable!=null) containersTable.getItems().clear();
        if(fuelsTable!=null) fuelsTable.getItems().clear();
        if(transactionsTable!=null) transactionsTable.getItems().clear();
    }

    private void adjustTools(int i) throws IOException {

        switch (i){
            case 0:
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/admin/EmployeePanel.fxml"
                        )
                );
                mainBorderPane.setRight((TabPane) loader.load());
                EmployeeController employeeController = loader.<EmployeeController>getController();
                employeeController.setAdminController(this);
                break;
            case 1:
                TabPane pane2 = FXMLLoader.load(getClass().getResource("/fxml/admin/CustomerPanel.fxml"));
                mainBorderPane.setRight(pane2);
                break;
            case 2:
                TabPane pane3 = FXMLLoader.load(getClass().getResource("/fxml/admin/ProductPanel.fxml"));
                mainBorderPane.setRight(pane3);
                break;
            case 3:
                TabPane pane4 = FXMLLoader.load(getClass().getResource("/fxml/admin/ContainerPanel.fxml"));
                mainBorderPane.setRight(pane4);
                break;
            case 4:
                TabPane pane5 = FXMLLoader.load(getClass().getResource("/fxml/admin/FuelPanel.fxml"));
                mainBorderPane.setRight(pane5);
                break;
            case 5:
                TabPane pane6 = FXMLLoader.load(getClass().getResource("/fxml/admin/TransactionPanel.fxml"));
                mainBorderPane.setRight(pane6);
                break;
        }
    }

    public void adjustColumnProperties(){
        TableColumn<Fuel,String> column1 = (TableColumn<Fuel, String>) fuelsTable.getColumns().get(3);
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Fuel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Fuel, String> param) {
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(param.getValue().getContainer().getId().toString());
                return simpleStringProperty;
            }
        });
        TableColumn<Transaction,String> column2 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(5);
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(param.getValue().getCustomer().getId().toString());
                return simpleStringProperty;
            }
        });
        TableColumn<Transaction,String> column3 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(6);
        column3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(param.getValue().getEmployee().getId().toString());
                return simpleStringProperty;
            }
        });
        TableColumn<Transaction,String> column4 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(7);
        column4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Transaction, String> param) {
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(param.getValue().getFuel().getId().toString());
                return simpleStringProperty;
            }
        });
    }

    public TableView getEmployeesTable() {
        return employeesTable;
    }
}
