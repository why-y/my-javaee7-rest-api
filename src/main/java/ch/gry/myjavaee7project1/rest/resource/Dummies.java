package ch.gry.myjavaee7project1.rest.resource;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.gry.myjavaee7project1.musicshelf.ejb.DummiesService;
import ch.gry.myjavaee7project1.musicshelf.model.Dummy;

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
    * @return
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Dummy> getDummies() {
       logger.info("REST-GET: getDummies()");
       return dummiesService.getAll(Dummy.class);
   }

}
