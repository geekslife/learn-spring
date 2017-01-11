package org.hibernate.tutorial.annotations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by geekslife on 2017. 1. 11..
 */
public class PersonTest {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Test
    public void oneToOneTest() {
        Cellular cellular = new Cellular();
        cellular.setNumber(521);
        entityManager.persist(cellular);

        Person person = new Person();
        person.setName("woniper");
        person.setCellular(cellular);
        entityManager.persist(person);

        Assert.assertEquals(person.getCellular().getId(), cellular.getId());
    }

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void after() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
