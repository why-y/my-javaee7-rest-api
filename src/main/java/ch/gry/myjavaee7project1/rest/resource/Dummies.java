package ch.gry.myjavaee7project1.rest.resource;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.gry.myjavaee7project1.musicshelf.ejb.DummiesService;
import ch.gry.myjavaee7project1.musicshelf.model.Dummy;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
*
* @author yvesgross
*/
@Stateless
@Path("dummies")
public class Dummies {

    private static final Logger logger = Logger.getLogger(Dummies.class.getName());

    @Inject
    DummiesService dummiesService;

    /**
    *
    * @param newDummy
    * @return
    */
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Dummy createDummy(final Dummy newDummy) {
       logger.info("REST-POST: createDummy()");
       return dummiesService.create(newDummy);      
   }
   
    /**
    *
    * @return
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Dummy> getDummies() {
       logger.info("REST-GET: getDummies()");
       return dummiesService.getAll();
   }
   
   @GET
   @Path("{dummyId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Dummy getDumy(@PathParam("dummyId") final Long dummyId) {
       logger.info(String.format("REST-GET: getDummy(%d)", dummyId));
       try {
           return dummiesService.get(dummyId);
       } catch (ResourceNotFoundException ex) {
           throw new NotFoundException(ex);
       }
   }
   
   /**
   *
   * @param dummyId
   * @param dummy
   */
  @PUT
  @Path("{dummyId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateDummy(@PathParam("dummyId") final Long dummyId, final Dummy dummy) {
      logger.info(String.format("REST-PUT: updateDummy(%d)", dummyId));
      try {

          // Make sure to update the object with the id of the resource.
          // I.e. the id of the given update data is irrelevant.
          dummy.setId(dummyId);

          dummiesService.update(dummy);
      } catch (ResourceNotFoundException ex) {
          throw new NotFoundException(ex);
      }
  }

  /**
   *
   * @param dummyId
   */
  @DELETE
  @Path("{dummyId}")
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteDummy(@PathParam("dummyId") final Long dummyId) {
      logger.info(String.format("REST-DELETE: deleteDummy(%s)", dummyId));
      try {
          dummiesService.delete(dummyId);
      } catch (ResourceNotFoundException ex) {
          throw new NotFoundException(ex);
      }
  }
  
  /**
   *
   * @return
   */
  @GET
  @Path("quantity")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject countDummys() {
      logger.info("REST-GET: countDummys()");
      return Json.createObjectBuilder().
              add("numOfDummys", dummiesService.count()).
              build();
  }

}
