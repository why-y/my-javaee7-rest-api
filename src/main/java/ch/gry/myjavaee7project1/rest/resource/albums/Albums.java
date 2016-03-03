package ch.gry.myjavaee7project1.rest.resource.albums;

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

import ch.gry.myjavaee7project1.musicshelf.ejb.AlbumsService;
import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.rest.resource.tracks.Tracks;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("albums")
public class Albums {
    
    private static final Logger logger = Logger.getLogger(Albums.class.getName());

    @Inject // how to properly use @Inject?
    AlbumsService service;
    
    @Inject
    Tracks tracksSubResource;
    
    /**
     *
     * @param newAlbum
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Album createAlbum(final Album newAlbum) {
        logger.info("REST-POST: createAlbum()");
        return service.create(newAlbum);      
    }
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Album> getAlbums() {
        logger.info("REST-GET: getAlbums()");
        return service.getAll(Album.class);
    }

    /**
     *
     * @param albumId
     * @return
     */
    @GET
    @Path("{albumId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Album getAlbum(@PathParam("albumId") final Long albumId) {
        logger.info(String.format("REST-GET: getAlbum(%d)", albumId));
        try {
            return service.get(albumId, Album.class);
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
    @Consumes(MediaType.APPLICATION_JSON)
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
            service.delete(albumId, Album.class);
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
    public JsonObject countAlbums() {
        logger.info("REST-GET: countAlbums()");
        return Json.createObjectBuilder().
                add("numOfAlbums", service.count(Album.class)).
                build();
    }

    //////////// SUB RESOURCES /////////////////////////////////////////////////
    @Path("{albumId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Tracks getTracksSubResource() {
        return tracksSubResource;
    }
}
