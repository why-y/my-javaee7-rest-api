/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.books.boundary;

import ch.gry.rest.exception.ResourceNotFoundException;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.myjavaee7project1.books.model.Chapter;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author yvesgross
 */
@Local
public interface BookService {

    /**
     *
     * @param newBook
     * @return
     */
     public Book createBook(final Book newBook);

    /**
     *
     * @return
     */
    public Collection<Book> getBooks();
    
    /**
     *
     * @param id
     * @return
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public Book getBook(final Long id) throws ResourceNotFoundException;
    
    /**
     *
     * @param book
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public void updateBook(final Book book) throws ResourceNotFoundException ;
    
    /**
     *
     * @param id
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public void deleteBook(final Long id) throws ResourceNotFoundException;

    /**
     *
     * @return
     */
    public int countBooks();

    /**
     *
     * @param bookId
     * @param newChapter
     * @return
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public Chapter createChapter(final Long bookId, final Chapter newChapter) throws ResourceNotFoundException;
    
    /**
     *
     * @param bookId
     * @param chapterId
     * @return
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public Chapter getChapter(final Long bookId, final Long chapterId) throws ResourceNotFoundException;
    
    /**
     *
     * @param bookId
     * @return
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public Collection<Chapter> getChapters(final Long bookId) throws ResourceNotFoundException;
    
    /**
     *
     * @param bookId
     * @param chapter
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public void updateChapter(final Long bookId, final Chapter chapter) throws ResourceNotFoundException;
    
    /**
     *
     * @param bookId
     * @param chapterId
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public void deleteChapter(final Long bookId, final Long chapterId) throws ResourceNotFoundException;
    
    /**
     *
     * @param bookId
     * @return
     * @throws ch.gry.rest.exception.ResourceNotFoundException
     */
    public int countChapters(final Long bookId) throws ResourceNotFoundException;
}
