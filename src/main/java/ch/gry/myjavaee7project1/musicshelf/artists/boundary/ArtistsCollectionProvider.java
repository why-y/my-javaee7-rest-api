/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.artists.boundary;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import ch.gry.myjavaee7project1.musicshelf.artists.entity.Artist;

/**
 *
 * @author yvesgross
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistsCollectionProvider implements MessageBodyWriter<Collection<Artist>> {

    @Inject
    private Logger logger;

    @Context
    UriInfo uriInfo;
    

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        logger.info(String.format("     <<< ArtistsCollectionProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, genericType.getClass() + "-" + genericType.getTypeName(), Arrays.toString(annotations), mediaType));
        
        if (!Collection.class.isAssignableFrom(type) || !MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {
            return false;
        }

        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        final ParameterizedType parameterizedType = (ParameterizedType) genericType;
        final Type actualType = parameterizedType.getActualTypeArguments()[0];
        return actualType == Artist.class;
    }

    @Override
    public long getSize(Collection<Artist> t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ArtistsCollectionProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Collection<Artist> artists, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< ArtistsCollectionProvider::writeTo(..) -----> artists: %s, type:%s, type1:%s, antns:%s, mt:%s", artists, type, type1, antns, mt));
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        artists.stream().forEach(artist -> jsonArrayBuilder.add(ArtistJsonProvider.toJson(artist, uriInfo)));
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.writeArray(jsonArrayBuilder.build());
        jsonWriter.close();
    }
        
}
