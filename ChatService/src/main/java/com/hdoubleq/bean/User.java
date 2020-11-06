package com.hdoubleq.bean;


/**
 * @author hdoubleq
 * @Date 2020/11/2-19:24
 */
public class User {
    private Integer id;
    private String acc;
    private String name;
    private String pwd;
    private String who;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", acc='" + acc + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", who='" + who + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, String acc, String name, String pwd, String who) {
        this.id = id;
        this.acc = acc;
        this.name = name;
        this.pwd = pwd;
        this.who = who;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
