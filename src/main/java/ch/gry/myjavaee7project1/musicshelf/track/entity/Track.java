/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.track.entity;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import ch.gry.myjavaee7project1.musicshelf.common.entity.Model;

/**
 *
 * @author yvesgross
 */
@Entity
public class Track extends Model {
    
	private static final long serialVersionUID = 1L;

	@Column
	private String Title;
    
	@Column
    private Duration duration;

	@Column
    private Integer trackNo;


    public Track() {
    }

    public Track(String Title, Duration duration, Integer trackNo) {
        this.Title = Title;
        this.duration = duration;
        this.trackNo = trackNo;
    }

    /**
     * Get the value of trackNo
     *
     * @return the value of trackNo
     */
    public Integer getTrackNo() {
        return trackNo;
    }

    /**
     * Set the value of trackNo
     *
     * @param trackNo new value of trackNo
     */
    public void setTrackNo(Integer trackNo) {
        this.trackNo = trackNo;
    }

    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Set the value of duration
     *
     * @param duration new value of duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.Title);
        hash = 23 * hash + Objects.hashCode(this.duration);
        hash = 23 * hash + Objects.hashCode(this.trackNo);
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
        final Track other = (Track) obj;
        if (!Objects.equals(this.Title, other.Title)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        if (!Objects.equals(this.trackNo, other.trackNo)) {
            return false;
        }
        return true;
    }

    
}
