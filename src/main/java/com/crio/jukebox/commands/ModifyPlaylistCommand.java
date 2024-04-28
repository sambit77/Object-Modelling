package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.util.ElementScanner6;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;
    public ModifyPlaylistCommand(IPlaylistService playlistService)
    {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String modif_type = tokens.get(1);
        if(modif_type.equalsIgnoreCase("ADD-SONG"))
        {
            addSong(tokens);
        }
        else if(modif_type.equalsIgnoreCase("DELETE-SONG"))
        {
            deleteSong(tokens);
        }
        else
        {
            //
        }


        
    }
    public void deleteSong(List<String> tokens)
    {
        String uid = tokens.get(2);
        String pid = tokens.get(3);
        List<String> songIDs = new ArrayList<String>();

        for(int i = 4; i < tokens.size() ; i++)
        {
            songIDs.add(tokens.get(i));
        }

        try{
            Playlist playlist = playlistService.deleteSong(uid, pid, songIDs);
            /*System.out.println("Playlist ID - "+playlist.getId());
            System.out.println("Playlist NAme - "+playlist.getName());
            List<String> updatedIDs = playlist.getSongIDs();
            StringBuilder finalSongIDs = new StringBuilder();
           for(int i = 0 ; i < updatedIDs.size() ; i++)
           {
            if(i != updatedIDs.size()-1)
            finalSongIDs.append(updatedIDs.get(i)+" ");
            else
            finalSongIDs.append(updatedIDs.get(i));
           }
           System.out.println("Song IDs - "+finalSongIDs);*/
           playlistService.printPlayList(playlist);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void addSong(List<String> tokens)
    {
        String uid = tokens.get(2);
        String pid = tokens.get(3);
        List<String> songIDs = new ArrayList<String>();

        for(int i = 4; i < tokens.size() ; i++)
        {
            songIDs.add(tokens.get(i));
        }

        try{
            Playlist playlist = playlistService.addSong(uid, pid, songIDs);
            /*System.out.println("Playlist ID - "+playlist.getId());
            System.out.println("Playlist NAme - "+playlist.getName());
            List<String> updatedIDs = playlist.getSongIDs();
            StringBuilder finalSongIDs = new StringBuilder();
           for(int i = 0 ; i < updatedIDs.size() ; i++)
           {
            if(i != updatedIDs.size()-1)
            finalSongIDs.append(updatedIDs.get(i)+" ");
            else
            finalSongIDs.append(updatedIDs.get(i));
           }
           System.out.println("Song IDs - "+finalSongIDs);*/
           playlistService.printPlayList(playlist);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
    
}
