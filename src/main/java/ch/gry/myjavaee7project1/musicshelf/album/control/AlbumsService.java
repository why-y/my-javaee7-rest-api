/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.album.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.album.entity.Album;
import ch.gry.myjavaee7project1.musicshelf.common.boundary.ResourceNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.common.control.AbstractCrudService;
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
     * @throws ResourceNotFoundException if no Album with the given ID has been found
     */
    public Album get(final Long id) throws ResourceNotFoundException {
    	return super.get(id, Album.class);
    }
    /**
     * Deletes the Album by its ID
     * @param id The ID of the Album to be deleted
     * @throws ResourceNotFoundException if no Album with the given ID has been found
     */
    public void delete(final Long id) throws ResourceNotFoundException {
    	super.delete(id, Album.class);
    }
    
    /**
     * Returns the total number of Album
     * @return the total number of Album
     */
    public int count() {
    	return super.count(Album.class);
    }    
    
    
    
    
    
    
	public Collection<Album> getAlbumsOf(final Long artistId) throws ResourceNotFoundException{
    	// TODO:
		return null;
    }
    
    public Track addTrack(final Long albumId, final Track newTrack) throws ResourceNotFoundException{
    	Album album = super.get(albumId, Album.class);
    	album.getTracks().add(newTrack);
    	super.update(album);
        return newTrack;
    }

}
