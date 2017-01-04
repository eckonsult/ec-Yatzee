package org.hibernate;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.model.Player;
import org.hibernate.query.Query;

import java.util.*;

import javax.transaction.Transaction;

import org.hibernate.util.HibernateUtil;
 
public class PlayerManager {
 
    public static void main(String[] args) {
    	PlayerManager mgr = new PlayerManager();
    	
//    	mgr.createAndStorePlayer("Hans", "Logger");
 
    	//look for player with specific ID
//		int searchID = 70;
//    	Player thePlay = mgr.getPlayerByID(searchID);
//    	
//    	if (thePlay == null)
//			System.out.println("Player not found!");
//		else
//    		System.out.println("Result for searching for player with PlayerID " + searchID + ": FirstName :" + 
//    				thePlay.getFirstName() + "LastName: " + 
//    				thePlay.getLastName());

//    	//list all players
//        List playerList = mgr.listPlayers();
//        for (int i = 0; i < playerList.size(); i++) {
//            Player thePlayer = (Player) playerList.get(i);
//            System.out.println(
//                    "Player: " + thePlayer.getFirstName() + thePlayer.getLastName()
//            );
//        }
    	    	
//    	//search for playerID
//    	int plID = mgr.getPlayerID("Rut","Tjur");
//    	
//    	if (plID == -1)
//    		System.out.println("Player not found!");
//    	else
//    		System.out.println("ID for Rut Tjur is " + plID);
//    	
        HibernateUtil.getSessionFactory().close();
    }
 
    private void createAndStorePlayer(String firstName, String lastName) {
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

    
    private int getPlayerID(String firstName, String lastName) {
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
    
    private List listPlayers() {
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