/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.model.Artist;

/**
 *
 * @author yvesgross
 */
@RequestScoped
public class ArtistsService extends AbstractCrudService<Artist>{

    public ArtistsService() {
    }
    
}
