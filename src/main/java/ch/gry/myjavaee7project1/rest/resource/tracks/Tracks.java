package ch.gry.myjavaee7project1.rest.resource.tracks;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

import ch.gry.myjavaee7project1.musicshelf.ejb.AlbumsService;
import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Track;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("")
public class Tracks {
    
    private static final Logger logger = Logger.getLogger(Tracks.class.getName());

//    @EJB
    @Inject // how to properly use @Inject?
    AlbumsService albumService;
    
    /**
     *
     * @param albumId
     * @param newTitle
     * @return
     */
    @POST
    public Track createTitle(@PathParam("albumId") final Long albumId, final Track newTitle) {
        logger.info("REST-POST: createTitle()");
        try {
            Map<Long, Track> titles = albumService.get(albumId).getTracks();
            long newId = titles.size()+1;
            newTitle.setId(newId);
            titles.put(newId, newTitle);
            return newTitle;
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(Tracks.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    public GenericEntity<Collection<Track>> getTitles(@PathParam("albumId") final Long albumId) {
        logger.info("REST-GET: getTitles()");
        try {
            return new GenericEntity<Collection<Track>>(albumService.get(albumId).getTracks().values()){};
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param titleId
     * @return
     */
    @GET
    @Path("{titleId}")
    public Track getTitle(
            @PathParam("albumId") final Long albumId, 
            @PathParam("titleId") final Long titleId) {
        
        logger.info(String.format("REST-GET: getTitle(%d)", titleId));
        try {
            return albumService.get(albumId).getTracks().get(titleId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param titleId
     * @param title
     */
    @PUT
    @Path("{titleId}")
    public void updateTitle(
            @PathParam("albumId") final Long albumId,
            @PathParam("titleId") final Long titleId, 
            final Track title) {
        logger.info(String.format("REST-PUT: updateTitle(%d)", titleId));
        try {
            Album album = albumService.get(albumId);
            Track existingTitle = album.getTracks().get(titleId);
            if(existingTitle==null){
                throw new NotFoundException(String.format("No title found in album: %d with titleId: %d", albumId, titleId));
            }
            album.getTracks().put(titleId, title);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param titleId
     */
    @DELETE
    @Path("{titleId}")
    public void deleteTitle(
            @PathParam("albumId") final Long albumId,
            @PathParam("titleId") final Long titleId) {
        logger.info(String.format("REST-DELETE: deleteTitle(%s)", titleId));
        try {
            Album album = albumService.get(albumId);
            Track existingTitle = album.getTracks().get(titleId);
            if(existingTitle==null){
                throw new NotFoundException(String.format("No title found in album: %d with titleId: %d", albumId, titleId));
            }
            album.getTracks().remove(titleId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @return
     */
    @GET
    @Path("quantity")
    public JsonObject countTracks(@PathParam("albumId") final Long albumId) {
        logger.info("REST-GET: countTracks()");
        try {
            Album album = albumService.get(albumId);
            return Json.createObjectBuilder().
                    add("numOfTracks", album.getTracks().size()).
                    build();
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

}
