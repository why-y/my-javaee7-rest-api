/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.common.boundary;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author yvesgross
 */
public class Link {
    
    public static final String REL = "rel";
    public static final String HREF = "href";
    
    private String href;

    public Link(String rel, String href) {
        this.href = href;
        this.rel = rel;
    }

    /**
     * Get the value of href
     *
     * @return the value of href
     */
    public String getHref() {
        return href;
    }

    /**
     * Set the value of href
     *
     * @param href new value of href
     */
    public void setHref(String href) {
        this.href = href;
    }

    private String rel;

    /**
     * Get the value of rel
     *
     * @return the value of rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * Set the value of rel
     *
     * @param rel new value of rel
     */
    public void setRel(String rel) {
        this.rel = rel;
    }
    
    public JsonObject asJson() {
        return Json.createObjectBuilder().
            add(Link.REL, getRel()).
            add(Link.HREF, getHref()).
            build(); 
    }
    
    public static JsonArray asJsonArray(final List<Link> links) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Link link : links) {
            builder.add(link.asJson());
        }
        return builder.build();
    }

}
