package com.dzieniu2.repository;

import com.dzieniu2.entity.TransactionFuel;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TransactionRepository {

    EntityManager em = EntitySingleton.getInstance();

    public TransactionFuel findById(Long id) {
        return em.find(TransactionFuel.class, id);
    }

    public List<TransactionFuel> findAll(){
        Query query = em.createQuery("FROM transaction ");
        return (List<TransactionFuel>) query.getResultList();
    }

    public void add(TransactionFuel transactionFuel) {
        em.getTransaction().begin();
        em.persist(transactionFuel);
        em.getTransaction().commit();
    }

    public TransactionFuel update(TransactionFuel transactionFuel) {
        em.getTransaction().begin();
        em.merge(transactionFuel);
        em.getTransaction().commit();
        return transactionFuel;
    }

    public void delete(Long id) {
        TransactionFuel transactionFuel = findById(id);
        em.getTransaction().begin();
        em.remove(transactionFuel);
        em.getTransaction().commit();
    }

}
