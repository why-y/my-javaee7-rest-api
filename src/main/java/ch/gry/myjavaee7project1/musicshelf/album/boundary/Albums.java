package ch.gry.myjavaee7project1.musicshelf.album.boundary;

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

import ch.gry.myjavaee7project1.musicshelf.album.control.AlbumsService;
import ch.gry.myjavaee7project1.musicshelf.album.entity.Album;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotPersistedException;
import ch.gry.myjavaee7project1.musicshelf.track.boundary.Tracks;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("albums")
public class Albums {
    
    @Inject
    private Logger logger;

    @Inject
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
        try {
			return service.create(newAlbum);
		} catch (EntityNotPersistedException e) {
			throw new BadRequestException(String.format("Could not create the album: %s", newAlbum), e);
		}      
    }
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Album> getAlbums() {
        logger.info("REST-GET: getAlbums()");
        return service.getAll();
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
        return service.get(albumId);
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
        
        // Make sure to update the object with the id of the resource.
        // I.e. the id of the given update data is irrelevant.
        album.setId(albumId);
        try {
            service.update(album);
        } catch (EntityNotFoundException e) {
			throw new NotFoundException(String.format("Could not find the album %s to update!", album), e);
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
        } catch (EntityNotFoundException e) {
			throw new NotFoundException(String.format("Could not find the album with the ID: %d to delete!", albumId), e);
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
                add("numOfAlbums", service.count()).
                build();
    }

    //////////// SUB RESOURCES /////////////////////////////////////////////////
    @Path("{albumId}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Tracks getTracksSubResource() {
        return tracksSubResource;
    }
}
