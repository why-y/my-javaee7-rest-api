/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.dummy.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import ch.gry.myjavaee7project1.musicshelf.common.control.AbstractCrudService;
import ch.gry.myjavaee7project1.musicshelf.common.control.EntityNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.dummy.entity.Dummy;

/**
 *
 * @author yvesgross
 */
@RequestScoped
public class DummiesService extends AbstractCrudService<Dummy>{
	
	public Collection<Dummy> getAll() {
		return super.getAll(Dummy.class);
	}
	
    public Dummy get(final Long id) {
    	return super.get(id, Dummy.class);
    }
    
    public void delete(final Long id) throws EntityNotFoundException {
    	super.delete(id, Dummy.class);
    }
    
    public int count() {
    	return super.count(Dummy.class);
    }

}
