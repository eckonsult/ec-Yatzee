package api.controller.model;

import org.hibernate.model.Player;

public class PlayerModel {

	private int ID;
	private String FirstName;
	private String LastName;
	
	public PlayerModel(Player dbPlayer) {
		
		ID = dbPlayer.getId();
		FirstName = dbPlayer.getFirstName();
		LastName = dbPlayer.getLastName();
		
	}

}
