package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository{

    private final Map<String,Playlist> playListMap;
    private Integer autoIncrement = 0;

    public PlaylistRepository()
    {
        playListMap = new HashMap<String,Playlist>();
    }

    
    @Override
    public Playlist save(Playlist entity) {

        // TODO Auto-generated method stub
        if(entity.getId() == null)
        {
            ++autoIncrement;
            entity.setId(autoIncrement+"");
            playListMap.put(entity.getId(), entity);
            return entity;
        }
        playListMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        List<Playlist> allPlayList = new ArrayList<Playlist>();
        for(String m : playListMap.keySet())
        {
            allPlayList.add(playListMap.get(m));
        }
        return allPlayList;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub

        return Optional.ofNullable(playListMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Playlist getActivePlayList() {
        // TODO Auto-generated method stub
        List<Playlist> allPlayList = findAll();
        Playlist activePlaylist = null;
        for(Playlist p: allPlayList)
        {
            boolean status = p.getIsActive();
            if(status)
            {
                activePlaylist = p;
            }
        }
        return activePlaylist;
    }
    
}
