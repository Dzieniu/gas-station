package com.dzieniu2.repository;

import com.dzieniu2.entity.Customer;
import com.dzieniu2.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class CustomerRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public Customer findByCardCode(String cardCode) {
        Query query = em.createQuery("FROM Customer C WHERE C.cardCode = '"+cardCode+"'");
        try{
            return (Customer) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Customer> findAll(){
        Query query = em.createQuery("FROM Customer ");
        return (List<Customer>) query.getResultList();
    }

    public void add(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public Customer update(Customer customer) {
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        return customer;
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }
}
