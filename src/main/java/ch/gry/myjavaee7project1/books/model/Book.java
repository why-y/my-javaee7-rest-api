/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.books.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author yvesgross
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UUID id;
        
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    private String iban;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.author);
        hash = 41 * hash + Objects.hashCode(this.iban);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.iban, other.iban)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "model.Book[ id=" + id + " ]";
    }
    
//    public static Book fromJson(JsonObject jsonObj) throws ParseException {
//
//        JsonString id = jsonObj.getJsonString("id");
//        if(id==null) {
//            throw new ParseException("Could not parse id!", 0);
//        }
//
//        JsonString title = jsonObj.getJsonString("title");
//        if(title==null) {
//            throw new ParseException("Could not parse title!", 0);
//        }
//        
//        JsonString author = jsonObj.getJsonString("author");
//        if(author==null) {
//            throw new ParseException("Could not parse author!", 0);
//        }
//        
//        JsonString iban = jsonObj.getJsonString("iban");
//        if(iban==null) {
//            throw new ParseException("Could not parse iban!", 0);
//        }
//        
//        Book book = new Book();
//        
//        book.setId(id.getString().isEmpty() ? null : UUID.fromString(id.getString()));
//        book.setTitle(title.getString());
//        book.setAuthor(author.getString());
//        book.setIban(iban.getString());
//        return book;
//    }
//
//    public JsonObject asJson() {
//        return Json.createObjectBuilder().
//                add(JSON_KEY.ID.getKey(), this.id!=null ? this.id.toString() : "").
//                add(JSON_KEY.TITLE.getKey(), this.title!=null ? this.title : "").
//                add(JSON_KEY.AUTHOR.getKey(), this.author!=null?  this.author : "").
//                add(JSON_KEY.IBAN.getKey(), this.iban!=null ? this.iban : "").
//                build();
//    }
    
}
