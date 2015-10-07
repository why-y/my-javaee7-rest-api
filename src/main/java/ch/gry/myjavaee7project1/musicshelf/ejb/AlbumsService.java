/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import java.time.LocalDate;
import java.time.Month;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author yvesgross
 */
@Singleton
public class AlbumsService extends AbstractCrudService<Album> {

    private static final int START_ID = 200;
    
    public AlbumsService() {
        super(START_ID);
    }
    
    @PostConstruct
    public void initData () {
        create(new Album("Album A", null, LocalDate.of(2008, Month.MARCH, 12)));
        create(new Album("Album B", null, LocalDate.of(1998, Month.JUNE, 23)));
    }
    
}
