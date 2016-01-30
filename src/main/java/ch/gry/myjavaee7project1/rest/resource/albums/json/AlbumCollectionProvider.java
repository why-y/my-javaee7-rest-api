/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.albums.json;

import ch.gry.myjavaee7project1.musicshelf.model.Album;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 *
 * @author yvesgross
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumCollectionProvider implements MessageBodyWriter<Collection<Album>> {

    private static final Logger logger = Logger.getLogger(AlbumCollectionProvider.class.getName());

    @Context
    UriInfo uriInfo;
    

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        
        logger.info(String.format("     <<< AlbumCollectionProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1.getClass() + "-" + type1.getTypeName(), Arrays.toString(antns), mt));
        // we expect a Collection of albums, which is a Parameterized Type with one parameter type "Album"
        if(type1 instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type1;
            Type[] paramTypes = parameterizedType.getActualTypeArguments();
            if(paramTypes.length==1 && paramTypes[0]==Album.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getSize(Collection<Album> t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< AlbumCollectionProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Collection<Album> albums, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< AlbumCollectionProvider::writeTo(..) -----> albums: %s, type:%s, type1:%s, antns:%s, mt:%s", albums, type, type1, antns, mt));

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        albums.stream().forEach(album -> jsonArrayBuilder.add(AlbumJsonProvider.toJson(album, uriInfo)));
        
        Json.createWriter(out).writeArray(jsonArrayBuilder.build());
    }
        
}
