package com.hdoubleq.backstage.service;

import com.hdoubleq.backstage.dao.LoginDao;
import com.hdoubleq.backstage.utils.CloseUtils;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.list.UserList;
import com.hdoubleq.service.impl.List.OnLineUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hdoubleq
 * @Date 2020/11/1-20:55
 */

//一个客户代表一个Channel

/**
 * 进行身份验证和注册
 */
public class Channel implements Runnable {
    private boolean pwderror=true;
    static boolean isrogin=false;
    private Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning;
    private String LoginorRegin;
    static ArrayList<String> intok = new ArrayList<String>();
    public String acc;
    static String[] ret;
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
    public Channel(Socket client) {
        this.client = client;
        try {
            //接收
            dis = new DataInputStream(client.getInputStream());
            this.LoginorRegin = receive();
            //身份验证
            ret = LoginDao.loginDeal(LoginorRegin).split(" ");
            this.acc = ret[0];
            dos = new DataOutputStream(client.getOutputStream());
            if(ret[0].equals("注册失败!")||ret[0].equals("注册成功!")) {  //注册信息返回
                isrogin=true;
                System.out.println(ret[0]);
                this.send("regin"+",&&,"+ret[0]);
                return;
            }
            //发送
            if(ret.length==2) {     //登录信息返回
                String people="欢迎你的到来";
                for(Channel other: ServiceStart.getAll()) {
                    people=people+",&&,"+other.acc;
                }
                this.send(people);
            }else {
//                登录失败
                System.out.println(ret[0]);
                this.send("login"+",&&,"+ret[0]);
                isRunning = false;
                release(false);
                return;
            }
//            将有用户登录的信息发布给其他人
            sendOthers("---"+"系统提示"+"---\n "+getName(this.acc)+" 来了聊天室\n",true);
            isRunning =true;
        } catch (Exception e) {
            System.out.println(this.acc+"连接服务器失败");
            release(false);
        }
    }
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
            System.out.println("发送"+this.acc +"的消息成功");
        } catch (IOException e) {
            System.out.println("发送"+this.acc +"的消息失败");
            release(true);
        }

    }

    private String receive() {
        String msg ="";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("接收"+this.acc +"的消息失败");
            release(true);
        }
        return msg;
    }

    //释放资源
    private void release(boolean pwderror) {
        this.isRunning = false;
        CloseUtils.close(dis,dos,client);
        //退出
        CopyOnWriteArrayList<Channel> all = ServiceStart.getAll();
        for (int i=0;i<all.size();i++){
            if(all.get(i).acc.equals(acc)) {
                OnLineUserList onLineUserList = new OnLineUserList();
                onLineUserList.getList().remove(i);
                ServiceStart.getAll().remove(all.get(i));
            }
            System.out.println(acc+"已下线");
        }

        if(pwderror) {
            //            当用户离开后发送（登录失败不发送）
            sendOthers("---"+"系统提示"+"---\n "+this.acc+" 离开大家庭...\n",true);
            --ServiceStart.clienttotal;
        }
        System.out.println("当前客户端数："+ServiceStart.clienttotal);
    }

    /**
     * 群聊：获取自己的消息，发给其他人
     *  私聊: 约定数据格式: @xxx:msg
     *  intok:禁言列表
     * @param msg
     */
    private void sendOthers(String msg, boolean isSys) {
        for(String intna:intok) {
            if(intna.equals(this.acc)) {
                return;
            }
        }
//        判断是否为私聊
        boolean isPrivate = msg.startsWith("@");
        if(isPrivate) { //私聊
            int idx =msg.indexOf(":");
            //获取目标和数据
            String targetname = msg.substring(1,idx);
            msg = msg.substring(idx+1);
            for(Channel other: ServiceStart.getAll()) {
                if(other.acc.equals(targetname)) {//私聊目标
                    other.send("---"+this.acc+"私聊您  "+df.format(new Date())+"---\n  "+msg+"\n");
                    break;
                }
            }
        }else {
//            如果不是私聊，则发送广播
            for(Channel other: ServiceStart.getAll()) {
                if(other==this) { //自己
                    continue;
                }
                if(!isSys) {
                    other.send("---"+this.acc+" "+df.format(new Date())+"---\n  "+msg+"\n");//群聊消息
                    System.out.println("---"+this.acc+df.format(new Date())+"---\n  "+msg+"\n");
                }else {
                    other.send(msg); //系统消息
                }

            }
        }
    }

    @Override
    public void run() {
        //发送群聊消息
        while(isRunning) {
            String msg = receive();
            if(!msg.equals("")) {
                System.out.println(msg);
                sendOthers(msg,false);
            }
        }

    }

    //禁言操作
    public static void isNoSay(String Acc,boolean isno) {
        for(Channel other: ServiceStart.getAll()) {
            if(Acc.equals(other.acc)) {
                if(isno) {
                    //禁言
                    System.out.println("禁言"+Acc);
                    intok.add(Acc);
                    for(Channel info: ServiceStart.getAll()) {
                        if(info==other) { //自己
                            info.send("---"+"系统提示"+"---\n "+"您"+" 已经被禁言了...\n"); //系统消息
                        }else {
                            info.send("---"+"系统提示"+"---\n "+other.acc+" 已经被禁言了...\n"); //系统消息
                        }
                    }
                }else {
                    //解除禁言
                    intok.remove(Acc);
                    for(Channel info: ServiceStart.getAll()) {
                        if(info==other) { //自己
                            info.send("---"+"系统提示"+"---\n "+"您"+" 已经被解除禁言了...\n"); //系统消息
                        }else {
                            info.send("---"+"系统提示"+"---\n "+other.acc+" 已经被解除禁言了...\n"); //系统消息
                        }
                    }
                }
            }
        }

    }

    public String getName(String acc){
        List<User> list = UserList.getList();
        for (User user:list){
            if (user.getAcc().equals(acc)){
                return acc+"_-_"+user.getName();
            }
        }
        return acc+"+";
    }

    //强制用户下线
    public static void DeleLine(String acc) {
        // 管理员要你下线
        Channel channel=null;
        for(Channel info: ServiceStart.getAll()) {
            if(info.acc.equals(acc)) { //自己
                channel=info;
                info.send("---"+"系统提示"+"---\n "+"您"+" 已经被踢出群聊了...\n"); //系统消息
                ServiceStart.getAll().remove(info);
            }else {
                info.send("---"+"系统提示"+"---\n "+acc+" 已经被踢出群聊了...\n"); //系统消息
            }
        }
        if (channel!=null){
            channel.isRunning = false;
            CloseUtils.close(channel.dis,channel.dos,channel.client);
        }

    }


    //删除用户
    public static void Dele(String acc) {
        for(Channel info: ServiceStart.getAll()) {
            if(info.acc.equals(acc)) {
                    info.send("---"+"系统提示"+"---\n "+"您"+" 已经被删除账号了...\n"); //系统消息
                    DeleLine(info.acc);
                }
            }
        }

    }