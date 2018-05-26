package com.dzieniu2;

import com.dzieniu2.entity.*;
import com.dzieniu2.entity.enums.ProductCategory;
import com.dzieniu2.entity.enums.Role;
import com.dzieniu2.repository.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loadData();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args)
    {
        launch(args);

    }

    public void loadData() throws IOException {

        ContainerRepository cr = new ContainerRepository();
        Container co1 = new Container();
        co1.setMaxCapacity(20000l);
        co1.setFuelLeft(15000.5);
        cr.add(co1);
        Container co2 = new Container();
        co2.setMaxCapacity(20000l);
        co2.setFuelLeft(5500.3);
        cr.add(co2);
        Container co3 = new Container();
        co3.setMaxCapacity(30000l);
        co3.setFuelLeft(2500.1);
        cr.add(co3);
        Container co4 = new Container();
        co4.setMaxCapacity(30000l);
        co4.setFuelLeft(2400.9);
        cr.add(co4);

        FuelRepository fr = new FuelRepository();
        Fuel f1 = new Fuel();
        f1.setName("Pb 95");
        f1.setPrice(3.50);
        f1.setContainer(co1);
        fr.add(f1);
        Fuel f2 = new Fuel();
        f2.setName("Pb 98");
        f2.setPrice(3.90);
        f2.setContainer(co2);
        fr.add(f2);
        Fuel f3 = new Fuel();
        f3.setName("ON");
        f3.setPrice(3.20);
        f3.setContainer(co3);
        fr.add(f3);
        Fuel f4 = new Fuel();
        f4.setName("LPG");
        f4.setPrice(1.2);
        f4.setContainer(co4);
        fr.add(f4);

        co1.setFuel(f1);
        co2.setFuel(f2);
        co3.setFuel(f3);
        co4.setFuel(f4);
        cr.update(co1);
        cr.update(co2);
        cr.update(co3);
        cr.update(co4);

        EmployeeRepository er = new EmployeeRepository();
        Employee e1 = new Employee();
        e1.setLogin("admin");
        e1.setPassword("");
        e1.setRole(Role.ADMIN);
        er.add(e1);

        Employee e2 = new Employee();
        e2.setLogin("employee");
        e2.setPassword("");
        e2.setRole(Role.EMPLOYEE);
        er.add(e2);

        CustomerRepository cur = new CustomerRepository();
        Customer cu = new Customer();
        cu.setCardCode("#5555");
        cu.setName("Mieczysław");
        cu.setSurname("Łopata");
        cur.add(cu);
        Customer cu2 = new Customer();
        cu2.setCardCode("#6666");
        cu2.setName("Aneta");
        cu2.setSurname("Kometa");
        cur.add(cu2);
        Customer cu3 = new Customer();
        cu3.setCardCode("#2222");
        cu3.setName("Andrzej");
        cu3.setSurname("Biały");
        cur.add(cu3);

        ProductRepository pr = new ProductRepository();
        Product p1 = new Product();
        p1.setCategory(ProductCategory.ALCOHOL);
        p1.setName("Harnaś");
        p1.setPrice(1.50);
        p1.setRemaining(102);
        p1.setImage(getImage("harnas.png"));
        pr.add(p1);
        Product p2 = new Product();
        p2.setCategory(ProductCategory.ALCOHOL);
        p2.setName("Żubr");
        p2.setPrice(1.70);
        p2.setRemaining(13);
        p2.setImage(getImage("zubr.png"));
        pr.add(p2);
        Product p3 = new Product();
        p3.setCategory(ProductCategory.ALCOHOL);
        p3.setName("Żubrówka");
        p3.setPrice(18.90);
        p3.setRemaining(24);
        p3.setImage(getImage("zubrowka.png"));
        pr.add(p3);
        Product p4 = new Product();
        p4.setCategory(ProductCategory.FOOD);
        p4.setName("Snickers");
        p4.setPrice(2.20);
        p4.setRemaining(334);
        p4.setImage(getImage("snickers.png"));
        pr.add(p4);
        Product p5 = new Product();
        p5.setCategory(ProductCategory.FOOD);
        p5.setName("Milka");
        p5.setPrice(4.00);
        p5.setRemaining(30);
        p5.setImage(getImage("milka.png"));
        pr.add(p5);
        Product p6 = new Product();
        p6.setCategory(ProductCategory.FOOD);
        p6.setName("Śmiej Żelki");
        p6.setPrice(3.70);
        p6.setRemaining(89);
        p6.setImage(getImage("zelki.png"));
        pr.add(p6);
        Product p7 = new Product();
        p7.setCategory(ProductCategory.ACCESSORIES);
        p7.setName("Płyn do spyskiwaczy");
        p7.setPrice(15.90);
        p7.setRemaining(45);
        p7.setImage(getImage("plyn.png"));
        pr.add(p7);
        Product p8 = new Product();
        p8.setCategory(ProductCategory.ACCESSORIES);
        p8.setName("Plak");
        p8.setPrice(4.50);
        p8.setRemaining(75);
        p8.setImage(getImage("plak.png"));
        pr.add(p8);
        Product p9 = new Product();
        p9.setCategory(ProductCategory.SNACK);
        p9.setName("Hot-Dog");
        p9.setPrice(6.90);
        p9.setRemaining(47);
        p9.setImage(getImage("hot-dog.png"));
        pr.add(p9);
        Product p10 = new Product();
        p10.setCategory(ProductCategory.SNACK);
        p10.setName("Zapiekanka");
        p10.setPrice(4.50);
        p10.setRemaining(56);
        p10.setImage(getImage("zapiekanka.png"));
        pr.add(p10);
        Product p11 = new Product();
        p11.setCategory(ProductCategory.FOOD);
        p11.setName("Orzeszki ziemne");
        p11.setPrice(7.00);
        p11.setRemaining(25);
        p11.setImage(getImage("orzeszki.jpg"));
        pr.add(p11);
        Product p12 = new Product();
        p12.setCategory(ProductCategory.FOOD);
        p12.setName("Lays");
        p12.setPrice(7.00);
        p12.setRemaining(25);
        p12.setImage(getImage("lays.png"));
        pr.add(p12);
        Product p13 = new Product();
        p13.setCategory(ProductCategory.DRINK);
        p13.setName("Kawa");
        p13.setPrice(7.00);
        p13.setRemaining(25);
        p13.setImage(getImage("kawa.png"));
        pr.add(p13);
        Product p14 = new Product();
        p14.setCategory(ProductCategory.DRINK);
        p14.setName("Woda");
        p14.setPrice(7.00);
        p14.setRemaining(25);
        p14.setImage(getImage("woda.jpg"));
        pr.add(p14);
        Product p15 = new Product();
        p15.setCategory(ProductCategory.DRINK);
        p15.setName("Coca-cola");
        p15.setPrice(7.00);
        p15.setRemaining(25);
        p15.setImage(getImage("coca-cola.jpg"));
        pr.add(p15);

        FuelTransactionRepository tr = new FuelTransactionRepository();
        TransactionFuel t1 = new TransactionFuel();
        t1.setFuelPrice(3.20);
        t1.setFuelQuantity(70.0);
        t1.setTotalPrice(224.0);
        t1.setCustomer(cur.findById(1l));
        t1.setEmployee(er.findById(1l));
        t1.setFuel(fr.findById(3l));
        tr.add(t1);

        TransactionFuel t2 = new TransactionFuel();
        t2.setFuelPrice(3.20);
        t2.setFuelQuantity(44.2);
        t2.setTotalPrice(141.44);
        t2.setCustomer(cur.findById(1l));
        t2.setEmployee(er.findById(1l));
        t2.setFuel(fr.findById(3l));
        tr.add(t2);

        TransactionFuel t3 = new TransactionFuel();
        t3.setFuelPrice(3.5);
        t3.setFuelQuantity(115.1);
        t3.setTotalPrice(402.85);
        t3.setEmployee(er.findById(1l));
        t3.setCustomer(cur.findById(2l));
        t3.setFuel(fr.findById(1l));
        tr.add(t3);

        TransactionFuel t4 = new TransactionFuel();
        t4.setFuelPrice(3.5);
        t4.setFuelQuantity(88.8);
        t4.setTotalPrice(310.8);
        t4.setEmployee(er.findById(2l));
        t4.setCustomer(cur.findById(2l));
        t4.setFuel(fr.findById(1l));
        tr.add(t4);

        TransactionFuel t5 = new TransactionFuel();
        t5.setFuelPrice(3.5);
        t5.setFuelQuantity(230.5);
        t5.setTotalPrice(806.75);
        t5.setEmployee(er.findById(2l));
        t5.setCustomer(cur.findById(2l));
        t5.setFuel(fr.findById(1l));
        tr.add(t5);

        ProductTransactionRepository productTransactionRepository = new ProductTransactionRepository();
        TransactionProduct transactionProduct1 = new TransactionProduct();
        transactionProduct1.setCustomer(cur.findById(1l));
        transactionProduct1.setEmployee(er.findById(1l));
        Set<Product> productSet1 = new HashSet<>();
        productSet1.add(p1);
        productSet1.add(p2);
        transactionProduct1.setProducts(productSet1);
        transactionProduct1.setProductsPrice(213.56d);
        productTransactionRepository.add(transactionProduct1);
    }

    public static byte[] getImage(String name) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\product-images\\" + name);
        return Files.readAllBytes(path);
    }
}
