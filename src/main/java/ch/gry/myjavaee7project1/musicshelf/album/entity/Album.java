/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.album.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ch.gry.myjavaee7project1.musicshelf.common.entity.Model;
import ch.gry.myjavaee7project1.musicshelf.track.entity.Track;

/**
 *
 * @author yvesgross
 */
@Entity
public class Album extends Model{
    
	private static final long serialVersionUID = 1L;

	@Column	
	private String Title;

	@Column
    private String artist;

	@Column
    private LocalDate appearance;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Track> tracks = new ArrayList<>();

    public Album() {
    }

    public Album(String Title, String artist, LocalDate appearance) {
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
    public String getArtist() {
        return artist;
    }

    /**
     * Set the artist
     *
     * @param artist
     */
    public void setArtist(String artist) {
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
    public List<Track> getTracks() {
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

	@Override
	public String toString() {
		return "Album [Title=" + Title + ", artist=" + artist + ", appearance=" + appearance + ", tracks=" + tracks
				+ "]";
	}
    
    
}
