package ch.gry.myjavaee7project1.rest.resource.albums;

import ch.gry.myjavaee7project1.musicshelf.ejb.AlbumsService;
import ch.gry.myjavaee7project1.musicshelf.model.Album;
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
@Path("albums")
public class Albums {
    
    private static final Logger logger = Logger.getLogger(Albums.class.getName());

//    @Inject // how to properly use @Inject?
    @EJB
    AlbumsService service;
    
    /**
     *
     * @param newAlbum
     * @return
     */
    @POST
    public Album createAlbum(final Album newAlbum) {
        logger.info("REST-POST: createAlbum()");
        return service.create(newAlbum);      
    }
    
    /**
     *
     * @return
     */
    @GET
    public GenericEntity<Collection<Album>> getAlbums() {
        logger.info("REST-GET: getAlbums()");
        return new GenericEntity<Collection<Album>>(service.getAll()){};
    }

    /**
     *
     * @param albumId
     * @return
     */
    @GET
    @Path("{albumId}")
    public Album getAlbum(@PathParam("albumId") final Long albumId) {
        logger.info(String.format("REST-GET: getAlbum(%d)", albumId));
        try {
            return service.get(albumId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param album
     */
    @PUT
    @Path("{albumId}")
    public void updateAlbum(@PathParam("albumId") final Long albumId, final Album album) {
        logger.info(String.format("REST-PUT: updateAlbum(%d)", albumId));
        try {

            // Make sure to update the object with the id of the resource.
            // I.e. the id of the given update data is irrelevant.
            album.setId(albumId);

            service.update(album);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     */
    @DELETE
    @Path("{albumId}")
    public void deleteAlbum(@PathParam("albumId") final Long albumId) {
        logger.info(String.format("REST-DELETE: deleteAlbum(%s)", albumId));
        try {
            service.delete(albumId);
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
    public JsonObject countAlbums() {
        logger.info("REST-GET: countAlbums()");
        return Json.createObjectBuilder().
                add("numOfAlbums", service.count()).
                build();
    }

    
}
