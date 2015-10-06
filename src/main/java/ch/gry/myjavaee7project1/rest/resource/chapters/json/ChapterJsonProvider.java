/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.chapters.json;

import ch.gry.myjavaee7project1.books.model.Chapter;
import ch.gry.myjavaee7project1.rest.resource.books.Books;
import ch.gry.myjavaee7project1.rest.resource.chapters.Chapters;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
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
public class ChapterJsonProvider  implements MessageBodyReader<Chapter>, MessageBodyWriter<Chapter> {
    
    private static final Logger logger = Logger.getLogger(ChapterJsonProvider.class.getName());
    
    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     >>> ChapterJsonProvider::isReadable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Chapter.class.isAssignableFrom(type);
    }

    @Override
    public Chapter readFrom(Class<Chapter> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        logger.info(String.format("     >>> ChapterJsonProvider::readFrom(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        JsonObject jsonObj = Json.createReader(in).readObject();
        
        JsonNumber id = jsonObj.getJsonNumber(ChapterJsonKey.ID.getKey());
        if(id==null) {
            throw new BadRequestException("Could not parse id!");
        }

        JsonString title = jsonObj.getJsonString(ChapterJsonKey.TITLE.getKey());
        if(title==null) {
            throw new BadRequestException("Could not parse title!");
        }
        
        JsonString text = jsonObj.getJsonString(ChapterJsonKey.TEXT.getKey());
        if(text==null) {
            throw new BadRequestException("Could not parse text!");
        }
        
        JsonString href = jsonObj.getJsonString(ChapterJsonKey.HREF.getKey());
        if(href==null) {
            throw new BadRequestException("Could not parse HREF!");            
        }
        
        Chapter chapter = new Chapter();
        
        chapter.setId(id.longValue());
        chapter.setTitle(title.getString());
        chapter.setText(text.getString());
        return chapter;        
    }

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ChapterJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Chapter.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Chapter t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ChapterJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Chapter chapter, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< ChapterJsonProvider::writeTo(..) -----> chapter: %s, type:%s, type1:%s, antns:%s, mt:%s", chapter, type, type1, antns, mt));
        JsonObject jsonObject = toJson(chapter, uriInfo);
        out.write(jsonObject.toString().getBytes("UTF-8"));
    }
    
    protected static JsonObject toJson(final Chapter chapter, final UriInfo uriInfo) {
        return Json.createObjectBuilder().
                add(ChapterJsonKey.ID.getKey(), chapter.getId() != null ? chapter.getId().toString() : "").
                add(ChapterJsonKey.TITLE.getKey(), chapter.getTitle() != null ? chapter.getTitle() : "").
                add(ChapterJsonKey.TEXT.getKey(), chapter.getText()!= null ? chapter.getText(): "").
                add("links", attachLinks(chapter, uriInfo)).
                build();
        
    }

    private static JsonArray attachLinks(final Chapter chapter, final UriInfo uriInfo) {
        assert chapter != null : "Argument chapter must not be null!";
        assert uriInfo != null : "Argument uriInfo must not be null!";
        String bookId = uriInfo.getPathParameters().get("bookId").get(0);
        URI selfUri = uriInfo.getBaseUriBuilder().
                path(Books.class).
                path(Books.class, "getChaptersResource").
                path(Chapters.class).
                path(chapter.getId().toString()).
                resolveTemplate("bookId", bookId).
                build();
        JsonObject jsonLink = Json.createObjectBuilder().
                add("rel", "self").
                add("href", selfUri.toString()).
                build();
        
        return Json.createArrayBuilder().add(jsonLink).build();
    }
    
}
