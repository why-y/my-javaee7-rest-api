/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.model;

import java.io.Serializable;

/**
 *
 * @author yvesgross
 */
public abstract class Model implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

}