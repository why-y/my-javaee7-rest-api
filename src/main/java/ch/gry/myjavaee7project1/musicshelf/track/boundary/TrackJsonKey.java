/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.gry.myjavaee7project1.musicshelf.track.boundary;

/**
 *
 * @author yvesgross
 */
public enum TrackJsonKey {

    ID("id"), TITLE("title"), DURATION("duration"), TRACK_NO("trackNo");
    private final String key;

    private TrackJsonKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static TrackJsonKey byString(String key) {
        switch (key) {
            case "id" :
                return ID;
            case "duration":
                return DURATION;
            case "trackNo":
                return TRACK_NO;
            default:
                throw new IllegalArgumentException(String.format("The given key: \"%s\" has no associated ENUM!", key));
        }
    }

}
