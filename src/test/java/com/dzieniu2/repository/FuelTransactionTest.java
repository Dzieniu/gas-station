package com.dzieniu2.repository;

import com.dzieniu2.Main;
import com.dzieniu2.entity.TransactionFuel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
public class FuelTransactionTest {

    FuelTransactionRepository ftr = new FuelTransactionRepository();

    @Test
    public void testFindById() {
        TransactionFuel transactionFuel = ftr.findById(1l);
        Assert.assertEquals(new Double(3.20), transactionFuel.getFuelPrice());
        Assert.assertEquals(new Double(70.0), transactionFuel.getFuelQuantity());
        Assert.assertEquals(new Double(224.0), transactionFuel.getTotalPrice());
        Assert.assertEquals(new Long(1), transactionFuel.getCustomer().getId());
        Assert.assertEquals(new Long(1), transactionFuel.getEmployee().getId());
        Assert.assertEquals(new Long(3), transactionFuel.getFuel().getId());
    }

    @Test
    public void findAll() {
        List<TransactionFuel> transactionFuels = ftr.findAll();
        Assert.assertNotNull(transactionFuels);
    }

    @Test
    public void delete() {
        int transactionFuelsCount = ftr.findAll().size();
        ftr.delete(2l);
        int transactionFuelsAfterDeleteCount = ftr.findAll().size();
        Assert.assertNotEquals(transactionFuelsCount, transactionFuelsAfterDeleteCount);
        Assert.assertNull(ftr.findById(2l));
    }

}
