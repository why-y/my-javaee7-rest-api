/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.gry.myjavaee7project1.musicshelf.boundary.CrudService;
import ch.gry.myjavaee7project1.musicshelf.model.Model;
import ch.gry.rest.exception.ResourceNotFoundException;

/**
 *
 * @author yvesgross
 * @param <T>
 */
public class AbstractCrudService<T extends Model> implements CrudService<T> {

	private static final Logger logger = Logger.getLogger(AbstractCrudService.class.getName());
	
    @PersistenceContext
    EntityManager em;
    
    /**
     *
     * @param startId
     */
    public AbstractCrudService() {
    }
    
    /**
     *
     * @param newModel
     * @return
     */
    @Override
    public T create(final T newModel) {
    	logger.info("About to persist the new Model : " + newModel);
    	newModel.setId(null);
    	em.persist(newModel);
    	em.flush();
    	logger.info("Just persisted the new Model. Assigned ID: " + newModel.getId());
        return newModel;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<T> getAll(final Class<T> entityClass) {
    	return em.createQuery("FROM " + entityClass.getName(), entityClass)
    			.getResultList();
    }

    /**
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public T get(final Long id , final Class<T> entityClass) throws ResourceNotFoundException {
    	try{
    		return em.find(entityClass, id);    		
    	} catch(IllegalArgumentException e) {
    		throw new ResourceNotFoundException(e);
    	}
    }

    /**
     *
     * @param model
     * @throws ResourceNotFoundException
     */
    @Override
    public void update(final T model) throws ResourceNotFoundException {
    	try {
    		em.merge(model);    		
    	} catch(IllegalArgumentException e) {
    		throw new ResourceNotFoundException(e);
    	}
    }

    /**
     *
     * @param id
     * @throws ResourceNotFoundException
     */
    @Override
    public void delete(final Long id, final Class<T> entityClass) throws ResourceNotFoundException {
    	try {
    		em.remove(em.find(entityClass, id));    		
    	} catch (IllegalArgumentException e) {
    		throw new ResourceNotFoundException(e);
    	}
    }

    /**
     *
     * @return
     */
    @Override
    public int count(final Class<T> entityClass) {
    	return getAll(entityClass).size();
    }    
    
}
