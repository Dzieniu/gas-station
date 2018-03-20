package com.dzieniu2.repository;

import com.dzieniu2.entity.Product;

import javax.persistence.EntityManager;

public class ProductRepository {

    EntityManager em = EntitySingleton.getInstance().getEntityManager();

    public Product findById(Long id) {
        return em.find(Product.class, id);
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
