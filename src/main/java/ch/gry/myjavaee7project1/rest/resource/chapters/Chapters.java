/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.chapters;

import ch.gry.myjavaee7project1.books.model.Chapter;
import ch.gry.myjavaee7project1.rest.resource.books.Books;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author yvesgross
 */
@Path("/")
public class Chapters {
    
    private Logger logger = Logger.getLogger(Chapters.class.getName());
 
    private final List<Chapter> CHAPTERS = Arrays.asList(
            new Chapter(1l, "Chapter 1", "Text 1"), 
            new Chapter(2l, "Chapter 2", "Text 2"));
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Chapter> getChapters() {
        logger.info("REST-GET: getChapters()");
        return CHAPTERS;
    }
    
}
