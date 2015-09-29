/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import java.util.UUID;
import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author yvesgross
 */
@Path("books")
public class Books {
    
    private Logger logger = Logger.getLogger(Books.class.getName());

        private enum JSON_KEY {
        TITLE("title"), AUTHOR("author"), HREF("href"), IBAN("iban");
        private final String key;
        private JSON_KEY(String key){
            this.key = key;
        }
        public String getKey(){
            return this.key;
        }
        public static JSON_KEY byString(String key){
            switch(key){
                case "title" : return TITLE;
                case "author" : return AUTHOR;
                case "href" : return HREF;
                case "iban" : return IBAN;
                default: throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
            }
        }
    };

//    @Inject // how to properly use @Inject?
    @EJB
    BookService service;
    
    @Context
    private UriInfo uriInfo;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public JsonObject createBook(final JsonObject bookJson) {
        logger.info("REST-POST: createBook()");
        String jsonId;
        UUID newId = null;
        try {
            Book newBook = fromJson(bookJson);
            newBook = service.createBook(newBook);
            return toJson(newBook);
        } catch (ParseException ex) {
            throw new BadRequestException("Could not parse the create data (POST) provided with the request", ex);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getBooks() {
        logger.info("REST-GET: getBooks()");
        Collection<Book> allBooks = service.getBooks();
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Book book : allBooks) {
            ab.add(toJson(book));
        }
        return ab.build();
    }
    
    @GET
    @Path("{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getBook(@PathParam("bookId") final UUID bookId) {
        logger.info(String.format("REST-GET: getBook(%s)", bookId.toString()));
        Book book;
        try {
            book = service.getBook(bookId);
            return toJson(book);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @PUT
    @Path("{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("bookId") final UUID bookId, final JsonObject bookJson) {
        logger.info(String.format("REST-PUT: updateBook(%s)", bookId));
        try {
            Book book = fromJson(bookJson);
            
            // Make sure to update the object with the id of the resource.
            // I.e. the id of the given update data is irrelevant.
            book.setId(bookId);
            
            service.updateBook(book);
        } catch (ParseException ex){
            throw new BadRequestException("Could not parse the update data (PUT) provided with the request!", ex);
        } catch (ResourceNotFoundException ex ) {
            throw new NotFoundException(ex);
        }
    }
    
    @DELETE
    @Path("{bookId}")
    public void deleteBook(@PathParam("bookId") final UUID bookId) throws ParseException {
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
    
    private JsonObject toJson(final Book book) {
        assert book!=null;
        return Json.createObjectBuilder().
                add(JSON_KEY.HREF.getKey(), book.getId() != null ? buildHref("books", book.getId().toString()) : "").
                add(JSON_KEY.TITLE.getKey(), book.getTitle() != null ? book.getTitle() : "").
                add(JSON_KEY.AUTHOR.getKey(), book.getAuthor() != null ? book.getAuthor() : "").
                add(JSON_KEY.IBAN.getKey(), book.getIban() != null ? book.getIban() : "").
                build();
    }
    
    private Book fromJson(final JsonObject jsonObj) throws ParseException {
        JsonString id = jsonObj.getJsonString(JSON_KEY.HREF.getKey());
        if(id==null) {
            throw new ParseException("Could not parse id!", 0);
        }

        JsonString title = jsonObj.getJsonString(JSON_KEY.TITLE.getKey());
        if(title==null) {
            throw new ParseException("Could not parse title!", 0);
        }
        
        JsonString author = jsonObj.getJsonString(JSON_KEY.AUTHOR.getKey());
        if(author==null) {
            throw new ParseException("Could not parse author!", 0);
        }
        
        JsonString iban = jsonObj.getJsonString(JSON_KEY.IBAN.getKey());
        if(iban==null) {
            throw new ParseException("Could not parse iban!", 0);
        }
        
        Book book = new Book();
        
        book.setId(id.getString().isEmpty() ? null : UUID.fromString(id.getString()));
        book.setTitle(title.getString());
        book.setAuthor(author.getString());
        book.setIban(iban.getString());
        return book;        
    }
    
    private String buildHref(final String resource, final String id) {
        assert resource!=null : "Argument resource must not be null!";
        assert id!=null : "Argument id must not be null!";
        return String.format("%s%s/%s", uriInfo.getBaseUri(), resource, id);
    }
/*    
    private JsonObject error(final Throwable ex) {
        return error(ex.getLocalizedMessage());
    }
    
    private JsonObject error(String errorMsg) {
        logger.warning(errorMsg);
        return Json.createObjectBuilder().
                add("error", errorMsg).
                build();
    }
*/
}
