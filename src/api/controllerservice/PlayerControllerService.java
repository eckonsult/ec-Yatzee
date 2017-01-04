package api.controllerservice;

import org.apache.log4j.Logger;
import org.hibernate.PlayerManager;
import org.hibernate.model.Player;

import api.controller.model.PlayerModel;

public class PlayerControllerService {

	final static Logger logger = Logger.getLogger(PlayerControllerService.class);
	
	static PlayerManager PlMa;
	
	public static PlayerModel GetPlayer(int id) {
		PlayerModel pl = null;
		
		if (PlMa == null) {
			PlMa = new PlayerManager();
			logger.debug("Player repo created!");
		}
		Player dbPlayer = PlMa.getPlayerByID(id);
		if (dbPlayer == null) {
			logger.debug("dbPlayer equals null.");
			return null;
		}
		
		try {
			pl = new PlayerModel(dbPlayer);
			logger.debug("dbPlayer playermodel created.");
		}
		catch (Exception ex) {
			logger.error("dbPlayer playermodel failed to be created.", ex);
		}
		return pl;
	}

	
}
