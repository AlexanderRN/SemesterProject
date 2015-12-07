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

    public String getDataFromURL(String from, String date, String persons) throws MalformedURLException {
        List<URL> URLS = new ArrayList<URL>();
        
        URL url = new URL("http://angularairline-plaul.rhcloud.com/api/flightinfo/" + from + "/" + date + "/" + persons);
        URLS.add(url);
            String output = "";
            String output2 = "";
        try {

            HttpURLConnection conn = (HttpURLConnection) URLS.get(0).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

           // System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                output2 += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            //System.err.println(e.getMessage());
           e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return output2;
    }

}
