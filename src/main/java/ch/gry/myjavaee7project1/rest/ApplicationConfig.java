/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author yvesgross
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ch.gry.myjavaee7project1.rest.GreetingResource.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.books.Books.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.books.json.BookJsonProvider.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.books.json.BooksJsonProvider.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.chapters.Chapters.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.chapters.json.ChapterJsonProvider.class);
        resources.add(ch.gry.myjavaee7project1.rest.resource.chapters.json.ChaptersJsonProvider.class);
    }
    
}
