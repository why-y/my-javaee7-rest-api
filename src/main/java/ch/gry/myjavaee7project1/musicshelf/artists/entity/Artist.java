/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.artists.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import ch.gry.myjavaee7project1.musicshelf.common.entity.Model;

/**
 *
 * @author yvesgross
 */
@Entity
public class Artist extends Model{
    
	private static final long serialVersionUID = 1L;
	
	@Column
	private String name;
    
	@Column
	private String origin;

    public Artist() {
    	setId(0l);
    }
    
    public Artist(String Name, String origin) {
        this.name = Name;
        this.origin = origin;
        setId(0l);
    }
    
    /**
     * Get the value of origin
     *
     * @return the value of origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the value of origin
     *
     * @param origin new value of origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name) {
        this.name = Name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.origin);
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
        final Artist other = (Artist) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        return true;
    }

}
