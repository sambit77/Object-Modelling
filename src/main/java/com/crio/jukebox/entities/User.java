package com.crio.jukebox.entities;

public class User extends BaseEntity{
    private final String name;

    public User(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }

}
