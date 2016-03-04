package ch.gry.myjavaee7project1.rest.resource.artists;

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
import ch.gry.myjavaee7project1.musicshelf.ejb.ArtistsService;
import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("artists")
public class Artists {
    
    private static final Logger logger = Logger.getLogger(Artists.class.getName());

    @Inject
    ArtistsService artistsService;
    
    @Inject
    AlbumsService albumsService;
    
    /**
     *
     * @param newArtist
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Artist createArtist(final Artist newArtist) {
        logger.info("REST-POST: createArtist()");
        return artistsService.create(newArtist);      
    }
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Artist> getArtists() {
        logger.info("REST-GET: getArtists()");
        return artistsService.getAll();
    }

    /**
     *
     * @param artistId
     * @return
     */
    @GET
    @Path("{artistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Artist getArtist(@PathParam("artistId") final Long artistId) {
        logger.info(String.format("REST-GET: getArtist(%d)", artistId));
        try {
            return artistsService.get(artistId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("{artistId}/albums")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Album> getDiscography(@PathParam("artistId") final Long artistId) {
        try {
            logger.info("REST-GET: getDiscography()");
            return albumsService.getAlbumsOf(artistId);
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
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateArtist(@PathParam("artistId") final Long artistId, final Artist artist) {
        logger.info(String.format("REST-PUT: updateArtist(%d)", artistId));
        try {

            // Make sure to update the object with the id of the resource.
            // I.e. the id of the given update data is irrelevant.
            artist.setId(artistId);

            artistsService.update(artist);
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
            artistsService.delete(artistId);
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
    public JsonObject countArtists() {
        logger.info("REST-GET: countArtists()");
        return Json.createObjectBuilder().
                add("numOfArtists", artistsService.count()).
                build();
    }

    
}
