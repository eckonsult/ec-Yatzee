package api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import api.controllerservice.DiceControllerService;
import api.model.Dice;
import api.model.DiceSet;

@Path("/Dice")
public class DiceController extends ControllerBase {

    @GET
    @Produces("application/json")
    public Response dice() throws JSONException {
        Dice dice = DiceControllerService.GetDice();
        return OkResponse(dice);
    }

    @Path("/Set")
    @GET
    @Produces("application/json")
    public Response diceSet() throws JSONException {
        DiceSet ds = DiceControllerService.GetDiceSet(5);        
        return OkResponse(ds);
    }

    @Path("Set/{amount}")
    @GET
    @Produces("application/json")
    public Response diceSet(@PathParam("amount") int f) throws JSONException {
        
        if(f <= 0){
            return BadRequestResponse("amount has to be greater then 0");
        }
        
        DiceSet ds = DiceControllerService.GetDiceSet(f);
        return OkResponse(ds);
    }

    
}