package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.services.IPlaylistService;

public class DeleteCommand implements ICommand{
    private final IPlaylistService playlistService;

    public DeleteCommand(IPlaylistService playlistService)
    {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String uid = tokens.get(1);
        String pid = tokens.get(2);
        try{
            boolean deleteSuccess = playlistService.delete(uid, pid);
            if(deleteSuccess)
            {
                System.out.println("Delete Successful");
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
