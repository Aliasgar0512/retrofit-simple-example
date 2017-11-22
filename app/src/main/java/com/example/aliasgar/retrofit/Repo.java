package com.example.aliasgar.retrofit;

import com.google.gson.annotations.Expose;

public class Repo
{
    @Expose
    private
    String id;
    String name;
    private int contributer;

    @Override
    public String toString() {
        return "Repo{" +
                "id='" + id + '\'' +
                ", contributer=" + contributer +
                '}';
    }
}
