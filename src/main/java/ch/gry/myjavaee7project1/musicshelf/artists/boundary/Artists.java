package ch.gry.myjavaee7project1.musicshelf.artists.boundary;

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
import ch.gry.myjavaee7project1.musicshelf.artists.control.ArtistsService;
import ch.gry.myjavaee7project1.musicshelf.artists.entity.Artist;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotPersistedException;

/**
 *
 * @author yvesgross
 */
@Stateless
@Path("artists")
public class Artists {
    
    @Inject
    private Logger logger;

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
        try {
			return artistsService.create(newArtist);
		} catch (EntityNotPersistedException e) {
			throw new BadRequestException(String.format("Could not create the artist: %s", newArtist), e);
		}      
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
        Artist result = artistsService.get(artistId);
        if(result == null) {
        	throw new NotFoundException(String.format("No Artist resource found with id:%d", artistId));
        }
        return artistsService.get(artistId);
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("{artistId}/albums")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Album> getAlbums(@PathParam("artistId") final Long artistId) {
    	logger.info("REST-GET: getAlbums()");
    	return albumsService.getAlbumsOf(artistId);
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
        // Make sure to update the object with the id of the resource.
        // I.e. the id of the given update data is irrelevant.
        artist.setId(artistId);
        
        try {
			artistsService.update(artist);
		} catch (EntityNotFoundException e) {
			throw new NotFoundException(String.format("Could not find the artist %s to update!", artist), e);
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
		} catch (EntityNotFoundException e) {
			throw new NotFoundException(String.format("Could not find the artist with the ID: %d to delete!", artistId), e);
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
