/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource;

/**
 *
 * @author yvesgross
 */
public enum BookJsonKey {

    ID("id"), TITLE("title"), AUTHOR("author"), HREF("href"), IBAN("iban");
    private final String key;

    private BookJsonKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static BookJsonKey byString(String key) {
        switch (key) {
            case "id" :
                return ID;
            case "title":
                return TITLE;
            case "author":
                return AUTHOR;
            case "href":
                return HREF;
            case "iban":
                return IBAN;
            default:
                throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
        }
    }

}
