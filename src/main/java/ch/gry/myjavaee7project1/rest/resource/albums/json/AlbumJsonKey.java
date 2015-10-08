/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.rest.resource.albums.json;

/**
 *
 * @author yvesgross
 */
public enum AlbumJsonKey {

    ID("id"), TITLE("title"), ARTIST("artist"), APPEARANCE("appearance");
    private final String key;

    private AlbumJsonKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static AlbumJsonKey byString(String key) {
        switch (key) {
            case "id" :
                return ID;
            case "title":
                return TITLE;
            case "artist":
                return ARTIST;
            case "appearance":
                return APPEARANCE;
            default:
                throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
        }
    }

}
