/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource;

import java.text.ParseException;
import java.util.UUID;
import ch.gry.myjavaee7project1.books.model.Book;
import javax.json.Json;
import javax.json.JsonObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author yvesgross
 */
public class BooksTest {
    
    final static String UUID_1 = "947e641b-11d3-4e04-9c9e-c64e90197803";
    final static String IBAN_1 = "823468";
    final static JsonObject BOOK_1_JSON = Json.createObjectBuilder().
            add("id", UUID_1).
            add("iban", IBAN_1).
            add("title", "The Real Book").
            add("author", "Evan Moore").
            build();
    
    public BooksTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createBook method, of class Books.
     */
    @Test
    @Ignore
    public void testCreateBook() {
        System.out.println("createBook");
        String title = "An Awsome Story";
        String author = "Mark Zempala";
        String iban = "29038";
        String bookJson = "{\"id\":\"\"," + 
                "\"title\":\"" + title + 
                "\",\"author\":\"" + author + 
                "\",\"iban\":\"" + iban + "\"}";
        System.out.println(bookJson);
        Books resource = new Books();
        String expResult = "";
        JsonObject result = resource.createBook(BOOK_1_JSON);
        System.out.println(result.toString());
        assertEquals(BOOK_1_JSON, result);
    }
   
}
