package facades;

import entity.Flightinstance;
import java.util.ArrayList;
import java.util.List;
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
        query1 +="WHERE fi.origin.iataCode = :o ";
        Query query = em.createQuery(query1);
        query.setParameter("o", origin);
        List<Flightinstance> list = query.getResultList();
//        System.out.println(list.get(0));
        return list;
    }
}
