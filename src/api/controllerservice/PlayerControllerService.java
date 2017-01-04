package api.controllerservice;

import org.apache.log4j.Logger;
import org.hibernate.PlayerManager;
import org.hibernate.model.Player;

import api.controller.model.PlayerModel;

public class PlayerControllerService {

	final static Logger logger = Logger.getLogger(PlayerControllerService.class);
	

	public static PlayerModel GetPlayer(int id) {

		Player dbPlayer = PlayerManager.getPlayerByID(id);
		if (dbPlayer == null) {
			logger.debug("dbPlayer equals null.");
			return null;
		}
		PlayerModel pl = null;
		try {
			pl = new PlayerModel(dbPlayer);
			logger.debug("dbPlayer playermodel created.");
		}
		catch (Exception ex) {
			logger.error("dbPlayer playermodel failed to be created.", ex);
		}
		return pl;
	}

	public static boolean DeletePlayer(int id) {
		boolean result = false;
		try {
			PlayerManager.deletePlayer(id);
			logger.info("Player with id " +id+ " has been deleted.");
		}
		catch(Exception ex){
			logger.error("", ex);
		}
		
		return result;
	}

	
}
