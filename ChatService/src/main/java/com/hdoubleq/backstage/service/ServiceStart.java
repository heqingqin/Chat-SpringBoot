package com.hdoubleq.backstage.service;

import com.hdoubleq.backstage.utils.CloseUtils;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.list.UserList;
import com.hdoubleq.service.impl.List.OnLineUserList;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hdoubleq
 * @Date 2020/11/1-20:54
 */
public class ServiceStart {
    private static boolean isrun=false;
    ServerSocket server;
    /*
    　写入时复制（CopyOnWrite，简称COW）思想是计算机程序设计领域中的一种优化策略。
    其核心思想是，如果有多个调用者（Callers）同时要求相同的资源（如内存或者是磁盘上
    的数据存储），他们会共同获取相同的指针指向相同的资源，直到某个调用者视图修改资源
    内容时，系统才会真正复制一份专用副本（private copy）给该调用者，而其他调用者所见
    到的最初的资源仍然保持不变。这过程对其他的调用者都是透明的（transparently）。此做
    法主要的优点是如果调用者没有修改资源，就不会有副本（private copy）被创建，因此多个调
    用者只是读取操作时可以共享同一份资源。
     */
    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Channel>();
    static int clienttotal=0;
    public void Chat_server() throws Exception{
        System.out.println("-----服务器正在启动-----");

        //	1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(8888);
//	 CreateFile.Creatfile();
        //	2、阻塞式等待连接 accept
        System.out.println(isrun);
        while(isrun) {
            System.out.println(isrun);
            Socket client = server.accept();
            Channel c =new Channel(client);
//            对请求连接的用户进行验证是否是已注册用户
            if(!Channel.isrogin && Channel.ret.length==2) {
                System.out.println("第"+(++clienttotal)+"个客户端创建连接");
                System.out.println("当前客户端数："+clienttotal);
                all.add(c);
                try {
//                    将连接到的用户添加到在线列表
                    OnLineUserList onLineUserList = new OnLineUserList();
                    onLineUserList.setList(c);
                }catch(NullPointerException e) {

                }
                new Thread(c).start();
            }else Channel.isrogin = false;
        }
    }

    public static void getStartService(){
        isrun=true;
    }
    public static boolean getIsStart(){
        return isrun;
    }
    public static CopyOnWriteArrayList<Channel> getAll() {
        return all;
    }



}


