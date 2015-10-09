/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.ejb;

import ch.gry.myjavaee7project1.musicshelf.boundary.CrudService;
import ch.gry.myjavaee7project1.musicshelf.model.Model;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yvesgross
 * @param <T>
 */
public class AbstractCrudService<T extends Model> implements CrudService<T> {
    
    private final Long startId;
    
    protected final Map<Long, T> store = new HashMap<>();

    /**
     *
     * @param startId
     */
    public AbstractCrudService(Long startId) {
        this.startId = startId;
    }
    
    /**
     *
     * @param newModel
     * @return
     */
    @Override
    public T create(final T newModel) {
        long newId = nextId(this.store, this.startId);
        newModel.setId(newId);
        store.put(newId, newModel);
        return newModel;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<T> getAll() {
        return store.values();
    }

    /**
     *
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public T get(Long id) throws ResourceNotFoundException {
        if(!store.containsKey(id)) {
            throw new ResourceNotFoundException(String.format("Unknown model id %s!", id));
        }
        return store.get(id);        
    }

    /**
     *
     * @param model
     * @throws ResourceNotFoundException
     */
    @Override
    public void update(final T model) throws ResourceNotFoundException {
        if(!store.containsKey(model.getId())) {
            throw new ResourceNotFoundException(String.format("Unknown model with id %s! Cannot be updated!", model.getId()));
        }
        else {
            store.put(model.getId(), model);
        }
    }

    /**
     *
     * @param id
     * @throws ResourceNotFoundException
     */
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if(!store.containsKey(id)){
            throw new ResourceNotFoundException(String.format("Unknown model id %s! Cannot be deleted!", id));
        }
        else {
            store.remove(id);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return store.size();
    }    

    protected Long nextId(Map<Long, ?> map, Long startId) {
        Long result = startId;
        if(!map.isEmpty()) {
            Long highestId = map.keySet().stream().max(Comparator.naturalOrder()).get();
            result = ++highestId;
        }
        return result;
    }
}
