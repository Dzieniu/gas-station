package com.dzieniu2.repository;

import com.dzieniu2.Main;
import com.dzieniu2.entity.Fuel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
public class FuelRepositoryTest {

    FuelRepository fr = new FuelRepository();

    @Test
    public void testFindByName() {
        Fuel fuel = fr.findByName("ON");
        Assert.assertEquals("ON", fuel.getName());
        Assert.assertEquals(new Double(3.20), fuel.getPrice());
        Assert.assertEquals(new Long(3), fuel.getContainer().getId());
    }

    @Test
    public void testFindAll() {
        List<Fuel> fuels = fr.findAll();
        Assert.assertNotNull(fuels);
    }

    @Test
    public void testAdd() {
        Fuel fuel = new Fuel();
        fuel.setPrice(4.44);
        fuel.setName("Pb 66");
        fr.add(fuel);

        Fuel newFuel = fr.findByName("Pb 66");
        Assert.assertEquals("Pb 66", newFuel.getName());
        Assert.assertEquals(new Double(4.44), newFuel.getPrice());
    }


}
