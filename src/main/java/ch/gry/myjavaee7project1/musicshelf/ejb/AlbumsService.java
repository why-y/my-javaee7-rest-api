/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Track;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 */
@Stateless
public class AlbumsService extends AbstractCrudService<Album> {

    private static final Logger logger = Logger.getLogger(AlbumsService.class.getName());

    private static final long START_ID = 200;
    
    @Inject
    private ArtistsService artistsService;
    
    public AlbumsService() {
        super(START_ID);
    }
    
    /**
     *
     */
    @PostConstruct
    public void initData() {
    	
    	try {
    		
            Album album = create(new Album("Album A", 
                    artistsService.get(100l),
                    LocalDate.of(2008, Month.MARCH, 12)));
            addTrack(album.getId(), new Track("Track A-1", Duration.ofMinutes(3).plusSeconds(45), 1));
            addTrack(album.getId(), new Track("Track A-2", Duration.ofMinutes(4).plusSeconds(35), 2));
            addTrack(album.getId(), new Track("Track A-3", Duration.ofMinutes(3).plusSeconds(25), 3));
            addTrack(album.getId(), new Track("Track A-4", Duration.ofMinutes(5).plusSeconds(15), 4));
            addTrack(album.getId(), new Track("Track A-5", Duration.ofMinutes(2).plusSeconds(55), 5));
            
            album = create(new Album("Album B", 
                    artistsService.get(101l), 
                    LocalDate.of(1998, Month.JUNE, 23)));
            addTrack(album.getId(), new Track("Track B-1", Duration.ofMinutes(3).plusSeconds(49), 1));
            addTrack(album.getId(), new Track("Track B-2", Duration.ofMinutes(4).plusSeconds(39), 2));
            addTrack(album.getId(), new Track("Track B-3", Duration.ofMinutes(3).plusSeconds(29), 3));
            addTrack(album.getId(), new Track("Track B-4", Duration.ofMinutes(5).plusSeconds(19), 4));
            addTrack(album.getId(), new Track("Track B-5", Duration.ofMinutes(2).plusSeconds(59), 5));
            
        } catch (ResourceNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
	public Collection<Album> getAlbumsOf(final Long artistId) throws ResourceNotFoundException{
        return this.store.values().stream().filter(album -> album.getArtist().getId().equals(artistId)).collect(Collectors.toList());
    }
    
    public Track addTrack(final Long albumId, final Track newTrack) throws ResourceNotFoundException{
        Album album = get(albumId);
        Long nextTrackId = nextId(album.getTracks(), 5000l);
        newTrack.setId(nextTrackId);
        album.getTracks().put(nextTrackId, newTrack);
        return newTrack;        
    }
}
