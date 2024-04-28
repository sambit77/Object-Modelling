package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{

    

    private final String name;
    private String genre;
    private String album;
    //private String albumArtist;
    private List<String> artists;

    public Song(String name,String genre,String album,List<String> artists)
    {
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.artists = artists;
        //this.albumArtist = albumArtist;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

   
    
}
