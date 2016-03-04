/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.artists.boundary;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonWriter;
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

import ch.gry.myjavaee7project1.musicshelf.artists.entity.Artist;
import ch.gry.myjavaee7project1.musicshelf.common.boundary.Link;

/**
 *
 * @author yvesgross
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistJsonProvider implements MessageBodyReader<Artist>, MessageBodyWriter<Artist> {

    private static final Logger logger = Logger.getLogger(ArtistJsonProvider.class.getName());

    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     >>> ArtistJsonProvider::isReadable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Artist.class.isAssignableFrom(type);
    }

    @Override
    public Artist readFrom(Class<Artist> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        logger.info(String.format("     >>> ArtistJsonProvider::readFrom(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        JsonObject jsonObj = Json.createReader(in).readObject();
        
        JsonNumber id = jsonObj.getJsonNumber(ArtistJsonKey.ID.getKey());
        if(id==null) {
            throw new BadRequestException("Could not parse id!");
        }

        JsonString name = jsonObj.getJsonString(ArtistJsonKey.NAME.getKey());
        if(name==null) {
            throw new BadRequestException("Could not parse name!");
        }
        
        JsonString origin = jsonObj.getJsonString(ArtistJsonKey.ORIGIN.getKey());
        if(origin==null) {
            throw new BadRequestException("Could not parse origin!");
        }
        
                
        Artist artist = new Artist(name.getString(), origin.getString());
        artist.setId(id.longValue());
        
        return artist;        
    }

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ArtistJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Artist.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Artist t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ArtistJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Artist artist, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< ArtistJsonProvider::writeTo(..) -----> artist: %s, type:%s, type1:%s, antns:%s, mt:%s", artist, type, type1, antns, mt));
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.writeObject(toJson(artist, uriInfo));
        jsonWriter.close();
    }
    
    public static JsonObject toJson(final Artist artist, final UriInfo uriInfo) {
        return Json.createObjectBuilder().
                add(ArtistJsonKey.ID.getKey(), artist.getId() != null ? artist.getId().toString() : "").
                add(ArtistJsonKey.NAME.getKey(), artist.getName()!= null ? artist.getName() : "").
                add(ArtistJsonKey.ORIGIN.getKey(), artist.getOrigin() != null ? artist.getOrigin() : "").
                add("links", Link.asJsonArray(Arrays.asList(new Link("self", uriInfo.getBaseUriBuilder().
                                path(Artists.class).
                                path(artist.getId().toString()).
                                build().toString())))).build();
        
    }

}

    