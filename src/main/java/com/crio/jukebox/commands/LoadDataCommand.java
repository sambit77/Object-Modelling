package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;

    public LoadDataCommand(ISongService songService)
    {
        this.songService = songService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String filename = tokens.get(1);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            //line = reader.readLine();
            while(line != null)
            {
                List<String> songDetails = Arrays.asList(line.split(","));
                //System.out.print(songDetails.toString());
                String songName = songDetails.get(0);
                String songGenre = songDetails.get(1);
                String alnum = songDetails.get(2);
                String album_artist = songDetails.get(3);
                List<String> artists = Arrays.asList(songDetails.get(4).split("#"));

               

               Song song = songService.create(songName, songGenre, alnum, artists);
               line = reader.readLine();
            //    if(song != null)
            //    {
            //     System.out.println("Songs Loaded successfully");
            //    }

            }
    } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Songs Loaded successfully");
        
    }
    
}
