package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;
    public CreatePlaylistCommand(IPlaylistService playlistService)
    {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String uid = tokens.get(1);
        String name = tokens.get(2);
        List<String> songs = new ArrayList<String>();
        for(int i = 3 ; i < tokens.size() ; i++)
        {
            songs.add(tokens.get(i));
        }

        try{
            Playlist playlist = playlistService.create(uid, name, songs);
            System.out.println(playlist.toString());
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }




        
    }
    
}
