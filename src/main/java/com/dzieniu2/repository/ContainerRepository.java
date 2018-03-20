package com.dzieniu2.repository;

import com.dzieniu2.entity.Container;

import javax.persistence.EntityManager;

public class ContainerRepository {

    EntityManager em = EntitySingleton.getInstance().getEntityManager();

    public Container findById(Long id) {
        return em.find(Container.class, id);
    }

    public void add(Container container) {
        em.getTransaction().begin();
        em.persist(container);
        em.getTransaction().commit();
    }

    public Container update(Container container) {
        em.getTransaction().begin();
        em.merge(container);
        em.getTransaction().commit();
        return container;
    }

    public void delete(Long id) {
        Container container = findById(id);
        em.getTransaction().begin();
        em.remove(container);
        em.getTransaction().commit();
    }

}
