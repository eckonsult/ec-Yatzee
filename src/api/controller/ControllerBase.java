package api.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ControllerBase {

    protected Gson gson;

    /// <summary>
    /// Returns status code 400, BadRequest
    /// </summary>
    public Response BadRequestResponse() {
        return BadRequestResponse(null);
    }

    public Response BadRequestResponse(Object o) {
        return CreateResponse(Status.BAD_REQUEST, o);
    }

    protected <T> Response CreateResponse(Status statusCode, T Value) {
        String json = "";
        if (Value != null) {
            json = GenerateJson(Value);
        }
        return Response.status(statusCode).entity(json).build();
    }

    protected String GenerateJson(Object o) {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson.toJson(o);
    }

    /** Returns status code 204, NoContent     
     */
    public Response NoContentResponse() {
        return NoContentResponse(null);
    }

    public Response NoContentResponse(Object o) {
        return CreateResponse(Status.NO_CONTENT, o);
    }

    /// <summary>
    /// Returns status code 200, Ok
    /// </summary>
    protected Response OkResponse() {
        return OkResponse(null);
    }

    protected Response OkResponse(Object o) {
        return CreateResponse(Status.OK, o);
    }
}
