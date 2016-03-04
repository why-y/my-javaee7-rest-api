/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.album.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.album.entity.Album;
import ch.gry.myjavaee7project1.musicshelf.common.control.AbstractCrudService;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.track.entity.Track;

/**
 *
 * @author yvesgross
 */
@RequestScoped
public class AlbumsService extends AbstractCrudService<Album> {

//    private static final Logger logger = Logger.getLogger(AlbumsService.class.getName());

	/**
	 * Get all albums
	 * @return all albums
	 */
	public Collection<Album> getAll() {
		return super.getAll(Album.class);
	}

    /**
     * Gets a single Album by its ID
     * @param id The ID of the requested Album
     * @return The Album with the given ID
     */
    public Album get(final Long id) {
    	return super.get(id, Album.class);
    }
    /**
     * Deletes the Album by its ID
     * @param id The ID of the Album to be deleted
     * @throws EntityNotFoundException 
     */
    public void delete(final Long id) throws EntityNotFoundException {
    	super.delete(id, Album.class);
    }
    
    /**
     * Returns the total number of Album
     * @return the total number of Album
     */
    public int count() {
    	return super.count(Album.class);
    }    
    
    
    
    
    
    
	public Collection<Album> getAlbumsOf(final Long artistId) {
    	// TODO:
		return null;
    }
    
    public Track addTrack(final Long albumId, final Track newTrack) throws EntityNotFoundException {
    	Album album = super.get(albumId, Album.class);
    	if(album==null) {
    		throw new EntityNotFoundException(String.format("Coulnd not find the Album with the id: %d! Thus, cannot add tracks to it.", albumId));
    	}
    	album.getTracks().add(newTrack);
    	super.update(album);
        return newTrack;
    }

}
