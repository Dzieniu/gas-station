package com.dzieniu2.repository;

import com.dzieniu2.entity.Customer;

import javax.persistence.EntityManager;

public class CustomerRepository {

    EntityManager em = EntitySingleton.getInstance().getEntityManager();

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
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
