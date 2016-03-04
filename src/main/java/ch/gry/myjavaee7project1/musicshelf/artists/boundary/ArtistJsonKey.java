/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.artists.boundary;

/**
 *
 * @author yvesgross
 */
public enum ArtistJsonKey {

    ID("id"), NAME("name"), ORIGIN("origin");
    private final String key;

    private ArtistJsonKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static ArtistJsonKey byString(String key) {
        switch (key) {
            case "id" :
                return ID;
            case "name":
                return NAME;
            case "origin":
                return ORIGIN;
            default:
                throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
        }
    }

}
