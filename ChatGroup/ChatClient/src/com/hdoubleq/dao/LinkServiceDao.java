package com.hdoubleq.dao;

import com.hdoubleq.model.CustomerListModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.Socket;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:50
 */
public class LinkServiceDao {
    static String acc="";
    static ApplicationContext applicationContext;
    static{
        applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
    }
    public void linkService(String acc,String uname,String upwd,boolean isre) throws Exception {
        this.acc=acc;
        //1、建立连接: 使用Socket创建客户端 +服务的地址和端口
        Socket client =new Socket("localhost",8888);
        //2、客户端发送消息
        //判断是否为注册账户
        if(isre) {
            ReceiveFromService receiveFromService=applicationContext.getBean("receiveFromService",ReceiveFromService.class);
            SendToSercice sendToSercice = applicationContext.getBean("sendToSercice", SendToSercice.class);
            sendToSercice.Send(client,"reacc="+acc+"&"+"reuname="+uname+"&"+"reupwd="+upwd,true);
            receiveFromService.Receive(client,true);

        }else {
            SendToSercice sendToSercice = applicationContext.getBean("sendToSercice", SendToSercice.class);
            sendToSercice.Send(client,"acc="+acc+"&"+"uname="+uname+"&"+"upwd="+upwd,false);
            ReceiveFromService receiveFromService=applicationContext.getBean("receiveFromService",ReceiveFromService.class);
            receiveFromService.Receive(client,false);
            new Thread(sendToSercice).start();
            new Thread(receiveFromService).start();
        }
    }

}


