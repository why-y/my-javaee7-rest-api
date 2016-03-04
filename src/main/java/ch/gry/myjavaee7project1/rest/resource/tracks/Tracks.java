package ch.gry.myjavaee7project1.rest.resource.tracks;

import java.util.List;
import java.util.Optional;
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
        	return albumService.addTrack(albumId, newTrack);
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(Tracks.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getTracks(@PathParam("albumId") final Long albumId) {
        logger.info("REST-GET: getTracks()");
        try {
            return albumService.get(albumId, Album.class).getTracks();
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    /**
     *
     * @param albumId
     * @param trackId
     * @return
     * @throws ResourceNotFoundException 
     */
    @GET
    @Path("{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(
            @PathParam("albumId") final Long albumId, 
            @PathParam("trackId") final Long trackId) throws ResourceNotFoundException {
        
        logger.info(String.format("REST-GET: getTrack(%d)", trackId));

        Optional<Track> optionalTrack = albumService.get(albumId).getTracks()
        		.stream()
        		.filter(t -> t.getId().equals(trackId))
        		.findFirst();
        if (!optionalTrack.isPresent()){
        	throw new NotFoundException("No Track found with ID : " + trackId);
        }
        return optionalTrack.get();
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
    	// TODO:
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
    	// TODO:
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
            Album album = albumService.get(albumId, Album.class);
            return Json.createObjectBuilder().
                    add("numOfTracks", album.getTracks().size()).
                    build();
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

}
