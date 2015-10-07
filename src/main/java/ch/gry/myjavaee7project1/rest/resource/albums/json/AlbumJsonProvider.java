/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.albums.json;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import ch.gry.myjavaee7project1.rest.resource.albums.Albums;
import ch.gry.myjavaee7project1.rest.resource.json.Link;
import ch.gry.myjavaee7project1.rest.resource.tracks.Tracks;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.json.Json;
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
public class AlbumJsonProvider implements MessageBodyReader<Album>, MessageBodyWriter<Album> {

    private static final Logger logger = Logger.getLogger(AlbumJsonProvider.class.getName());

    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     >>> AlbumJsonProvider::isReadable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Album.class.isAssignableFrom(type);
    }

    @Override
    public Album readFrom(Class<Album> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        logger.info(String.format("     >>> AlbumJsonProvider::readFrom(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        JsonObject jsonObj = Json.createReader(in).readObject();
        
        JsonNumber id = jsonObj.getJsonNumber(AlbumJsonKey.ID.getKey());
        if(id==null) {
            throw new BadRequestException("Could not parse id!");
        }

        JsonString title = jsonObj.getJsonString(AlbumJsonKey.TITLE.getKey());
        if(title==null) {
            throw new BadRequestException("Could not parse title!");
        }
        
        JsonNumber artistId = jsonObj.getJsonNumber(AlbumJsonKey.ARTIST_ID.getKey());
        if(artistId==null) {
            throw new BadRequestException("Could not parse artistId!");
        }
        
        JsonString appearance = jsonObj.getJsonString(AlbumJsonKey.APPEARANCE.getKey());
        if(appearance==null) {
            throw new BadRequestException("Could not parse appearance!");
        }
        
        
        Album album = new Album(title.getString(), artistId.longValue(), LocalDate.parse(appearance.getChars(), DateTimeFormatter.ISO_DATE));
        album.setId(id.longValue());
        
        return album;        
    }

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< AlbumJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Album.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Album t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< AlbumJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Album album, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< AlbumJsonProvider::writeTo(..) -----> album: %s, type:%s, type1:%s, antns:%s, mt:%s", album, type, type1, antns, mt));
        JsonObject jsonObject = toJson(album, uriInfo);
        out.write(jsonObject.toString().getBytes("UTF-8"));
    }
    
    protected static JsonObject toJson(final Album album, final UriInfo uriInfo) {
        return Json.createObjectBuilder().
                add(AlbumJsonKey.ID.getKey(), album.getId() != null ? album.getId().toString() : "").
                add(AlbumJsonKey.TITLE.getKey(), album.getTitle() != null ? album.getTitle() : "").
                add(AlbumJsonKey.ARTIST_ID.getKey(), album.getArtistId()!= null ? album.getArtistId().toString() : "").
                add(AlbumJsonKey.APPEARANCE.getKey(), album.getAppearance() != null ? album.getAppearance().toString() : "").
                add("links", Link.asJsonArray(Arrays.asList(new Link("self", uriInfo.getBaseUriBuilder().
                                path(Albums.class).
                                path(album.getId().toString()).
                                build().toString()),
                        new Link("tracks", uriInfo.getBaseUriBuilder().
                                 path(Albums.class).
                                path(Albums.class, "getTracksSubResource").
                                path(Tracks.class).
                                resolveTemplate("albumId", album.getId()).
                               build().toString())))).
                build();
        
    }

}

    