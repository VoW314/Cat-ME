/*
 * Created by Osman Balci. Adapted By Shreyas Bera
 * Copyright © 2026 Osman Balci. All rights reserved
 */

package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Cat;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CatFacade extends AbstractFacade<Cat> {


    /*
    ---------------------------------------------------------------------------------------------
    The EntityManager is an API that enables database CRUD (Create Read Update Delete) operations
    and complex database searches. An EntityManager instance is created to manage entities
    that are defined by a persistence unit. The @PersistenceContext annotation below associates
    the entityManager instance with the persistence unitName identified below.
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "Cats-BeraPU")
    private EntityManager entityManager;

    // Obtain the object reference of the EntityManager instance in charge of
    // managing the entities in the persistence context identified above.
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    //Constructor
    public CatFacade(){
        super(Cat.class);
    }


    // Get Cat by ID
    public Cat getCat(String id) {
        return entityManager.find(Cat.class, id);
    }

    // find cat by name
    public Cat findByName(String name) {
        try {
            List<Cat> list = entityManager
                    .createQuery("SELECT c FROM Cat c WHERE c.name = :name", Cat.class)
                    .setParameter("name", name)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);
        } catch(NoResultException e){
            return null;
        }
    }

    // delete the cat
    public void deleteCat(String id) {
        Cat cat = entityManager.find(Cat.class, id);
        if (cat != null) {
            entityManager.remove(cat);
        }
    }

    //edit cat
    public void edit(Cat cat){
        getEntityManager().merge(cat);
    }

    // cat removal
    public void remove(Cat cat){
        getEntityManager().remove(getEntityManager().merge(cat));
    }








}
