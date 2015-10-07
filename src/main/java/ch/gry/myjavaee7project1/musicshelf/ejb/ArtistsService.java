/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author yvesgross
 */
@Singleton
public class ArtistsService extends AbstractCrudService<Artist>{

    private static final long START_ID = 100;

    public ArtistsService() {
        super(START_ID);
    }
    
    @PostConstruct
    public void initData () {
        create(new Artist("Artist A", "USA"));
        create(new Artist("Artist B", "UK"));        
    }
}
