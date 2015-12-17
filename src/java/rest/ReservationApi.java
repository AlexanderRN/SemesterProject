/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Passenger;
import entity.Reservation;
import facades.FlightFacade;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import jdk.nashorn.internal.parser.JSONParser;
import org.eclipse.persistence.sessions.serializers.JSONSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Lenovo
 */
@Path( "reservation" )
public class ReservationApi
{

    Gson gson;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservationApi
     */
    public ReservationApi()
    {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy( FieldNamingPolicy.IDENTITY ).create();
    }

    /**
     * Retrieves representation of an instance of rest.ReservationApi
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces( "application/json" )
    public String getJson()
    {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ReservationApi
     *
     * @param iatacode
     * @param from
     * @param to
     * @param res_date
     * @param traveltime
     * @param price
     * @param content representation for the resource
     * @param passangers
     *
     * @return an HTTP response with content of the updated or created resource.
     */
//    @PUT
//    @Consumes( "application/json" )
//    @Path( "{from}, {to}, {res_date}, {traveltime}, {price}, {passengers}" )
//    public String putJson( @PathParam( "from" ) String from,
//            @PathParam( "to" ) String to,
//            @PathParam( "res_date" ) String res_date,
//            @PathParam( "traveltime" ) String traveltime,
//            @PathParam( "price" ) String price,
//            @PathParam( "passengers" ) String passengers ) throws JSONException
//    {
//        Double pPrice = Double.parseDouble( price );
//
//        Passenger[] pasArray = gson.fromJson( passengers, Passenger[].class );
//
//        List<Passenger> pasList = new ArrayList();
//
//        for ( int i = 0; i < pasArray.length; i++ )
//        {
//            pasList.add( pasArray[i] );
//        }
//
//        FlightFacade f = new FlightFacade();
//        f.setReservation( from, to, pPrice, res_date, traveltime, pasList );
//
//        return "OK";
//    }

    @PUT
    @Consumes( "application/json" )
    @Path( "insert/{resJson}/{pasJson}" )
    public boolean insertReservation( @PathParam( "resJson" ) String resJson, @PathParam( "pasJson" ) String pasJson ) throws JSONException
    {
        
        // GET FLIGT INFO & PASSENGER INFO
        JSONObject obj = new JSONObject( resJson );
        JSONObject pasObj = new JSONObject( pasJson );

        // FLIGHT INFO
        String airline = obj.getJSONObject( "reservation" ).getString( "airline" );
        String origin = obj.getJSONObject( "reservation" ).getString( "origin" );
        String destination = obj.getJSONObject( "reservation" ).getString( "destination" );
        String date = obj.getJSONObject( "reservation" ).getString( "date" );
        String numberOfSeats = obj.getJSONObject( "reservation" ).getString( "numberOfSeats" );
        String traveltime = obj.getJSONObject( "reservation" ).getString( "traveltime" );
        String totalprice = obj.getJSONObject( "reservation" ).getString( "totalprice" );
        String username = obj.getJSONObject( "reservation" ).getString( "username" );
        
        // PASSENGERS
        JSONArray passengers = pasObj.getJSONArray( "passengers" );
        int pasCount = passengers.length();

        // PUT PASSAGERS TO ARRAYLIST
        ArrayList<Passenger> pasList = new ArrayList();
        for(int i = 0; i < pasCount; i++)
        {
            String firstname = passengers.getJSONObject( i ).getString( "firstname" );
            String lastname = passengers.getJSONObject( i ).getString( "lastname" );
            boolean reserver = true;
            
            if( i > 0 )
            {
                reserver = false;
            }
            
            Passenger p = new Passenger(firstname, lastname, reserver);
            pasList.add( p );
        }
        
        FlightFacade ff = new FlightFacade();
        boolean ifInsert = ff.setReservation( airline, origin, Integer.parseInt(numberOfSeats), destination, Double.parseDouble( totalprice ), date, traveltime, pasList, username );

        System.out.println( pasList.get( 0 ).getFirstName() );
        
        return ifInsert;
    }

}
