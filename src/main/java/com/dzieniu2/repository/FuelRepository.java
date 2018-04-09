package com.dzieniu2.repository;

import com.dzieniu2.entity.Fuel;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FuelRepository {

    EntityManager em = EntitySingleton.getInstance();

    public Fuel findById(Long id) {
        return em.find(Fuel.class, id);
    }

    public List<Fuel> findAll(){
        Query query = em.createQuery("FROM fuel ");
        return (List<Fuel>) query.getResultList();
    }

    public void add(Fuel fuel) {
        em.getTransaction().begin();
        em.persist(fuel);
        em.getTransaction().commit();
    }

    public Fuel update(Fuel fuel) {
        em.getTransaction().begin();
        em.merge(fuel);
        em.getTransaction().commit();
        return fuel;
    }

    public void delete(Long id) {
        Fuel fuel = findById(id);
        em.getTransaction().begin();
        em.remove(fuel);
        em.getTransaction().commit();
    }

}
