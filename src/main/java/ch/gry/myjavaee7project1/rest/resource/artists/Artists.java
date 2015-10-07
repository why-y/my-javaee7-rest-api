package ch.gry.myjavaee7project1.rest.resource.artists;

import ch.gry.myjavaee7project1.musicshelf.ejb.ArtistsService;
import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("artists")
public class Artists {
    
    private static final Logger logger = Logger.getLogger(Artists.class.getName());

//    @Inject // how to properly use @Inject?
    @EJB
    ArtistsService service;
    
    /**
     *
     * @param newArtist
     * @return
     */
    @POST
    public Artist createArtist(final Artist newArtist) {
        logger.info("REST-POST: createArtist()");
        return service.create(newArtist);      
    }
    
    /**
     *
     * @return
     */
    @GET
    public GenericEntity<Collection<Artist>> getArtists() {
        logger.info("REST-GET: getArtists()");
        return new GenericEntity<Collection<Artist>>(service.getAll()){};
    }

    /**
     *
     * @param artistId
     * @return
     */
    @GET
    @Path("{artistId}")
    public Artist getArtist(@PathParam("artistId") final Long artistId) {
        logger.info(String.format("REST-GET: getArtist(%d)", artistId));
        try {
            return service.get(artistId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param artistId
     * @param artist
     */
    @PUT
    @Path("{artistId}")
    public void updateArtist(@PathParam("artistId") final Long artistId, final Artist artist) {
        logger.info(String.format("REST-PUT: updateArtist(%d)", artistId));
        try {

            // Make sure to update the object with the id of the resource.
            // I.e. the id of the given update data is irrelevant.
            artist.setId(artistId);

            service.update(artist);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param artistId
     */
    @DELETE
    @Path("{artistId}")
    public void deleteArtist(@PathParam("artistId") final Long artistId) {
        logger.info(String.format("REST-DELETE: deleteArtist(%s)", artistId));
        try {
            service.delete(artistId);
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
    public JsonObject countArtists() {
        logger.info("REST-GET: countArtists()");
        return Json.createObjectBuilder().
                add("numOfArtists", service.count()).
                build();
    }

    
}