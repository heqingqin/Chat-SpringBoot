package com.hdoubleq.bean;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/3-8:42
 */
public class Identity {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Identity() {
    }


    public Identity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
