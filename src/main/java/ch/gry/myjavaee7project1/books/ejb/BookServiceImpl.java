/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.books.ejb;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import java.util.Collection;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.myjavaee7project1.books.model.Chapter;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author yvesgross
 */
//@Stateless
@Singleton
//@Default // this is the only default implementation of the BookService
public class BookServiceImpl implements BookService{

    private Logger logger = Logger.getLogger(getClass().getName());

    Map<Long, Book> books = new HashMap<>();

    public BookServiceImpl() {
        try {
            // provide some initial books
            Book book = createBook(new Book("Initial Book A", "Finn Higgins", "203980"));
            createChapter(book.getId(), new Chapter("Chapter A-1", "Text A-1"));
            createChapter(book.getId(), new Chapter("Chapter A-2", "Text A-2"));
            
            book = createBook(new Book("Initial Book B", "Karl Ericson", "0389482"));
            createChapter(book.getId(), new Chapter("Chapter B-1", "Text Bs-1"));
            createChapter(book.getId(), new Chapter("Chapter B-2", "Text B-2"));
            
            book = createBook(new Book("Initial Book C", "Mary Joe Lynn", "2384798"));
            createChapter(book.getId(), new Chapter("Chapter C-1", "Text C-1"));
            createChapter(book.getId(), new Chapter("Chapter C-2", "Text C-2"));
            
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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

    @Override
    public Chapter createChapter(final Long bookId, final Chapter newChapter) throws ResourceNotFoundException{
        Book book = getBook(bookId);

        long newId = book.getChapters().size() + 400;
        newChapter.setId(newId);
        book.getChapters().put(newId, newChapter);
        
        return newChapter;
    }

    @Override
    public Chapter getChapter(final Long bookId, final Long chapterId) throws ResourceNotFoundException{
        Book book = getBook(bookId);        
        if(book.getChapters().containsKey(chapterId)) {
            return book.getChapters().get(chapterId);
        }
        else {
            throw new ResourceNotFoundException(String.format("Unknown chapter id %s!", chapterId));            
        }
    }

    @Override
    public Collection<Chapter> getChapters(final Long bookId) throws ResourceNotFoundException {
        return getBook(bookId).getChapters().values();
    }

    @Override
    public void updateChapter(final Long bookId, final Chapter chapter) throws ResourceNotFoundException {
        Book book = getBook(bookId);
        Long chapterId = chapter.getId();
        if(book.getChapters().containsKey(chapterId)) {
            book.getChapters().put(chapterId, chapter);
        }
        else {
            throw new ResourceNotFoundException(String.format("Unknown chapter id %s! Cannot be updated.", chapterId));            
        }        
    }

    @Override
    public void deleteChapter(final Long bookId, final Long chapterId) throws ResourceNotFoundException {
        Book book = getBook(bookId);
        if(book.getChapters().containsKey(chapterId)) {
            book.getChapters().remove(chapterId);
        }
        else {
            throw new ResourceNotFoundException(String.format("Unknown chapter id %s! Cannot be deleted.", chapterId));            
        }        
     
    }

    @Override
    public int countChapters(Long bookId) throws ResourceNotFoundException {
        Book book = getBook(bookId);
        return book.getChapters().size();
    }
}
