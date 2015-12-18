package unitTest;

import entity.Flightinstance;
import entity.User;
import facades.FlightFacade;
import facades.UserFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.Test;
import static org.junit.Assert.*;

public class NewEmptyJUnitTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-Local");

//    public NewEmptyJUnitTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
    @Test
    public void testRegisterUser() {
        EntityManager em = emf.createEntityManager();
        UserFacade uf = new UserFacade();

        uf.registerUser("junitTest", "junitTest");
        User assertUser;

        try {

            assertUser = em.find(User.class, "junitTest");
        } finally {
            em.close();
       }

        assertEquals(assertUser.getUserName(), "junitTest");
    }

    @Test
    public void testUserRole() {
        EntityManager em = emf.createEntityManager();
        User assertUser;

        try {
            assertUser = em.find(User.class, "User");
        } finally {
            em.close();
        }

        assertEquals(assertUser.getRoles().get(0).getRoleName(), "User");
    }

    FlightFacade ff = new FlightFacade();
// Denne test kræver at der bliver opretet en airline airport flight flightintance
    @Test
    public void testTrips() {
        List<Flightinstance> d = ff.getTrips("iata");
        assertEquals("iata", d.get(0).getOrigin().getIataCode());
    }
    
   @Test 
   public void testUrls(){
      List<String> c = ff.getUrls();
       assertEquals("http://angularairline-plaul.rhcloud.com/api/flightinfo/",c.get(0));
   }

   
}
