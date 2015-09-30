/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.books.ejb;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import java.util.Collection;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author yvesgross
 */
@Stateless
//@Default // this is the only default implementation of the BookService
public class BookServiceImpl implements BookService{
    
    Map<Long, Book> books = new HashMap<>();

    /**
     *
     * @param newBook
     * @return
     */
    @Override
    public Book createBook(final Book newBook) {
        long newId = books.size() + 100;
        newBook.setId(newId);
        books.put(newId, newBook);
        return newBook;
    }
    
    @Override
    public Collection<Book> getBooks() {
        return books.values();
    }

    @Override
    public Book getBook(final Long id) throws ResourceNotFoundException {
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
    public void deleteBook(final Long id) throws ResourceNotFoundException{
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
