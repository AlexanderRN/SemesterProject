
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Flightinstance;
import facades.FlightFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;


@Path("trips")
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
    
    @GET
    @Produces("application/json")
    @Path( "{origin}" )
    public String getFlights(@PathParam( "origin" ) String origin){
        List<Flightinstance> a = f.getTrips(origin);
        
        JsonObject obj = new JsonObject();
        obj.addProperty( "airline",a.get(0).getFlight().getNumberOfSeats());
        obj.addProperty( "descr", a.get(0).getFlight().getAirline().getName());
//      obj.addProperty( "price", cf.getCurrency( code ).getAmount() );

        
        
        return gson.toJson(obj);
    }
    
    
}
