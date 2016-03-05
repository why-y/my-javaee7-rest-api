package ch.gry.myjavaee7project1.musicshelf.dummy.boundary;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
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

import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotPersistedException;
import ch.gry.myjavaee7project1.musicshelf.dummy.control.DummiesService;
import ch.gry.myjavaee7project1.musicshelf.dummy.entity.Dummy;

/**
*
* @author yvesgross
*/
@Stateless
@Path("dummies")
public class Dummies {

    @Inject
    private Logger logger;

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
       try {
    	   return dummiesService.create(newDummy);
       } catch (EntityNotPersistedException e) {
    	   throw new BadRequestException(String.format("Could not create the dummy: %s", newDummy), e);
       }      
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
       return dummiesService.get(dummyId);
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
      } catch (EntityNotFoundException e) {
          throw new NotFoundException(e);
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
      } catch (EntityNotFoundException e) {
          throw new NotFoundException(e);
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
