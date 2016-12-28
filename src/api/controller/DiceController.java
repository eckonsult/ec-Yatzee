package api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONException;

import api.controllerservice.DiceControllerService;
import api.model.Dice;
import api.model.DiceSet;

@Path("/Dice")
public class DiceController extends ControllerBase {

    final static Logger logger = Logger.getLogger(DiceController.class);

    @GET
    @Produces("application/json")
    public Response dice() throws JSONException {
        logger.debug("Get 1 Dice");
        Dice dice = DiceControllerService.GetDice();
        return OkResponse(dice);
    }

    @Path("/Set")
    @GET
    @Produces("application/json")
    public Response diceSet() throws JSONException {
        logger.debug("Get 1 set of 5 Dice");
        DiceSet ds = DiceControllerService.GetDiceSet(5);
        return OkResponse(ds);
    }

    @Path("Set/{amount}")
    @GET
    @Produces("application/json")
    public Response diceSet(@PathParam("amount") int f) throws JSONException {
        if (f <= 0) {
            logger.info(f + "is less then 0");
            return BadRequestResponse("amount has to be greater then 0");
        }
        logger.debug("Get 1 set of " + f + " Dice");
        DiceSet ds = DiceControllerService.GetDiceSet(f);
        return OkResponse(ds);
    }
}