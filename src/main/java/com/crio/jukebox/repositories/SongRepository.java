package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{

    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository()
    {
        songMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String,Song> songMap)
    {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }
    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if(entity.getId() == null)
        {
            ++autoIncrement;
            entity.setId(autoIncrement+"");
            songMap.put(entity.getId(), entity);
            return entity;
        }

        songMap.put((entity.getId()), entity);
        return null;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        boolean exists = false;
        if(songMap.containsKey(id))
        {
            exists = true;
        }
        else
        {
            exists = false;
        }
        return exists;
    }

    @Override
    public void delete(Song entity) {
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
    
}
