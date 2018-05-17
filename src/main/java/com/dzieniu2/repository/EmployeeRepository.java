package com.dzieniu2.repository;

import com.dzieniu2.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Employee findById(Long id) {
        System.out.println(id);
        return em.find(Employee.class, id);
    }

    public Employee findByLogin(String login){
        Query query = em.createQuery("FROM Employee E WHERE E.login = '"+login+"'");
        return (Employee) query.getSingleResult();
    }

    public List<Employee> findAll(){
        Query query = em.createQuery("FROM Employee");
        return (List<Employee>) query.getResultList();
    }

    public void add(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    public Employee update(Employee employee) {
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        return employee;
    }

    public void delete(Long id) {
        Employee employee = findById(id);
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }

}
