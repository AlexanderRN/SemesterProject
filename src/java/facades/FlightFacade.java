package facades;

import entity.Flightinstance;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FlightFacade {

    private EntityManagerFactory emf;
    static CallableFacade cf;

    public FlightFacade() {

        EntityManagerFactory e = Persistence.createEntityManagerFactory("PU-Local");
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Flightinstance> getTrips(String origin) {
        EntityManager em = getEntityManager();
        String query1 = "SELECT fi FROM Flightinstance fi ";
        query1 += "JOIN fi.flight fif ";
        query1 += "JOIN fif.airline fia ";
        query1 += "WHERE fi.origin.iataCode = :o ";
        Query query = em.createQuery(query1);
        query.setParameter("o", origin);
        List<Flightinstance> list = query.getResultList();
        return list;
    }

    public String getDataFromURL(String from, String to, String date, String persons) throws MalformedURLException {
        List<String> u = getUrls();
        CallableFacade g = new CallableFacade(u,"",from, to, date, persons);
      return "{\"airlines\": [" + g.runThreads() + "]}";
    
    }

     public List<String> getUrls(){
        EntityManager em = getEntityManager();
        String sql = "SELECT u.url FROM Url u";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
 
}
