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

    private Artist artist;

    private LocalDate appearance;

    private Map<Long, Track> tracks = new HashMap<>();

    public Album() {
    }

    public Album(String Title, Artist artist, LocalDate appearance) {
        this.Title = Title;
        this.artist = artist;
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
     * Get the artist
     *
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Set the artist
     *
     * @param artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.Title);
        hash = 67 * hash + Objects.hashCode(this.artist);
        hash = 67 * hash + Objects.hashCode(this.appearance);
        hash = 67 * hash + Objects.hashCode(this.tracks);
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
        if (!Objects.equals(this.artist, other.artist)) {
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
