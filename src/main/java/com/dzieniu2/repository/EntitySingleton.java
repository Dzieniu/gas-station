package com.dzieniu2.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitySingleton {
    private static EntityManager entityManager = null;

    private EntitySingleton() { }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gas-station.jpa");
            entityManager = entityManagerFactory.createEntityManager();
        }

        return entityManager;
    }
}
