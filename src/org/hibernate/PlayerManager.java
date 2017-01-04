package org.hibernate;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.model.Player;
import org.hibernate.query.Query;

import java.util.*;

import javax.transaction.Transaction;

import org.hibernate.util.HibernateUtil;
 
public class PlayerManager {
 
    
 
    public void createAndStorePlayer(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
        	Player thePlayer = new Player();
        	thePlayer.setFirstName(firstName);
        	thePlayer.setLastName(lastName);
        	
        	session.beginTransaction();

        	session.save(thePlayer);
 
        	session.getTransaction().commit();
        
        	System.out.println("ID for " + thePlayer.getFirstName() + " " + thePlayer.getLastName() + " is " + thePlayer.getId());
        }
        catch (HibernateException ex) {
        	if (session.getTransaction() != null)
        		session.getTransaction().rollback();
        	ex.printStackTrace();	
        }
        finally {
        	session.close();
        }
    }

    
    public int getPlayerID(String firstName, String lastName) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try {    	
	    	session.beginTransaction();
	    	Query query = session.createQuery("from Player where FirstName=:fName and LastName=:lName");
	    	query.setParameter("fName", firstName);
	    	query.setParameter("lName", lastName);
	    	
	    	List results = query.list();
	        session.getTransaction().commit();
	        
	        Player thePlayer = (Player) results.get(0);
	        
	        //if player isn't found
	        if (thePlayer != null)
	        	return thePlayer.getId();
        }
        catch (HibernateException ex) {
        	if (session.getTransaction() != null)
        		session.getTransaction().rollback();
        	ex.printStackTrace();	
        }
        finally {
        	session.close();
        }
    	return -1;        
    }
    
    
    public Player getPlayerByID(int id) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Player thePlayer = null;
    	try {
	    	session.beginTransaction();
	        thePlayer = (Player) session.get(Player.class, id);     
	        session.getTransaction().commit();
    	}
        catch (HibernateException ex) {
        	if (session.getTransaction() != null)
        		session.getTransaction().rollback();
        	ex.printStackTrace();	
        }
        finally {
        	session.close();
        }
    	
    	return thePlayer;
    }
    
    public List listPlayers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List result = null;
        try {
        	session.beginTransaction();
        	result = session.createQuery("from Player").list();
        	session.getTransaction().commit();
    	}
        catch (HibernateException ex) {
        	if (session.getTransaction() != null)
        		session.getTransaction().rollback();
        	ex.printStackTrace();	
        }
        finally {
        	session.close();
        }
    	
    	return result;
    }
 
}