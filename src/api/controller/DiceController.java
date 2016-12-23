package api.controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.controllerservice.DiceControllerService;
import api.model.Dice;
import api.model.DiceSet;


@Path("/Dice")
public class DiceController {

    @GET
    @Produces("application/json")
    public Response dice() throws JSONException {
        Gson gson = new GsonBuilder().create();   
        Dice dice = DiceControllerService.GetDice();
        String json = gson.toJson(dice);      
        return Response.status(200).entity(json).build();
    }

    @Path("Set/{amount}")
    @GET
    @Produces("application/json")
    public Response diceSet(@PathParam("amount") int f) throws JSONException {
        
        DiceSet ds = DiceControllerService.GetDiceSet(f);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(ds);
        return Response.status(200).entity(json).build();
    }
    @Path("/Set")
    @GET
    @Produces("application/json")
    public Response diceSet() throws JSONException {
        
        DiceSet ds = DiceControllerService.GetDiceSet(5);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(ds);
        return Response.status(200).entity(json).build();
    }
}