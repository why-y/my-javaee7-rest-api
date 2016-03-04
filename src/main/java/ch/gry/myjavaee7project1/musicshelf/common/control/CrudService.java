/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.common.control;

import ch.gry.myjavaee7project1.musicshelf.common.boundary.ResourceNotFoundException;
import ch.gry.myjavaee7project1.musicshelf.common.entity.Model;

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
     * @param entityClass
     * @return
     */
    public Collection<T> getAll(final Class<T> entityClass);

    /**
     *
     * @param id
     * @param entityClass
     * @return
     * @throws ResourceNotFoundException
     */
    public T get(final Long id, final Class<T> entityClass) throws ResourceNotFoundException;
    
    /**
     *
     * @param model
     * @throws ResourceNotFoundException
     */
    public void update(final T model) throws ResourceNotFoundException;
    
    /**
     *
     * @param id
     * @param entityClass
     * @throws ResourceNotFoundException
     */
    public void delete(final Long id, final Class<T> entityClass) throws ResourceNotFoundException;
    
    /**
     *
     * @return
     */
    public int count(final Class<T> entityClass);
    
}
