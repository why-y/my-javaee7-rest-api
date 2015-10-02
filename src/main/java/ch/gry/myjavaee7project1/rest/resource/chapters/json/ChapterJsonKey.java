/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.chapters.json;

/**
 *
 * @author yvesgross
 */
public enum ChapterJsonKey {
    ID("id"), TITLE("title"), TEXT("text"), HREF("href");
    private final String key;

    private ChapterJsonKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static ChapterJsonKey byString(String key) {
        switch (key) {
            case "id" :
                return ID;
            case "title":
                return TITLE;
            case "text":
                return TEXT;
            case "href":
                return HREF;
            default:
                throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
        }
    }

    
}
