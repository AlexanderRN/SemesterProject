package rest;

import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
//@RolesAllowed("User")
public class User {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}"; 
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{user}/{pass}")
  public boolean registerUser(@PathParam("user") String user, @PathParam("pass") String pass) {
      UserFacade uf = new UserFacade();
      uf.registerUser(user, pass);

      entity.User user1 = uf.getUserByUserId(user);
      
      if(user1 != null)
      {
          return true;
      }
      else
      {
          return false;
      }
  }
 
}