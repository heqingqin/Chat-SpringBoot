package com.hdoubleq.bean;

import java.io.Serializable;

/**
 * @author hdoubleq
 * @Date 2020/11/1-16:06
 */
public class OnLineUser implements Serializable {
    private int id;
    private String acc;
    private String name;
    private String who;
    private String status="正常";  //在线状态

    @Override
    public String toString() {
        return "OnLineUser{" +
                "id='" + id + '\'' +
                ", acc='" + acc + '\'' +
                ", name='" + name + '\'' +
                ", who='" + who + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public OnLineUser() {
    }

    public OnLineUser(int id, String acc, String name, String who, String status) {
        this.id = id;
        this.acc = acc;
        this.name = name;
        this.who = who;
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
