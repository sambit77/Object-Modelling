package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public interface IPlaylistService{
    public Playlist create(String uid,String name,List<String> songIDs) throws Exception;
    public boolean delete(String uid,String pid) throws Exception;
    public Playlist addSong(String uid,String pid,List<String> songIDs) throws Exception;
    public Playlist deleteSong(String uid,String pid,List<String> songIDs) throws Exception;
    public boolean playPlayList(String uid,String pid) throws Exception;
    public boolean playSong(String uid,String songId) throws Exception;
    public boolean play(String songID) throws Exception;  
    public void printPlayList(Playlist playlist);  
}
