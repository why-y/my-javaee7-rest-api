/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Track;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 */
@RequestScoped
public class AlbumsService extends AbstractCrudService<Album> {

//    private static final Logger logger = Logger.getLogger(AlbumsService.class.getName());

    public AlbumsService() {
    }
        
	public Collection<Album> getAlbumsOf(final Long artistId) throws ResourceNotFoundException{
    	// TODO:
		return null;
    }
    
    public Track addTrack(final Long albumId, final Track newTrack) throws ResourceNotFoundException{
    	// TODO:
        return null;
    }
}
