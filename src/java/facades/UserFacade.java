package facades;

import deploy.DeploymentConfiguration;
import entity.Role;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordHash;

public class UserFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public UserFacade() {

    }

    public User getUserByUserId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, userName);
            if (user == null) {
                return null;
            }
            try {
                boolean authenticated = PasswordHash.validatePassword(password, user.getPassword());
                return authenticated ? user.getRolesAsStrings() : null;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } finally {
            em.close();
        }

    }

    public void registerUser(String user, String pass) {
        EntityManager em = emf.createEntityManager();
        try {
            User newUser = new User(user, PasswordHash.createHash(pass));
            newUser.AddRole(em.find(Role.class, "User"));

            if (newUser.getUserName() != null && newUser.getPassword() != null) {
                em.getTransaction().begin();
                em.persist(newUser);
                em.getTransaction().commit();
            } else {
                System.out.println("Please fill out all fields.");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e);
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
