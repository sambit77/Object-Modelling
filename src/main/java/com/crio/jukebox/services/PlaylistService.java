package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import javax.lang.model.util.ElementScanner6;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService{

    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;

    public PlaylistService(IPlaylistRepository playlistRepository,IUserRepository userRepository ,ISongRepository songRepository)
    {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }
    @Override
    public Playlist create(String uid, String name, List<String> songIDs) throws Exception{
        // TODO Auto-generated method stub
        final User user = userRepository.findById(uid).orElseThrow(()-> new UserNotFoundException("User Not found"));
        
        //Check If all song IDs are present or not 
        for(String songID : songIDs)
        {
            if(!songRepository.existsById(songID))
            {
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }

        Playlist playlist = new Playlist(name, songIDs);

        return playlistRepository.save(playlist);
    }
    @Override
    public boolean delete(String uid, String pid) throws Exception {
        // TODO Auto-generated method stub
        //return true if delete is successful
        boolean deleteStatus = false;
        Playlist playlist = playlistRepository.findById(pid).orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));

        playlistRepository.delete(playlist);
        deleteStatus = true;

        return deleteStatus;
    }
    @Override
    public Playlist addSong(String uid, String pid, List<String> songIDs) throws Exception {
        // TODO Auto-generated method stub
        final User user = userRepository.findById(uid).orElseThrow(()-> new UserNotFoundException("USer Not Found"));
        
        //Check If all song IDs are present or not 
        for(String songID : songIDs)
        {
            if(!songRepository.existsById(songID))
            {
                throw new SongNotFoundException("Error Message if Any of the Requested Song IDs is not present in the pool.");
            }
        }

        Playlist playlist = playlistRepository.findById(pid).orElseThrow(()->new Exception());
        List<String> songsInPlayList = playlist.getSongIDs();
        for(String sid : songIDs)
        {
            boolean isExistsInPlayList = checkIfSongIsPresentInPlaylist(songsInPlayList, sid);
            if(!isExistsInPlayList)
            {
               songsInPlayList.add(sid);
            }
        }
        playlist.setSongIDs(songsInPlayList);
        return playlistRepository.save(playlist);
    }
    @Override
    public Playlist deleteSong(String uid, String pid, List<String> songIDs) throws Exception {
        // TODO Auto-generated method stub
        final User user = userRepository.findById(uid).orElseThrow(()-> new UserNotFoundException("USer not found custom"));
        
        //Check If all song IDs are present or not 
        for(String songID : songIDs)
        {
            if(!songRepository.existsById(songID))
            {
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        Playlist playlist = playlistRepository.findById(pid).orElseThrow(()->new Exception());
        List<String> songsInPlayList = playlist.getSongIDs();

        for(String sid : songIDs)
        {
            boolean isExistsInPlayList = checkIfSongIsPresentInPlaylist(songsInPlayList, sid);
            if(isExistsInPlayList)
            {
               songsInPlayList.remove(sid);
            }
            else
            {
                throw new Exception();
            }
        }

        playlist.setSongIDs(songsInPlayList);
        return playlistRepository.save(playlist);
    }
    private boolean checkIfSongIsPresentInPlaylist(List<String> songsList,String songID)
    {
        boolean val = false;
        for(String sid : songsList)
        {
            if(sid.equalsIgnoreCase(songID))
            {
                val = true;
                break;
            }
        }

        return val;
    }

	@Override
	public boolean playPlayList(String uid, String pid) throws Exception {
		// TODO Auto-generated method stub
        final User user = userRepository.findById(uid).orElseThrow(()-> new UserNotFoundException());
        Playlist playlist = playlistRepository.findById(pid).orElseThrow(()->new PlaylistNotFoundException());
        
        List<String> songsInPlayList = playlist.getSongIDs();
        if(songsInPlayList.size() < 1)
        {
            throw new PlaylistEmptyException("Playlist is empty.");
        }

        //Deactivate any existing active playlist 
        Playlist currentActivePlayList = playlistRepository.getActivePlayList();
        if(currentActivePlayList != null)
        {
            currentActivePlayList.setActive(false);
            currentActivePlayList.setIdx(0);
            playlistRepository.save(currentActivePlayList);
        }
       

        playlist.setActive(true);
        playlist.setIdx(0);
        List<String> songs = playlist.getSongIDs();
        String songIDToPlay = songs.get(0);
        
        play(songIDToPlay);
        
        playlistRepository.save(playlist);

		return false;
	}

    @Override
    public boolean playSong(String uid, String songId) throws Exception {
        // TODO Auto-generated method stub
        final User user = userRepository.findById(uid).orElseThrow(()-> new UserNotFoundException());
        Playlist playlist = playlistRepository.getActivePlayList();
        if(playlist == null)
        {
           throw new PlaylistNotFoundException("No Active Playlist");
        }
        
        List<String> songIDsInPlaylist = playlist.getSongIDs();

        if(songIDsInPlaylist.size() < 1)
        {
            throw new PlaylistEmptyException("Playlist is empty");
        }
        int currentIndex = playlist.getIdx();
        int playlistSize = songIDsInPlaylist.size();
     
        if(songId.equals("BACK"))
        {
           
            currentIndex = (currentIndex == 0) ? playlistSize-1 : currentIndex-1; 
            songId = songIDsInPlaylist.get(currentIndex);
            playlist.setIdx(currentIndex);
        }
        else if(songId.equals("NEXT"))
        {
            
            currentIndex = (currentIndex == playlistSize-1) ? 0 : currentIndex+1; 
            songId = songIDsInPlaylist.get(currentIndex);
            playlist.setIdx(currentIndex);
        }
        else if(songIDsInPlaylist.contains(songId)){
            //check if song is part of active playlist
            currentIndex = songIDsInPlaylist.indexOf(songId);
            playlist.setIdx(currentIndex);
        }
        else 
        {
            throw new SongNotFoundException("Given song id is not a part of the active playlist");
            //Error Message if above Song ID is not part of a current active playlist.
        }
    
        play(songId);
        playlistRepository.save(playlist);
        return false;
    }


    @Override
    public boolean play(String songID) throws Exception {
        // TODO Auto-generated method stub
        Optional<Song> song = songRepository.findById(songID);
        Song s = song.get();

       // System.out.println("Debug"+ s);
    
        //Play the song:
        System.out.println("Current Song Playing");
        System.out.println("Song - "+s.getName());
        System.out.println("Album - "+s.getAlbum());
        List<String> artists = s.getArtists();
        StringBuilder artisBuilder = new StringBuilder();
        int i = 0 ;
        for(String artist : artists)
        {
            artisBuilder.append(artist);
            if(i!= artists.size()-1)
            {
                artisBuilder.append(",");
            }
            i++;

        }

        System.out.println("Artists - "+artisBuilder);
        return true;
    }

    public void printPlayList(Playlist playlist)
    {
        System.out.println("Playlist ID - "+playlist.getId());
        System.out.println("Playlist Name - "+playlist.getName());
        List<String> updatedIDs = playlist.getSongIDs();
        StringBuilder finalSongIDs = new StringBuilder();
       for(int i = 0 ; i < updatedIDs.size() ; i++)
       {
        if(i != updatedIDs.size()-1)
        finalSongIDs.append(updatedIDs.get(i)+" ");
        else
        finalSongIDs.append(updatedIDs.get(i));
       }
       System.out.println("Song IDs - "+finalSongIDs);
    }


    


    
}
