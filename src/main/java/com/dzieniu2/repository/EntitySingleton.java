package com.dzieniu2.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitySingleton {
    private static EntitySingleton instance = null;
    private EntityManagerFactory entitymanagerFactory = null;

    private EntitySingleton() { }

    public static EntitySingleton getInstance() {
        if (instance == null)
            instance = new EntitySingleton();

        return instance;
    }

    public EntityManager getEntityManager() {
        entitymanagerFactory = Persistence.createEntityManagerFactory("gas-station.jpa");
        EntityManager entityManager = entitymanagerFactory.createEntityManager();
        return entityManager;
    }

    public void closeEntityManagerFactory() {
        entitymanagerFactory.close();
    }
}
