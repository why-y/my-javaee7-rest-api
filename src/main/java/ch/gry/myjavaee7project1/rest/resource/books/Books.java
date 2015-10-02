/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.books;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.myjavaee7project1.rest.resource.chapters.Chapters;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 *
 * @author yvesgross
 */
@Path("books")
public class Books {

    private Logger logger = Logger.getLogger(Books.class.getName());

//    @Inject // how to properly use @Inject?
    @EJB
    BookService service;

    @POST
    public Book createBook(final Book book) {
        logger.info("REST-POST: createBook()");
        return service.createBook(book);
    }

    @GET
    public Collection<Book> getBooks() {
        logger.info("REST-GET: getBooks()");
        return service.getBooks();
    }

    @GET
    @Path("{bookId}")
    public Book getBook(@PathParam("bookId") final Long bookId) {
        logger.info(String.format("REST-GET: getBook(%s)", bookId.toString()));
        try {
            return service.getBook(bookId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    @PUT
    @Path("{bookId}")
    public void updateBook(@PathParam("bookId") final Long bookId, final Book book) {
        logger.info(String.format("REST-PUT: updateBook(%s)", bookId));
        try {

            // Make sure to update the object with the id of the resource.
            // I.e. the id of the given update data is irrelevant.
            book.setId(bookId);

            service.updateBook(book);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    @DELETE
    @Path("{bookId}")
    public void deleteBook(@PathParam("bookId") final Long bookId) {
        logger.info(String.format("REST-DELETE: deleteBook(%s)", bookId));
        try {
            service.deleteBook(bookId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }

    @GET
    @Path("quantity")
    public JsonObject countBooks() {
        logger.info("REST-GET: countBooks()");
        return Json.createObjectBuilder().
                add("numOfBooks", service.countBooks()).
                build();
    }
    
    
    /////////// SUB-RESOURCES //////////////////////////////////////////////////
    
    @Path("{bookId}/chapters")
    public Chapters getChaptersResource() {
        return new Chapters();
    }

}
