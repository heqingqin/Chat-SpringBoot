package com.hdoubleq.bean;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:31
 */
public class Friend {
    private String acc;
    private String alias; //别名

    public Friend() {
    }

    public Friend(String acc, String alias) {
        this.acc = acc;
        this.alias = alias;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
