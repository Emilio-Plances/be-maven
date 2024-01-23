package org.example.dao;

import org.example.entities.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EventDao {
    private EntityManagerFactory emf;
    private EntityManager em;
    public EventDao(){
        emf= Persistence.createEntityManagerFactory("epicodejpa");
        em= emf.createEntityManager();
    }

    public Event addEvent(Event e){
        EntityTransaction et= em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
        em.refresh(e);
        return e;
    }

    public Event getEventById(int id){
        return em.find(Event.class,id);
    }
    public void deleteEvent(int id){
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.remove( getEventById(id) );
        et.commit();
    }
}
