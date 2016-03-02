/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.boundary;

import ch.gry.myjavaee7project1.musicshelf.model.Model;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;

/**
 *
 * @author yvesgross
 * @param <T>
 */
public interface CrudService<T extends Model> {
    
    /**
     *
     * @param model
     * @return
     */
    public T create(final T model);
    
    /**
     *
     * @return
     */
    public Collection<T> getAll(Class<T> clazz);

    /**
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public T get(final Long id) throws ResourceNotFoundException;
    
    /**
     *
     * @param model
     * @throws ResourceNotFoundException
     */
    public void update(final T model) throws ResourceNotFoundException;
    
    /**
     *
     * @param id
     * @throws ResourceNotFoundException
     */
    public void delete(final Long id) throws ResourceNotFoundException;
    
    /**
     *
     * @return
     */
    public int count();
    
}
