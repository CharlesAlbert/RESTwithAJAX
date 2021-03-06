/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author sabre
 */
@Path("quote")
public class Quote {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man   , is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Quote() {
    }

    /**
     * READ - GET
     */
    @GET
    @Produces("application/json")
    @Path("/random")
    public String getRandomQuote() throws QuoteNotFoundException {

        int collectionLength = quotes.size() + 1;

        Random rand = new Random();

        int rdmQuoteID = rand.nextInt(collectionLength) + 1;

        String quote = quotes.get(rdmQuoteID);
        JsonObject response = new JsonObject();
        response.addProperty("quote", quote);
        return gson.toJson(response);

    }

    @GET
    @Produces("application/json")
    @Path("{id}")
    public String getSpecificQuote(@PathParam("id") String id) throws QuoteNotFoundException {

        int number = Integer.parseInt(id);
        if (quotes.get(number) == null) {
            throw new QuoteNotFoundException("No quote on id:" + number);
        } else {

            String quote = quotes.get(number);
            JsonObject response = new JsonObject();
            response.addProperty("quote", quote);
            return gson.toJson(response);

        }

    }

    /**
     * UPDATE- PUT
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String putQuote(@PathParam("id") String id, String quote) {

        JsonObject request = parser.parse(quote).getAsJsonObject();

        int newId = Integer.parseInt(id);
        String ting = request.get("quote").getAsString();
        System.out.println("ren quote" + ting);
        System.out.println("id" + id);
        quotes.put(newId, ting);

        // if(quotes.get(quotes.size()+1)==null)
        JsonObject response = new JsonObject();
        response.addProperty("id", newId);
        response.addProperty("quote", ting);
        return gson.toJson(response);

    }

    /**
     * CREATE - POST
     */
    @POST
    public String createQuote(String content) {

        JsonObject request = parser.parse(content).getAsJsonObject();

        String quote = request.get("quote").getAsString();

        // if(quotes.get(quotes.size()+1)==null)
        quotes.put(quotes.size() + 1, quote);

        JsonObject response = new JsonObject();
        response.addProperty("id", quotes.size());
        response.addProperty("quote", quote);
        return gson.toJson(response);

    }

    /**
     * DELETE
     */
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public void deleteQuote(@PathParam("id") String id) {

        quotes.remove(Integer.parseInt(id));

    }

}
