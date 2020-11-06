package com.hdoubleq.dao;

import com.hdoubleq.bean.Friend;
import com.hdoubleq.bean.list.FriendList;
import com.hdoubleq.model.CustomerListModel;
import com.hdoubleq.utils.CloseUtils;
import com.hdoubleq.view.MainView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:52
 */
public class ReceiveFromService implements Runnable {
    private Socket client;
    private boolean isRunning;
    private DataInputStream dis;
    static ApplicationContext applicationContext;
    static CustomerListModel model;
    static{
        applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
    }
    ArrayList<Friend> musiclist= FriendList.getList();
    public void Receive(Socket client,boolean isregin) {
        this.client = client;
        this.isRunning = true;
        try {
            dis = new DataInputStream(client.getInputStream());
            if(isregin) {
                String regin = "";
                regin = dis.readUTF();
                System.out.println(regin);
                MainView.getTip().setText(regin);
                release();
            }
        } catch (IOException e) {
            System.out.println("1接收消息失败");
            release();
        }
    }

    //释放资源
    private void release() {
        this.isRunning = false;
        CloseUtils.close(dis,client);
    }

    @Override
    public void run() {
        while(isRunning) {
            //接收消息
            String msg = receive();
            System.out.println("M"+msg);
            String[] people = null;
            people =  msg.split(",&&,");
            if(!msg.equals("")) {
                //判断是否为登录
                if(people[0].equals("login")) {
                    //密码错误
                    MainView.getTip().setText(people[1]);
                    MainView.islogin = false;
                    System.out.println(people[1]);
                }else if(people[0].equals("regin")) {
                    //注册错误
                    if(people[1].equals("注册成功!")) {
                        MainView.getTip().setText(people[1]);
                        MainView.isregin = false;
                    }else {
                        MainView.getTip().setText(people[1]);
                        MainView.isregin = true;
                    }
                    System.out.println(people[1]);
                }else if(people[0].equals("欢迎你的到来")){
                    leadinlist("我",LinkServiceDao.acc);
                    for(int t=1;t<people.length;t++) {
                        if(!people[t].equals(LinkServiceDao.acc))
                            leadinlist(people[t],people[t]);
                    }
                    MainView.islogin = true;
                    MainView.getShowcontent().setText("---系统提示---\n  "+ MainView.getShowcontent().getText()+"  欢迎你的到来\n");
                    System.out.println("欢迎你的到来");
                }else {
                    System.out.println("msg:"+msg);
                    String temp="";
                    String[] s = msg.split(" ");
                    System.out.println("s"+s.length);
                    if(s[0].equals("---系统提示---\n") && s[s.length-1].equals("来了聊天室\n")) {
                        for(int i=1;i<s.length-1;i++) {
                            temp=temp+s[i];
                            System.out.println("temp1"+temp);
                        }
                        System.out.println(temp);
                        String[] split = temp.split("_-_");

                        leadinlist(split[1],split[0]);
                        model = applicationContext.getBean("customerListModel", CustomerListModel.class);
                        MainView.getTable().setModel(model);
                    }
                    if(s[0].equals("---系统提示---\n") && s[s.length-1].equals("离开大家庭...\n")) {
                        for(int i=1;i<s.length-1;i++) {
                            temp=temp+s[i];
                        }
                        for(int i=0;i<musiclist.size();i++) {
                            if(temp.equals(musiclist.get(i).getAcc()))
                                musiclist.remove(i);
                            model = applicationContext.getBean("customerListModel", CustomerListModel.class);
                            MainView.getTable().setModel(model);
                        }
                    }
                    if (msg.contains("_-_")) {
                        String[] s1 = msg.split("_-_");
                        String[] s2 = s1[1].split(" ");
                        msg=s1[0]+"("+s2[0]+")"+s2[1];
                        System.out.println(msg);
                    }
                    MainView.getShowcontent().setText(MainView.getShowcontent().getText()+msg);
                }
            }
        }
    }


    private void leadinlist( String alias,String acc) {
        Friend f = new Friend();
        f.setAcc(acc);
        f.setAlias(alias);
        musiclist.add(f);
    }

    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("2接收消息失败");
            release();
        }
        return msg;
    }

}
