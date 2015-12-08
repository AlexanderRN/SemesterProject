
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import entity.Flightinstance;
import facades.FlightFacade;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;


@Path("flightinfo")
public class FlightApi {
Gson gson;
static FlightFacade f = new FlightFacade();

    @Context
    private UriInfo context;

    
    public FlightApi() {
         gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy( FieldNamingPolicy.IDENTITY ).create();
    }

    /**
     * Retrieves representation of an instance of rest.FlightApi
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FlightApi
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
//    @GET
//    @Produces("application/json")
//    @Path( "{origin}" )
//    public String getFlights(@PathParam( "origin" ) String origin){
//        List<Flightinstance> a = f.getTrips(origin);
//        
//        JsonObject obj = new JsonObject();
//        obj.addProperty( "airline",a.get(0).getFlight().getNumberOfSeats());
//        obj.addProperty( "descr", a.get(0).getFlight().getAirline().getName());
//            
//        return gson.toJson(obj);
//    }
    
    @GET
    @Produces("application/json")
    @Path("{from}/{date}/{persons}")
    public String getFlights(@PathParam("from") String from, 
                             @PathParam("date") String date, 
                             @PathParam("persons") String persons) throws MalformedURLException
    {
        String response = f.getDataFromURL(from, "", date, persons);
        
        return response;
    }
    
    @GET
    @Produces("application/json")
    @Path("{from}/{to}/{date}/{persons}")
    public String getFlightsTo(@PathParam("from") String from, 
                             @PathParam("to") String to,
                             @PathParam("date") String date, 
                             @PathParam("persons") String persons) throws MalformedURLException
    {
        String response = f.getDataFromURL(from, to, date, persons);
        
        return response;
    }
    
    
}
