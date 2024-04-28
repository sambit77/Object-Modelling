package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity{
    private final String name;
    List<String> songIDs;
    boolean isActive = false;
    int idx = 0; //Playing song index

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getIdx() {
        return idx;
    }

    @Override
    public String toString() {
        return "Playlist ID - "+id;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Playlist(String name, List<String> songIDs)
    {
        this.name = name;
        this.songIDs = songIDs;
        this.isActive = false;
        this.idx = 0;
    }

    public String getName() {
        return name;
    }

    public List<String> getSongIDs() {
        return songIDs;
    }

    public void setSongIDs(List<String> songIDs) {
        this.songIDs = songIDs;
    }

    

    
    
}
