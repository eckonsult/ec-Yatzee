package test.manager;

import java.util.List;

import org.hibernate.PlayerManager;
import org.hibernate.model.Player;


public class PlayerManagerTest {

	static PlayerManager mgr;
	public static void main(String[] args) {
		
    	
    	createPlayer("Hans","Logger");
 
    	getPlayerByID(70);
    	
        listAllPlayers();
    	    	    	
    	getPlayerID();
    	       
    }
	
	
	private static PlayerManager getMgr() {
		if (mgr == null)
			mgr = new PlayerManager();
		return mgr;
	}


	/**
	 * search for playerID
	 */
	private static void getPlayerID() {
		int plID = getMgr().getPlayerID("Rut","Tjur");
    	
    	if (plID == -1)
    		System.out.println("Player not found!");
    	else
    		System.out.println("ID for Rut Tjur is " + plID);
	}
	
	/**
	 * list all players
	 */
	private static void listAllPlayers() {
		List playerList = getMgr().listPlayers();
        for (int i = 0; i < playerList.size(); i++) {
            Player thePlayer = (Player) playerList.get(i);
            System.out.println(
                    "Player: " + thePlayer.getFirstName() + thePlayer.getLastName()
            );
        }
	}
	/**
	 * look for player with specific ID
	 * @param searchID
	 */
	private static void getPlayerByID(int searchID) {
		Player thePlay = getMgr().getPlayerByID(searchID);
    	
    	if (thePlay == null)
			System.out.println("Player not found!");
		else
    		System.out.println("Result for searching for player with PlayerID " + searchID + ": FirstName :" + 
    				thePlay.getFirstName() + "LastName: " + 
    				thePlay.getLastName());
	}
	/**
	 * creates new player
	 * @param firstName first name of player
	 * @param lastName last name of player
	 */
	private static void createPlayer(String firstName, String lastName) {
		getMgr().createAndStorePlayer(firstName, lastName);
	}

}
