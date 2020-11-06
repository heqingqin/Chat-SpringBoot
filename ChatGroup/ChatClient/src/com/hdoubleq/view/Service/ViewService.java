package com.hdoubleq.view.Service;

import com.hdoubleq.bean.Friend;
import com.hdoubleq.bean.list.FriendList;
import com.hdoubleq.dao.LinkServiceDao;
import com.hdoubleq.model.CustomerListModel;
import com.hdoubleq.startMian;
import com.hdoubleq.view.MainView;
import com.hdoubleq.view.MainView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:12
 */
public class ViewService {
    static ApplicationContext applicationContext;
    static MainView mainView;
    static LinkServiceDao LinkServiceDao;
    static int Id=-1;
    static{
        applicationContext= new ClassPathXmlApplicationContext("spring-config.xml");
        mainView = applicationContext.getBean("mainView", MainView.class);
        LinkServiceDao = applicationContext.getBean("linkServiceDao", LinkServiceDao.class);
    }
    //退出
    public static void exit() {
        System.exit(0);
    }
    public static void MinWindow() {
        // 窗口隐藏
        startMian.mainView.setExtendedState(JFrame.ICONIFIED);
    }


    public static void PanelFullScree(boolean IS) {
        if(IS) {
            startMian.mainView.setExtendedState(mainView.MAXIMIZED_BOTH);
            startMian.mainView.getBigorsmall().setIcon(new ImageIcon(MainView.class.getResource("/images/changesmall.png")));
        }else {
            startMian.mainView.setExtendedState(mainView.NORMAL);
            startMian.mainView.getBigorsmall().setIcon(new ImageIcon(MainView.class.getResource("/images/changebig.png")));
        }

    }


    public static void Login(String acc, String pwd) throws Exception {
        LinkServiceDao.linkService(acc,null,pwd,false);
    }

    public static void Regin(String acc,String name, String pwd) throws Exception {
        // 注册账户

        LinkServiceDao.linkService(acc,name,pwd,true);
        String regin = name+" "+pwd;
        System.out.println(regin);
    }

    public static void Search(String text) {
        ArrayList<Friend> list = FriendList.getList();
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getAcc().equals(text)) {
                for(int t=0;t<i;t++) {
                    Collections.swap(list,i,t);
                }

                mainView.getTable().setModel(applicationContext.getBean("customerListModel", CustomerListModel.class));
                mainView.getTable().setRowSelectionInterval(0, 0);
                mainView.getTable().setSelectionBackground(Color.yellow);
                mainView.message="";
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "查不到"+text+"这个人！","错误",JOptionPane.WARNING_MESSAGE);
    }
    public static void ClickList() {
        int id = mainView.getTable().getSelectedRow();
        System.out.print("id="+id);
        if(Id==id) {
            mainView.message="";
            mainView.getTable().setSelectionBackground(Color.WHITE);
            Id=-1;
        }else {
            Id=id;
            mainView.getTable().setRowSelectionInterval(Id, Id);
            mainView.getTable().setSelectionBackground(Color.green);
            String string = mainView.getTable().getValueAt(Id,0).toString();
            String name = string.substring(string.indexOf("(")+1,string.indexOf(")"));
            mainView.message = "@"+name+":";
            System.out.println(mainView.message);
        }
    }

}
