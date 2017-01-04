package api.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.controller.model.PlayerModel;
import api.controllerservice.PlayerControllerService;

@Path("/Player")
public class PlayerController extends ControllerBase{

    @Path("{id}")
    @GET
    @Produces("application/json")
    public Response getPlayer(@PathParam("id") int id) throws JSONException {
        
    	if (id < 0) {    		
    		return BadRequestResponse("ID need to be greater then 0.");
    	}
 
    	PlayerModel pl = PlayerControllerService.GetPlayer(id);
        
    	if (pl == null) {
    		return NoContentResponse("Player not found!");
    	}
        return OkResponse(pl);
    }
    
    @Path("{id}")
    @DELETE
    @Produces("application/json")
    public Response deletePlayer(@PathParam("id") int id) throws JSONException {
        
    	if (id < 0) {    		
    		return BadRequestResponse("ID need to be greater then 0.");
    	}
 
     boolean pl = PlayerControllerService.DeletePlayer(id);
        
     return OkResponse(pl);
    }
}