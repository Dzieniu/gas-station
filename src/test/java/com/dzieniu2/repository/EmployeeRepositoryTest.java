package com.dzieniu2.repository;

import com.dzieniu2.Main;
import com.dzieniu2.entity.Employee;
import com.dzieniu2.entity.enums.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
public class EmployeeRepositoryTest {

    EmployeeRepository er = new EmployeeRepository();

    @Test
    public void testFindById() {
        Employee employee = er.findById(1l);
        Assert.assertEquals(new Long(1), employee.getId());
        Assert.assertEquals("admin", employee.getLogin());
        Assert.assertEquals(Role.ADMIN, employee.getRole());
    }

    @Test
    public void testAdd() {
        Employee employee = new Employee();
        employee.setLogin("leszek");
        employee.setPassword("");
        employee.setRole(Role.ADMIN);
        er.add(employee);

        Employee testEmployee = er.findByLogin("leszek");
        Assert.assertEquals("leszek", testEmployee.getLogin());
        Assert.assertEquals("", testEmployee.getPassword());
        Assert.assertEquals(Role.ADMIN, testEmployee.getRole());
    }

    @Test
    public void testFindByLogin() {
        Employee employee = er.findByLogin("admin");
        Assert.assertEquals(new Long(1), employee.getId());
        Assert.assertEquals("admin", employee.getLogin());
        Assert.assertEquals(Role.ADMIN, employee.getRole());
    }

    @Test
    public void testUpdate() {
        Employee employee = er.findById(1l);
        employee.setLogin("szef");
        er.update(employee);
        Employee updatedEmployee = er.findById(1l);

        Assert.assertEquals(employee.getId(), updatedEmployee.getId());
        Assert.assertEquals(employee.getLogin(), updatedEmployee.getLogin());
    }

    @Test
    public void testDelete() {
        int totalEmployees = er.findAll().size();
        er.delete(2l);
        int totalEmployeesAfterDelete = er.findAll().size();
        Assert.assertNotEquals(totalEmployees, totalEmployeesAfterDelete);
    }

}
