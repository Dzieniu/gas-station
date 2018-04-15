package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.*;
import com.dzieniu2.repository.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private TableView employeesTable,customersTable,productsTable,containersTable,fuelsTable,transactionsTable;

    @FXML
    private TabPane tableTabPane;

    @FXML
    public void initialize(){
        adjustTables();
    }

    @FXML
    public void showEmployeeWindow(Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/EmployeePanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Employee");
        stage.setScene(new Scene(loader.load()));
        EmployeeController employeeController = loader.<EmployeeController>getController();
        employeeController.setAdminController(this);
        employeeController.setSelectedEmployee(employee);
        stage.show();
    }

    @FXML void showCustomerWindow(Customer customer) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/CustomerPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Customer");
        stage.setScene(new Scene(loader.load()));
        CustomerController customerController = loader.<CustomerController>getController();
        customerController.setAdminController(this);
        customerController.setSelectedCustomer(customer);
        stage.show();
    }

    @FXML void showProductWindow(Product product) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/ProductPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Product");
        stage.setScene(new Scene(loader.load()));
        ProductController productController = loader.<ProductController>getController();
        productController.setAdminController(this);
        productController.setSelectedProduct(product);
        stage.show();
    }

    @FXML void showContainerWindow(Container container) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/ContainerPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Container");
        stage.setScene(new Scene(loader.load()));
        ContainerController containerController = loader.<ContainerController>getController();
        containerController.setAdminController(this);
        containerController.setSelectedContainer(container);
        stage.show();
    }

    @FXML void showFuelWindow(Fuel fuel) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/FuelPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Fuel");
        stage.setScene(new Scene(loader.load()));
        FuelController fuelController = loader.<FuelController>getController();
        fuelController.setAdminController(this);
        fuelController.setSelectedFuel(fuel);
        stage.show();
    }

    @FXML void showTransactionWindow(Transaction transaction) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/TransactionPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("Transaction");
        stage.setScene(new Scene(loader.load()));
        TransactionController transactionController = loader.<TransactionController>getController();
        transactionController.setAdminController(this);
        transactionController.setSelectedTransaction(transaction);
        stage.show();
    }

    @FXML
    public void switchTab(){

        clearTables();

        int selectedTab = tableTabPane.getSelectionModel().getSelectedIndex();

        switch (selectedTab){
            case 0:
                EmployeeRepository employeeRepository = new EmployeeRepository();
                List<Employee> employees = employeeRepository.findAll();
                employeesTable.getItems().addAll(employees);
                break;
            case 1:
                CustomerRepository customerRepository = new CustomerRepository();
                List<Customer> customers = customerRepository.findAll();
                customersTable.getItems().addAll(customers);
                break;
            case 2:
                ProductRepository productRepository = new ProductRepository();
                List<Product> products = productRepository.findAll();
                productsTable.getItems().addAll(products);
                break;
            case 3:
                ContainerRepository containerRepository = new ContainerRepository();
                List<Container> containers = containerRepository.findAll();
                containersTable.getItems().addAll(containers);
                break;
            case 4:
                FuelRepository fuelRepository = new FuelRepository();
                List<Fuel> fuels = fuelRepository.findAll();
                fuelsTable.getItems().addAll(fuels);
                break;
            case 5:
                TransactionRepository transactionRepository = new TransactionRepository();
                List<Transaction> transactions = transactionRepository.findAll();
                transactionsTable.getItems().addAll(transactions);
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

    public void adjustTables(){

        employeesTable.setFixedCellSize(100);
        customersTable.setFixedCellSize(100);
        productsTable.setFixedCellSize(100);
        containersTable.setFixedCellSize(100);
        fuelsTable.setFixedCellSize(100);
        transactionsTable.setFixedCellSize(100);

        employeesTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Employee employee = (Employee) employeesTable.getSelectionModel().getSelectedItem();
                try {
                    showEmployeeWindow(employee);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        customersTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
                try {
                    showCustomerWindow(customer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        productsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Product product = (Product) productsTable.getSelectionModel().getSelectedItem();
                try {
                    showProductWindow(product);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        containersTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Container container = (Container) containersTable.getSelectionModel().getSelectedItem();
                try {
                    showContainerWindow(container);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fuelsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Fuel fuel = (Fuel) fuelsTable.getSelectionModel().getSelectedItem();
                try {
                    showFuelWindow(fuel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        transactionsTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                Transaction transaction = (Transaction) transactionsTable.getSelectionModel().getSelectedItem();
                try {
                    showTransactionWindow(transaction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        TableColumn<Container,String> column1 = (TableColumn<Container, String>) containersTable.getColumns().get(3);
        column1.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getFuel().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<Transaction,String> column2 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(5);
        column2.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getCustomer().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<Transaction,String> column3 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(6);
        column3.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getEmployee().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<Transaction,String> column4 = (TableColumn<Transaction, String>) transactionsTable.getColumns().get(7);
        column4.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getFuel().getId().toString());
            return simpleStringProperty;
        });
    }
}
