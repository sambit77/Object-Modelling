package com.crio.jukebox.exceptions;

public class PlaylistEmptyException extends RuntimeException{
    public PlaylistEmptyException()
    {
        super();
    }

    public PlaylistEmptyException(String msg)
    {
        super(msg);
    }
    
}
