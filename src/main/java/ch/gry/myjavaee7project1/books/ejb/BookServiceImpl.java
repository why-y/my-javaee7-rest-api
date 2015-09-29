/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.books.ejb;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.rest.exception.ResourceNotFoundException;
import javax.ejb.Stateless;

/**
 *
 * @author yvesgross
 */
@Stateless
//@Default // this is the only default implementation of the BookService
public class BookServiceImpl implements BookService{
    
    Map<UUID, Book> books = new HashMap();

    /**
     *
     * @param newBook
     * @return
     */
    @Override
    public Book createBook(final Book newBook) {
        UUID newId = UUID.randomUUID();
        newBook.setId(newId);
        books.put(newId, newBook);
        return newBook;
    }
    
    @Override
    public Collection<Book> getBooks() {
        return books.values();
    }

    @Override
    public Book getBook(final UUID id) throws ResourceNotFoundException {
        if(!books.containsKey(id)) {
            throw new ResourceNotFoundException(String.format("Unknown book id %s!", id));
        }
        return books.get(id);
    }
    
    @Override
    public void updateBook(final Book book)throws ResourceNotFoundException{
        if(!books.containsKey(book.getId())) {
            throw new ResourceNotFoundException(String.format("Unknown book with id %s! Cannot be updated!", book.getId()));
        }
        else {
            books.put(book.getId(), book);
        }
    }
    
    @Override
    public void deleteBook(final UUID id) throws ResourceNotFoundException{
        if(!books.containsKey(id)){
            throw new ResourceNotFoundException(String.format("Unknown book id %s! Cannot be deleted!", id));
        }
        else {
            books.remove(id);
        }
    }
    
    @Override
    public int countBooks(){
        return books.size();
    }
}
