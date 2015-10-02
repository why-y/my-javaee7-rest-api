/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource;

import ch.gry.myjavaee7project1.rest.resource.books.Books;
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
    
    final static Long ID_1 = new Long(77);
    final static String IBAN_1 = "823468";
    final static String AUTHOR_1 = "Evan Moore";
    final static String TITLE_1 = "The Real Book";
    final static JsonObject BOOK_1_JSON = Json.createObjectBuilder().
            add("id", ID_1.toString()).
            add("iban", IBAN_1).
            add("title", TITLE_1).
            add("author", AUTHOR_1).
            build();
    
    final static Book BOOK_1 = new Book();
    
    public BooksTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        BOOK_1.setAuthor(AUTHOR_1);
        BOOK_1.setId(ID_1);
        BOOK_1.setIban(IBAN_1);
        BOOK_1.setTitle(TITLE_1);
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
        Books resource = new Books();
        String expResult = "";
        Book result = resource.createBook(BOOK_1);
        System.out.println(result.toString());
        assertEquals(BOOK_1, result);
    }
   
}
