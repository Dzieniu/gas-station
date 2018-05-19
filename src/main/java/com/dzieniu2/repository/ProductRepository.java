package com.dzieniu2.repository;

import com.dzieniu2.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        Query query = em.createQuery("FROM Product");
        return query.getResultList();
    }

    public List<Product> findBynameContaining(String exp) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :expression");
        query.setParameter("expression", "%"+exp+"%");
        return query.getResultList();
    }

    public void add(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public Product update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        return product;
    }

    public void delete(Long id) {
        Product product = findById(id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

}
