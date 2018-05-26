package com.dzieniu2.controller.admin;

import com.dzieniu2.entity.*;
import com.dzieniu2.repository.*;
import com.dzieniu2.service.DateConverterService;
import com.dzieniu2.service.ReportService;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController {

    @FXML
    private TableView employeesTable,customersTable,productsTable,containersTable,fuelsTable,
            fuelTransactionsTable,productTransactionsTable;

    @FXML
    private TabPane tableTabPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label calendarDate;

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

    @FXML void showTransactionWindow(TransactionFuel transactionFuel) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/admin/TransactionPanel.fxml"
                )
        );
        Stage stage = new Stage();
        stage.setTitle("TransactionFuel");
        stage.setScene(new Scene(loader.load()));
        TransactionController transactionController = loader.<TransactionController>getController();
        transactionController.setAdminController(this);
        transactionController.setSelectedTransactionFuel(transactionFuel);
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
                List<SimpleEmployee> simpleEmployees = employees.stream()
                        .map(x -> new SimpleEmployee(x.getId(), x.getLogin(), generatePassword(x.getPassword()), x.getRole().toString()))
                        .collect(Collectors.toList());
                employeesTable.getItems().addAll(simpleEmployees);
                break;
            case 1:
                CustomerRepository customerRepository = new CustomerRepository();
                List<Customer> customers = customerRepository.findAll();
                List<SimpleCustomer> simpleCustomers = customers.stream()
                        .map(x -> new SimpleCustomer(x.getId(), x.getName(), x.getSurname(), x.getCardCode(), DateConverterService.formatDate(x.getRegisterDate())))
                        .collect(Collectors.toList());

                customersTable.getItems().addAll(simpleCustomers);
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
                FuelTransactionRepository fuelTransactionRepository = new FuelTransactionRepository();
                List<TransactionFuel> fuelTransactions = fuelTransactionRepository.findAll();
                fuelTransactionsTable.getItems().addAll(fuelTransactions);
                break;
            case 6:
                ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();
                List<TransactionProduct> productTransactions = productTransactionRepository.findAll();
                productTransactionsTable.getItems().addAll(productTransactions);
                break;
        }
    }

    private void clearTables(){

        employeesTable.getItems().clear();
        if(customersTable!=null) customersTable.getItems().clear();
        if(productsTable!=null) productsTable.getItems().clear();
        if(containersTable!=null) containersTable.getItems().clear();
        if(fuelsTable!=null) fuelsTable.getItems().clear();
        if(fuelTransactionsTable!=null) fuelTransactionsTable.getItems().clear();
        if(productTransactionsTable!=null) productTransactionsTable.getItems().clear();
    }

    public void adjustTables(){

        employeesTable.setFixedCellSize(100);
        customersTable.setFixedCellSize(100);
        productsTable.setFixedCellSize(100);
        containersTable.setFixedCellSize(100);
        fuelsTable.setFixedCellSize(100);
        fuelTransactionsTable.setFixedCellSize(100);
        productTransactionsTable.setFixedCellSize(100);

        employeesTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                EmployeeRepository employeeRepository = new EmployeeRepository();
                SimpleEmployee simpleEmployee = (SimpleEmployee) employeesTable.getSelectionModel().getSelectedItem();
                Employee employee = employeeRepository.findById(simpleEmployee.getId());
                try {
                    showEmployeeWindow(employee);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        customersTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                CustomerRepository customerRepository = new CustomerRepository();
                SimpleCustomer simpleCustomer = (SimpleCustomer) customersTable.getSelectionModel().getSelectedItem();
                Customer customer = customerRepository.findById(simpleCustomer.getId());
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

//        transactionsTable.setOnMouseClicked(mouseEvent -> {
//            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
//                TransactionFuel transactionFuel = (TransactionFuel) transactionsTable.getSelectionModel().getSelectedItem();
//                try {
//                    showTransactionWindow(transactionFuel);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        TableColumn<Container,String> fuelIdColumn = (TableColumn<Container, String>) containersTable.getColumns().get(3);
        fuelIdColumn.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getFuel().getId().toString());
            return simpleStringProperty;
        });

        TableColumn<TransactionFuel,String> column2 = (TableColumn<TransactionFuel, String>) fuelTransactionsTable.getColumns().get(5);
        column2.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getCustomer().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<TransactionFuel,String> column3 = (TableColumn<TransactionFuel, String>) fuelTransactionsTable.getColumns().get(6);
        column3.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getEmployee().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<TransactionFuel,String> column4 = (TableColumn<TransactionFuel, String>) fuelTransactionsTable.getColumns().get(7);
        column4.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getFuel().getId().toString());
            return simpleStringProperty;
        });

        TableColumn<TransactionProduct,String> column5 = (TableColumn<TransactionProduct, String>) productTransactionsTable.getColumns().get(3);
        column5.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getCustomer().getId().toString());
            return simpleStringProperty;
        });
        TableColumn<TransactionProduct,String> column6 = (TableColumn<TransactionProduct, String>) productTransactionsTable.getColumns().get(4);
        column6.setCellValueFactory(param -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(param.getValue().getEmployee().getId().toString());
            return simpleStringProperty;
        });
    }

    @FXML
    public void showDate() {
        ReportService reportService = new ReportService();

        try {
            reportService.generateReport(datePicker.getValue());
            calendarDate.setText("Raport został utworzony");
        } catch (FileNotFoundException e) {
            calendarDate.setText("Błąd przy tworzeniu raportu, spróbuj ponownie");
        } catch (DocumentException e) {
            calendarDate.setText("Błąd przy tworzeniu raportu, spróbuj ponownie");
        }
    }

    private static final class EmployeeTableObject extends RecursiveTreeObject {

        final StringProperty id, login, password, role;

        public EmployeeTableObject(Employee employee){
            this.id = new SimpleStringProperty(employee.getId().toString());
            this.login = new SimpleStringProperty(employee.getLogin());
            this.password = new SimpleStringProperty(employee.getPassword());
            this.role = new SimpleStringProperty(employee.getRole().toString());
        }
    }

    @AllArgsConstructor
    @Data
    public class SimpleCustomer {
        private Long id;
        private String name;
        private String surname;
        private String cardCode;
        private String registerDate;
    }

    @AllArgsConstructor
    @Data
    public class SimpleEmployee {
        private Long id;
        private String login;
        private String password;
        private String role;
    }

    private String generatePassword(String password) {
        return password.length() > 0 ? generateHash(password) : "*****";
    }

    private String generateHash(String text) {
        String hash = "";
        for (int i = 0; i < text.length(); i++)
            hash += "*";
        return hash;
    }
}
