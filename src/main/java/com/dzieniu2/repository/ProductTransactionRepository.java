package com.dzieniu2.repository;

import com.dzieniu2.entity.TransactionFuel;
import com.dzieniu2.entity.TransactionProduct;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductTransactionRepository {

    EntityManager em = EntitySingleton.getInstance();

    public TransactionProduct findById(Long id) {
        return em.find(TransactionProduct.class, id);
    }

    public List<TransactionProduct> findAll(){
        Query query = em.createQuery("FROM TransactionProduct ");
        return (List<TransactionProduct>) query.getResultList();
    }

    public List<TransactionProduct> findByDate(String date) {
        String query = "from TransactionProduct as tp where cast(tp.transactionDate as string) like :date";
        return em.createQuery(query, TransactionProduct.class).setParameter("date", date + "%").getResultList();
    }

    public void add(TransactionProduct transactionProduct) {
        em.getTransaction().begin();
        em.persist(transactionProduct);
        em.getTransaction().commit();
    }

    public TransactionProduct update(TransactionProduct transactionProduct) {
        em.getTransaction().begin();
        em.merge(transactionProduct);
        em.getTransaction().commit();
        return transactionProduct;
    }

    public void delete(Long id) {
        TransactionProduct transactionProduct = findById(id);
        em.getTransaction().begin();
        em.remove(transactionProduct);
        em.getTransaction().commit();
    }

}
