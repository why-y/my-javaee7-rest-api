/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.chapters;

import ch.gry.myjavaee7project1.books.boundary.BookService;
import ch.gry.myjavaee7project1.books.model.Chapter;
import ch.gry.rest.exception.ResourceNotFoundException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author yvesgross
 */
@Path("/")
public class Chapters {
    
    private Logger logger = Logger.getLogger(getClass().getName());
 
//    @Inject // how to properly use @Inject?
    @EJB
    BookService service;

    
    @POST
    public Chapter createChapter(
            @PathParam("chapterId") final Long bookId, // PathParams of the parent resources are also accessable from here!
            final Chapter newChapter){
        
        logger.info("REST-POST: createChapter()");
        try {
            return service.createChapter(bookId, newChapter);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    public Collection<Chapter> getChapters(@PathParam("chapterId") final Long bookId) {
        logger.info("REST-GET: getChapters()");
        try {
            return service.getChapters(bookId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    @Path("{chapterId}")
    public Chapter getChapter(
            @PathParam("bookId") final Long bookId, // PathParams of the parent resources are also accessable from here!,
            @PathParam("chapterId") final Long chapterId) {
        logger.info(String.format("REST-GET: getChapter %d of book %d", chapterId, bookId));
        try {
            return service.getChapter(bookId, chapterId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @PUT
    @Path("{chapterId}")
    public void updateChapter(@PathParam("chapterId") final Long chapterId, final Chapter chapter) {
        logger.info(String.format("REST-PUT: updateChapter(%d)", chapterId));
        try {
            service.updateChapter(chapterId, chapter);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @DELETE
    @Path("{chapterId}")
    public void deleteChapter(
            @PathParam("chapterId") final Long bookId, // PathParams of the parent resources are also accessable from here!
            @PathParam("chapterId") final Long chapterId) {
        logger.info(String.format("REST-DELETE: deleteChapter(%d) of the book(%d)", chapterId, bookId));
        try {
            service.deleteChapter(bookId, chapterId);
        } catch (ResourceNotFoundException ex) {
            throw new NotFoundException(ex);
        }
    }
    
}
