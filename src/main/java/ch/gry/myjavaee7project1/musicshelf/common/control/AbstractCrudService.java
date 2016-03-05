/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.common.control;

import java.util.Collection;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.gry.myjavaee7project1.musicshelf.common.entity.Model;

/**
 *
 * @author yvesgross
 * @param <T>
 */
public class AbstractCrudService<T extends Model> {

    @Inject
    private Logger logger;
	
    @Inject
    EntityManager em;
    
    /**
     * Persists the given model object as a new entity. It's id attribute will be ignored.
     * @param newModel all data of the given model will be saved as a new entity
     * @return the new entity with it's new ID
     * @throws EntityNotPersistedException if the given model object could not be persisted.
     */
    public T create(final T newModel) throws EntityNotPersistedException{
    	logger.info("About to persist the new Model : " + newModel);
    	// the id of the model must be null, to make sure a new entity will be created
    	newModel.setId(null);
    	try{
    		em.persist(newModel);
    		em.flush();    		
    	} catch (Throwable e) {
    		throw new EntityNotPersistedException(String.format("Could not create the new entity: %s", newModel), e);
    	}
    	logger.info("Just persisted the new Model. Assigned ID: " + newModel.getId());
        return newModel;
    }

    /**
     * Returns all entities of the given type
     * @return all entities of the given type
     */
    public Collection<T> getAll(final Class<T> entityClass) {
    	return em.createQuery("FROM " + entityClass.getName(), entityClass)
    			.getResultList();
    }

    /**
     * Returns the entity of the given type and ID, or null if no entity could be found
     * @param id The ID of the requested entity
     * @param entityClass The class type of the requested entity
     * @return The requested entity or null if it could not be determined for some reason.
     */
    public T get(final Long id , final Class<T> entityClass) {
    	try{
    		return em.find(entityClass, id);    		
    	} catch (IllegalArgumentException e) {
    		logger.warning("Cannot get an entity with the given arguments! Thus NULL is returned! Error: " + e.getLocalizedMessage());
    		return null;
    	}
    }

    /**
     * Updates the given entity.
     * @param model The entity to update
     * @throws EntityNotFoundException If the given entity can not be found.
     */
    public void update(final T model) throws EntityNotFoundException {
    	try{
    		em.merge(model);    		
    	} catch (IllegalArgumentException e) {
    		throw new EntityNotFoundException(String.format("Cannot update the entity : %s", model), e);
    	}
    }

    /**
     * Deletes the entity of the given class type and ID. 
     * @param id The ID of the entity to be deleted
     * @param entityClass The class type of the requested entity
     * @throws EntityNotFoundException If the given entity can not be found.
     */
    public void delete(final Long id, final Class<T> entityClass) throws EntityNotFoundException {
    	try {
    		em.remove(em.find(entityClass, id));    		
    	} catch (IllegalArgumentException e) {
    		throw new EntityNotFoundException(String.format("Cannot delete the entity of type:(%s) and ID:%d", entityClass.getName(), id), e);
    	}
    }

    /**
     * Returns the number of entities of the given entity type
     * @param entityClass The class type of the entities to be counted
     * @return The number of entities of the given class type
     */
    public int count(final Class<T> entityClass) {
    	return getAll(entityClass).size();
    }    
    
}
