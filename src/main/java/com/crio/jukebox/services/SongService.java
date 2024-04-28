package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class SongService  implements ISongService{

    private final ISongRepository songRepository;
    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    public SongService(ISongRepository songRepository,IPlaylistRepository playlistRepository,IUserRepository userRepository)
    {
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Song create(String name, String genre, String album, List<String> artists) {
        // TODO Auto-generated method stub
        Song song = new Song(name, genre, album, artists);

        return songRepository.save(song);
    }
  
}
