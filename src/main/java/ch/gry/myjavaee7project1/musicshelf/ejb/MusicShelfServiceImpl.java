/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.util.Collection;
import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import ch.gry.myjavaee7project1.musicshelf.model.Title;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author yvesgross
 */
public class MusicShelfServiceImpl{

//    private static final Logger logger = Logger.getLogger(MusicShelfServiceImpl.class.getName());
//
//    Map<Long, Album> albums = new HashMap<>();
//    Map<Long, Artist> artists = new HashMap<>();
//
//    public MusicShelfServiceImpl() {
//        try {
//            // provide some initial artists
//            Artist artist = crea
//            
//            // provide some initial albums
//            Album album = createAlbum(new Album("Album 1", null, null));
//            createTitle(album.getId(), new Title("Title A-1", "Text A-1"));
//            createTitle(album.getId(), new Title("Title A-2", "Text A-2"));
//            
//            album = createAlbum(new Album("Initial Album B", "Karl Ericson", "0389482"));
//            createTitle(album.getId(), new Title("Title B-1", "Text Bs-1"));
//            createTitle(album.getId(), new Title("Title B-2", "Text B-2"));
//            
//            album = createAlbum(new Album("Initial Album C", "Mary Joe Lynn", "2384798"));
//            createTitle(album.getId(), new Title("Title C-1", "Text C-1"));
//            createTitle(album.getId(), new Title("Title C-2", "Text C-2"));
//            
//        } catch (ResourceNotFoundException ex) {
//            logger.severe(ex.getLocalizedMessage());
//        }
//    }
//    
//    @Override
//    public Artist createArtist(Artist newArtist) {
//        long newId = artists.size() + 600;
//        newArtist.setId(newId);
//        artists.put(newId, newArtist);
//        return newArtist;
//   }
//
//    @Override
//    public Collection<Artist> getArtists() {
//        return artists.values();
//    }
//
//    @Override
//    public Artist getArtist(Long id) throws ResourceNotFoundException {
//        if(!artists.containsKey(id)) {
//            throw new ResourceNotFoundException(String.format("Unknown artist id %s!", id));
//        }
//        return artists.get(id);        
//    }
//
//    @Override
//    public void updateArtist(Artist artist) throws ResourceNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void deleteArtist(Long id) throws ResourceNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int countArtists() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    /**
//     *
//     * @param newAlbum
//     * @return
//     */
//    @Override
//    public Album createAlbum(final Album newAlbum) {
//        long newId = albums.size() + 100;
//        newAlbum.setId(newId);
//        albums.put(newId, newAlbum);
//        return newAlbum;
//    }
//    
//    @Override
//    public Collection<Album> getAlbums() {
//        return albums.values();
//    }
//
//    @Override
//    public Album getAlbum(final Long id) throws ResourceNotFoundException {
//        if(!albums.containsKey(id)) {
//            throw new ResourceNotFoundException(String.format("Unknown album id %s!", id));
//        }
//        return albums.get(id);
//    }
//    
//    @Override
//    public void updateAlbum(final Album album)throws ResourceNotFoundException{
//        if(!albums.containsKey(album.getId())) {
//            throw new ResourceNotFoundException(String.format("Unknown album with id %s! Cannot be updated!", album.getId()));
//        }
//        else {
//            albums.put(album.getId(), album);
//        }
//    }
//    
//    @Override
//    public void deleteAlbum(final Long id) throws ResourceNotFoundException{
//        if(!albums.containsKey(id)){
//            throw new ResourceNotFoundException(String.format("Unknown album id %s! Cannot be deleted!", id));
//        }
//        else {
//            albums.remove(id);
//        }
//    }
//    
//    @Override
//    public int countAlbums(){
//        return albums.size();
//    }
//
//    @Override
//    public Title createTitle(final Long albumId, final Title newTitle) throws ResourceNotFoundException{
//        Album album = getAlbum(albumId);
//
//        long newId = album.getTitles().size() + 400;
//        newTitle.setId(newId);
//        album.getTitles().put(newId, newTitle);
//        
//        return newTitle;
//    }
//
//    @Override
//    public Title getTitle(final Long albumId, final Long titleId) throws ResourceNotFoundException{
//        Album album = getAlbum(albumId);        
//        if(album.getTitles().containsKey(titleId)) {
//            return album.getTitles().get(titleId);
//        }
//        else {
//            throw new ResourceNotFoundException(String.format("Unknown title id %s!", titleId));            
//        }
//    }
//
//    @Override
//    public Collection<Title> getTitles(final Long albumId) throws ResourceNotFoundException {
//        Collection<Title> titles = getAlbum(albumId).getTitles().values();
//        return titles;
//    }
//
//    @Override
//    public void updateTitle(final Long albumId, final Title title) throws ResourceNotFoundException {
//        Album album = getAlbum(albumId);
//        Long titleId = title.getId();
//        if(album.getTitles().containsKey(titleId)) {
//            album.getTitles().put(titleId, title);
//        }
//        else {
//            throw new ResourceNotFoundException(String.format("Unknown title id %s! Cannot be updated.", titleId));            
//        }        
//    }
//
//    @Override
//    public void deleteTitle(final Long albumId, final Long titleId) throws ResourceNotFoundException {
//        Album album = getAlbum(albumId);
//        if(album.getTitles().containsKey(titleId)) {
//            album.getTitles().remove(titleId);
//        }
//        else {
//            throw new ResourceNotFoundException(String.format("Unknown title id %s! Cannot be deleted.", titleId));            
//        }        
//     
//    }
//
//    @Override
//    public int countTitles(Long albumId) throws ResourceNotFoundException {
//        Album album = getAlbum(albumId);
//        return album.getTitles().size();
//    }

}
