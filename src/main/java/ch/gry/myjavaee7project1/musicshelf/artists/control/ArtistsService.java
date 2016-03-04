/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.artists.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.artists.entity.Artist;
import ch.gry.myjavaee7project1.musicshelf.common.control.AbstractCrudService;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;

/**
 *
 * @author yvesgross
 */
@RequestScoped
public class ArtistsService extends AbstractCrudService<Artist>{

	/**
	 * Get all artists
	 * @return all artists
	 */
    public Collection<Artist> getAll() {
    	return super.getAll(Artist.class);
    }
    
    /**
     * Gets a single Artist by its ID
     * @param id The ID of the requested Artist
     * @return The Artist with the given ID
     */
    public Artist get(final Long id) {
    	return super.get(id, Artist.class);
    }
    
    /**
     * Deletes the Artist by its ID
     * @param id The ID of the Artist to be deleted
     * @throws EntityNotFoundException if the artist with the given ID cannot be found
     */
    public void delete(final Long id) throws EntityNotFoundException {
    	super.delete(id, Artist.class);
    }
    
    /**
     * Returns the total number of Artists
     * @return the total number of Artists
     */
    public int count() {
    	return super.count(Artist.class);
    }
}
