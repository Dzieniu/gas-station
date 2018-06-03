package com.dzieniu2.repository;

import com.dzieniu2.Main;
import com.dzieniu2.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
public class CustomerRepositoryTest {

    CustomerRepository cr = new CustomerRepository();
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() throws IOException {
        if (!setUpIsDone) {
            setUpIsDone = true;
            Main.loadData();
        }
    }

    @Test
    public void testFindById() {
        Customer customer = cr.findById(1l);
        Assert.assertEquals(new Long(1), customer.getId());
        Assert.assertEquals("Mieczysław", customer.getName());
        Assert.assertEquals("Łopata", customer.getSurname());
        Assert.assertEquals("#5555", customer.getCardCode());
    }

    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setName("Adam");
        customer.setSurname("Biały");
        customer.setCardCode("#1234");
        cr.add(customer);

        Customer testCustomer = cr.findByCardCode("#1234");
        Assert.assertEquals("Adam", testCustomer.getName());
        Assert.assertEquals("Biały", testCustomer.getSurname());
        Assert.assertEquals("#1234", testCustomer.getCardCode());
    }

    @Test
    public void testFindByCardCode() {
        Customer customer = cr.findByCardCode("#5555");
        Assert.assertEquals(new Long(1), customer.getId());
        Assert.assertEquals("Mieczysław", customer.getName());
        Assert.assertEquals("Łopata", customer.getSurname());
        Assert.assertEquals("#5555", customer.getCardCode());
    }

    @Test
    public void testUpdate() {
        Customer customer = cr.findById(1l);
        customer.setName("Adrian");
        customer.setSurname("Niebieski");
        cr.update(customer);
        Customer updatedCustomer = cr.findById(1l);

        Assert.assertEquals(customer.getId(), updatedCustomer.getId());
        Assert.assertEquals(customer.getName(), updatedCustomer.getName());
        Assert.assertEquals(customer.getSurname(), updatedCustomer.getSurname());
    }

    @Test
    public void testDelete() {
        int totalCustomers = cr.findAll().size();
        cr.delete(2l);
        int totalCustomersAfterDelete = cr.findAll().size();
        Assert.assertNotEquals(totalCustomers, totalCustomersAfterDelete);
    }
}
