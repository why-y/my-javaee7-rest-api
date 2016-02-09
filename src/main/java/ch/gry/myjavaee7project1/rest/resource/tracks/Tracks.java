package ch.gry.myjavaee7project1.rest.resource.tracks;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
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

    @Inject
    AlbumsService albumService;
    
    /**
     *
     * @param albumId
     * @param newTrack
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Track createTrack(@PathParam("albumId") final Long albumId, final Track newTrack) {
        logger.info("REST-POST: createTrack()");
        try {
            Map<Long, Track> tracks = albumService.get(albumId).getTracks();
            long newId = tracks.size()+1;
            newTrack.setId(newId);
            tracks.put(newId, newTrack);
            return newTrack;
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(Tracks.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Track> getTracks(@PathParam("albumId") final Long albumId) {
        logger.info("REST-GET: getTracks()");
        try {
            return albumService.get(albumId).getTracks().values();
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param trackId
     * @return
     */
    @GET
    @Path("{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(
            @PathParam("albumId") final Long albumId, 
            @PathParam("trackId") final Long trackId) {
        
        logger.info(String.format("REST-GET: getTrack(%d)", trackId));
        try {
            return albumService.get(albumId).getTracks().get(trackId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param trackId
     * @param track
     */
    @PUT
    @Path("{trackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTrack(
            @PathParam("albumId") final Long albumId,
            @PathParam("trackId") final Long trackId, 
            final Track track) {
        logger.info(String.format("REST-PUT: updateTrack(%d)", trackId));
        try {
            Album album = albumService.get(albumId);
            Track existingTrack = album.getTracks().get(trackId);
            if(existingTrack==null){
                throw new NotFoundException(String.format("No track found in album: %d with trackId: %d", albumId, trackId));
            }
            album.getTracks().put(trackId, track);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param trackId
     */
    @DELETE
    @Path("{trackId}")
    public void deleteTrack(
            @PathParam("albumId") final Long albumId,
            @PathParam("trackId") final Long trackId) {
        logger.info(String.format("REST-DELETE: deleteTrack(%s)", trackId));
        try {
            Album album = albumService.get(albumId);
            Track existingTrack = album.getTracks().get(trackId);
            if(existingTrack==null){
                throw new NotFoundException(String.format("No track found in album: %d with trackId: %d", albumId, trackId));
            }
            album.getTracks().remove(trackId);
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
    @Produces(MediaType.APPLICATION_JSON)
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
