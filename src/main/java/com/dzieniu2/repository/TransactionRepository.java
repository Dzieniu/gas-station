package com.dzieniu2.repository;

import com.dzieniu2.entity.Transaction;

import javax.persistence.EntityManager;

public class TransactionRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Transaction findById(Long id) {
        return em.find(Transaction.class, id);
    }

    public void add(Transaction transaction) {
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
    }

    public Transaction update(Transaction transaction) {
        em.getTransaction().begin();
        em.merge(transaction);
        em.getTransaction().commit();
        return transaction;
    }

    public void delete(Long id) {
        Transaction transaction = findById(id);
        em.getTransaction().begin();
        em.remove(transaction);
        em.getTransaction().commit();
    }

}
