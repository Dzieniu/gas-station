package com.dzieniu2.repository;

import com.dzieniu2.entity.Employee;
import com.dzieniu2.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TransactionRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Transaction findById(Long id) {
        return em.find(Transaction.class, id);
    }

    public List<Transaction> findAll(){
        Query query = em.createQuery("FROM transaction ");
        return (List<Transaction>) query.getResultList();
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
