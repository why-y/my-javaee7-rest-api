/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.books.json;

import ch.gry.myjavaee7project1.books.model.Book;
import ch.gry.myjavaee7project1.rest.resource.books.Books;
import ch.gry.myjavaee7project1.rest.resource.json.Link;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author yvesgross
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookJsonProvider implements MessageBodyReader<Book>, MessageBodyWriter<Book> {

    private static final Logger logger = Logger.getLogger(BookJsonProvider.class.getName());

    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     >>> BookJsonProvider::isReadable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Book.class.isAssignableFrom(type);
    }

    @Override
    public Book readFrom(Class<Book> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        logger.info(String.format("     >>> BookJsonProvider::readFrom(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        JsonObject jsonObj = Json.createReader(in).readObject();
        
        JsonNumber id = jsonObj.getJsonNumber(BookJsonKey.ID.getKey());
        if(id==null) {
            throw new BadRequestException("Could not parse id!");
        }

        JsonString title = jsonObj.getJsonString(BookJsonKey.TITLE.getKey());
        if(title==null) {
            throw new BadRequestException("Could not parse title!");
        }
        
        JsonString author = jsonObj.getJsonString(BookJsonKey.AUTHOR.getKey());
        if(author==null) {
            throw new BadRequestException("Could not parse author!");
        }
        
        JsonString iban = jsonObj.getJsonString(BookJsonKey.IBAN.getKey());
        if(iban==null) {
            throw new BadRequestException("Could not parse iban!");
        }
                
        Book book = new Book();
        
        book.setId(id.longValue());
        book.setTitle(title.getString());
        book.setAuthor(author.getString());
        book.setIban(iban.getString());
        return book;        
    }

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< BookJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Book.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Book t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< BookJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Book book, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< BookJsonProvider::writeTo(..) -----> book: %s, type:%s, type1:%s, antns:%s, mt:%s", book, type, type1, antns, mt));
        JsonObject jsonObject = toJson(book, uriInfo);
        out.write(jsonObject.toString().getBytes("UTF-8"));
    }
    
    protected static JsonObject toJson(final Book book, final UriInfo uriInfo) {
        return Json.createObjectBuilder().
                add(BookJsonKey.ID.getKey(), book.getId() != null ? book.getId().toString() : "").
                add(BookJsonKey.TITLE.getKey(), book.getTitle() != null ? book.getTitle() : "").
                add(BookJsonKey.AUTHOR.getKey(), book.getAuthor() != null ? book.getAuthor() : "").
                add(BookJsonKey.IBAN.getKey(), book.getIban() != null ? book.getIban() : "").
                add("links", Link.asJsonArray(Arrays.asList(
                        new Link("self", uriInfo.getBaseUriBuilder().
                                path(Books.class).
                                path(book.getId().toString()).
                                build().toString())))).
                build();
        
    }

}

    