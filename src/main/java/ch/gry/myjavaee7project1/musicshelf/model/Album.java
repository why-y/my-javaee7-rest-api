/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author yvesgross
 */
public class Album extends Model{
    
    private String Title;

    private Long artistId;

    private LocalDate appearance;

    private Map<Long, Track> tracks = new HashMap<>();

    public Album() {
    }

    public Album(String Title, Long artistId, LocalDate appearance) {
        this.Title = Title;
        this.artistId = artistId;
        this.appearance = appearance;
    }
    
    /**
     * Get the value of appearance
     *
     * @return the value of appearance
     */
    public LocalDate getAppearance() {
        return appearance;
    }

    /**
     * Set the value of appearance
     *
     * @param appearance new value of appearance
     */
    public void setAppearance(LocalDate appearance) {
        this.appearance = appearance;
    }

    /**
     * Get the Id of the artist
     *
     * @return the Id of the artist
     */
    public Long getArtistId() {
        return artistId;
    }

    /**
     * Set the Id of the artist
     *
     * @param artistId new Id of the artist
     */
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    /**
     * Get the value of Title
     *
     * @return the value of Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Set the value of Title
     *
     * @param Title new value of Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     */
    public Map<Long, Track> getTracks() {
        return tracks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.Title);
        hash = 53 * hash + Objects.hashCode(this.artistId);
        hash = 53 * hash + Objects.hashCode(this.appearance);
        hash = 53 * hash + Objects.hashCode(this.tracks);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.Title, other.Title)) {
            return false;
        }
        if (!Objects.equals(this.artistId, other.artistId)) {
            return false;
        }
        if (!Objects.equals(this.appearance, other.appearance)) {
            return false;
        }
        if (!Objects.equals(this.tracks, other.tracks)) {
            return false;
        }
        return true;
    }

    
}
