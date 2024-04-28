package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand {

    private final IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService playlistService)
    {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        String uid = tokens.get(1);
        String sid = tokens.get(2);
        // TODO Auto-generated method stub
        try{
            playlistService.playSong(uid, sid);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
