/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.artists.json;

import ch.gry.myjavaee7project1.musicshelf.model.Artist;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
public class ArtistsCollectionProvider implements MessageBodyWriter<Collection<Artist>> {

    private static final Logger logger = Logger.getLogger(ArtistJsonProvider.class.getName());

    @Context
    UriInfo uriInfo;
    

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        
        logger.info(String.format("     <<< ArtistsJsonProvider::isWritable(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1.getClass() + "-" + type1.getTypeName(), Arrays.toString(antns), mt));
        // we expect a Collection of artists, which is a Parameterized Type with one parameter type "Artist"
        if(type1 instanceof ParameterizedTypeImpl) {
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) type1;
            Type[] paramTypes = parameterizedType.getActualTypeArguments();
            if(paramTypes.length==1 && paramTypes[0]==Artist.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getSize(Collection<Artist> t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        logger.info(String.format("     <<< ArtistsJsonProvider::getSize(..) -----> type:%s type1:%s antns:%s mt:%s", type, type1, antns, mt));
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Collection<Artist> artists, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        logger.info(String.format("     <<< ArtistsJsonProvider::writeTo(..) -----> artists: %s, type:%s, type1:%s, antns:%s, mt:%s", artists, type, type1, antns, mt));
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        artists.stream().forEach(artist -> jsonArrayBuilder.add(ArtistJsonProvider.toJson(artist, uriInfo)));
        Json.createWriter(out).writeArray(jsonArrayBuilder.build());
    }
        
}
