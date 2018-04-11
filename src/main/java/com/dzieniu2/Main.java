package com.dzieniu2;

import com.dzieniu2.entity.*;
import com.dzieniu2.repository.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //loadExampleData();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    public void loadExampleData() {
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
        Container c1 = new Container();
        c1.setId(1l);
        f1.setContainer(c1);
        fr.add(f1);
        Fuel f2 = new Fuel();
        f2.setName("Pb 98");
        f2.setPrice(3.90);
        Container c2 = new Container();
        c2.setId(2l);
        f2.setContainer(c2);
        fr.add(f2);
        Fuel f3 = new Fuel();
        f3.setName("ON");
        f3.setPrice(3.20);
        Container c3 = new Container();
        c3.setId(3l);
        f3.setContainer(c3);
        fr.add(f3);
        Fuel f4 = new Fuel();
        f4.setName("LPG");
        f4.setPrice(1.2);
        Container c4 = new Container();
        c4.setId(4l);
        f4.setContainer(c4);
        fr.add(f4);

        EmployeeRepository er = new EmployeeRepository();
        Employee e1 = new Employee();
        e1.setEmail("email@gmail.com");
        e1.setName("Andrzej");
        e1.setSurname("Klawiatura");
        e1.setPassword("123");
        er.add(e1);
        Employee e2 = new Employee();
        e2.setEmail("marzenka@gmail.com");
        e2.setName("Marzena");
        e2.setSurname("Monitor");
        e2.setPassword("123");
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
        p1.setCategory("alkohol");
        p1.setName("Harnaś");
        p1.setPrice(1.50);
        p1.setPath("hranas.png");
        pr.add(p1);
        Product p2 = new Product();
        p2.setCategory("alkohol");
        p2.setName("Żubr");
        p2.setPrice(1.70);
        p2.setPath("zubr.png");
        pr.add(p2);
        Product p3 = new Product();
        p3.setCategory("alkohol");
        p3.setName("Żubrówka");
        p3.setPrice(18.90);
        p3.setPath("zubrowka.png");
        pr.add(p3);
        Product p4 = new Product();
        p4.setCategory("spożywcze");
        p4.setName("Snickers");
        p4.setPrice(2.20);
        p4.setPath("snickers.png");
        pr.add(p4);
        Product p5 = new Product();
        p5.setCategory("spożywcze");
        p5.setName("Milka");
        p5.setPrice(4.00);
        p5.setPath("milka.png");
        pr.add(p5);
        Product p6 = new Product();
        p6.setCategory("spożywcze");
        p6.setName("Śmiej Żelki");
        p6.setPrice(3.70);
        p6.setPath("zelki.png");
        pr.add(p6);
        Product p7 = new Product();
        p7.setCategory("akcesoria samochodowe");
        p7.setName("Płyn do spyskiwaczy");
        p7.setPrice(15.90);
        p7.setPath("plyn.png");
        pr.add(p7);
        Product p8 = new Product();
        p8.setCategory("akcesoria samochodowe");
        p8.setName("Plak");
        p8.setPrice(4.50);
        p8.setPath("plak.png");
        pr.add(p8);
        Product p9 = new Product();
        p9.setCategory("na ciepło");
        p9.setName("Hot-Dog");
        p9.setPrice(6.90);
        p9.setPath("hot-dog.png");
        pr.add(p9);
        Product p10 = new Product();
        p10.setCategory("na ciepło");
        p10.setName("Zapiekanka");
        p10.setPrice(4.50);
        p10.setPath("zapiekanka.png");
        pr.add(p10);

        TransactionRepository tr = new TransactionRepository();
        Transaction t1 = new Transaction();
        t1.setFuelPrice(3.20);
        t1.setFuelQuantity(70.0);
        t1.setTotalPrice(224.0);
        Customer cs1 = new Customer();
        cs1.setId(1l);
        t1.setCustomer(cs1);
        Employee em1 = new Employee();
        em1.setId(1l);
        t1.setEmployee(em1);
        Fuel fl1 = new Fuel();
        fl1.setId(3l);
        t1.setFuel(fl1);
        tr.add(t1);

        Transaction t2 = new Transaction();
        t2.setFuelPrice(3.20);
        t2.setFuelQuantity(44.2);
        t2.setTotalPrice(141.44);
        Customer cs2 = new Customer();
        cs2.setId(1l);
        t2.setCustomer(cs2);
        Employee em2 = new Employee();
        em2.setId(1l);
        t2.setEmployee(em2);
        Fuel fl2 = new Fuel();
        fl2.setId(3l);
        t2.setFuel(fl2);
        Set<Product> products = new HashSet<>();
        Product prod1 = new Product();
        prod1.setId(1l);
        products.add(prod1);
        Product prod2 = new Product();
        prod2.setId(1l);
        products.add(prod2);
        Product prod3 = new Product();
        prod3.setId(9l);
        products.add(prod3);
        t2.setProducts(products);
        tr.add(t2);

        Transaction t3 = new Transaction();
        t3.setFuelPrice(3.5);
        t3.setFuelQuantity(115.1);
        t3.setTotalPrice(402.85);
        Customer cs3 = new Customer();
        cs3.setId(2l);
        t3.setCustomer(cs3);
        Employee em3 = new Employee();
        em3.setId(1l);
        t3.setEmployee(em3);
        Fuel fl3 = new Fuel();
        fl3.setId(1l);
        t3.setFuel(fl3);
        tr.add(t3);

        Transaction t4 = new Transaction();
        t4.setFuelPrice(3.5);
        t4.setFuelQuantity(88.8);
        t4.setTotalPrice(310.8);
        Customer cs4 = new Customer();
        cs4.setId(2l);
        t4.setCustomer(cs4);
        Employee em4 = new Employee();
        em4.setId(2l);
        t4.setEmployee(em4);
        Fuel fl4 = new Fuel();
        fl4.setId(1l);
        t4.setFuel(fl4);
        tr.add(t4);
        Set<Product> products2 = new HashSet<>();
        Product prod4 = new Product();
        prod4.setId(4l);
        products2.add(prod4);
        Product prod5 = new Product();
        prod5.setId(10l);
        products2.add(prod5);
        t4.setProducts(products2);
        tr.add(t4);

        Transaction t5 = new Transaction();
        t5.setFuelPrice(3.5);
        t5.setFuelQuantity(230.5);
        t5.setTotalPrice(806.75);
        Customer cs5 = new Customer();
        cs5.setId(2l);
        t5.setCustomer(cs5);
        Employee em5 = new Employee();
        em5.setId(2l);
        t5.setEmployee(em5);
        Fuel fl5 = new Fuel();
        fl5.setId(1l);
        t5.setFuel(fl5);
        tr.add(t5);


        //System.out.println(pr.findAll());
        System.out.println(pr.findBynameContaining("żu"));
    }
}
