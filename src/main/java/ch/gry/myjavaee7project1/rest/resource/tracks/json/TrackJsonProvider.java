/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.tracks.json;

import ch.gry.myjavaee7project1.musicshelf.model.Track;
import ch.gry.myjavaee7project1.rest.resource.albums.Albums;
import ch.gry.myjavaee7project1.rest.resource.json.Link;
import ch.gry.myjavaee7project1.rest.resource.tracks.Tracks;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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

/**
 *
 * @author yvesgross
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackJsonProvider implements MessageBodyReader<Track>, MessageBodyWriter<Track> {

    private static final Logger logger = Logger.getLogger(TrackJsonProvider.class.getName());

    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     >>> TrackJsonProvider::isReadable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Track.class.isAssignableFrom(type);
    }

    @Override
    public Track readFrom(Class<Track> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        logger.info(String.format("     >>> TrackJsonProvider::readFrom(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        JsonObject jsonObj = Json.createReader(in).readObject();
        
        JsonNumber id = jsonObj.getJsonNumber(TrackJsonKey.ID.getKey());
        if(id==null) {
            throw new BadRequestException("Could not parse id!");
        }

        JsonString jsonTitle = jsonObj.getJsonString(TrackJsonKey.TITLE.getKey());
        if(jsonTitle==null) {
            throw new BadRequestException("Could not parse title!");
        }
        
        JsonString duration = jsonObj.getJsonString(TrackJsonKey.DURATION.getKey());
        if(duration==null) {
            throw new BadRequestException("Could not parse duration!");
        }
        
        JsonNumber trackNo = jsonObj.getJsonNumber(TrackJsonKey.TRACK_NO.getKey());
        if(trackNo==null) {
            throw new BadRequestException("Could not parse trackNo!");
        }
                
        Track title = new Track(jsonTitle.toString(), Duration.parse(duration.getChars()), trackNo.intValue());
        title.setId(id.longValue());
        
        return title;        
    }

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< TrackJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        return Track.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Track t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< TrackJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Track title, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< TrackJsonProvider::writeTo(..) -----> title: %s, type:%s, type1:%s, antns:%s, mt:%s", title, type, type1, antns, mt));
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.write(toJson(title, uriInfo).build());
        jsonWriter.close();
    }
    
    protected static JsonObjectBuilder toJson(final Track title, final UriInfo uriInfo) {
        String albumId = uriInfo.getPathParameters().get("albumId").get(0);
        return Json.createObjectBuilder().
                add(TrackJsonKey.ID.getKey(), title.getId() != null ? title.getId().toString() : "").
                add(TrackJsonKey.TITLE.getKey(), title.getTitle() != null ? title.getTitle() : "").
                add(TrackJsonKey.DURATION.getKey(), title.getDuration() != null ? title.getDuration().toString() : "").
                add(TrackJsonKey.TRACK_NO.getKey(), title.getTrackNo() != null ? title.getTrackNo() : 0).
                add("links", Link.asJsonArray(Arrays.asList(new Link("self", uriInfo.getBaseUriBuilder().
                                path(Albums.class).
                                path(Albums.class, "getTracksSubResource").
                                path(Tracks.class).
                                path(title.getId().toString()).
                                resolveTemplate("albumId", albumId).
                                build().toString()))));
        
    }

}

    