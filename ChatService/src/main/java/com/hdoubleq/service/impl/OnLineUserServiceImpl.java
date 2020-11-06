package com.hdoubleq.service.impl;

import com.hdoubleq.backstage.service.Channel;
import com.hdoubleq.bean.OnLineUser;
import com.hdoubleq.service.OnLineUserService;
import com.hdoubleq.service.impl.List.OnLineUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/3-11:56
 */
@Service
public class OnLineUserServiceImpl implements OnLineUserService {

    @Autowired
    OnLineUserList onLineUserList;

//    获取在线用户
    @Override
    public List<OnLineUser> getAllOnLineUser() {
        List<OnLineUser> list = onLineUserList.getList();
        System.out.println("添加所有");
        return list;
    }

    //根据id从在线用户列表中删除该用户
    @Override
    public void logoff(int id) {
        List<OnLineUser> list = onLineUserList.getList();
       for (int i=0;i<list.size();i++){
           if (list.get(i).getId()==id){
               //将该用户从线程管道中移除
               Channel.DeleLine(list.get(i).getAcc());
               list.remove(i);
           }
       }
    }
    //禁言操作
    @Override
    public boolean say(int id) {
        List<OnLineUser> list = onLineUserList.getList();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getId()==id){
                if (list.get(i).getStatus().equals("正常")){
                    Channel.isNoSay(list.get(i).getAcc(),true);
                    list.get(i).setStatus("禁言");
                    return true;
                }else{
                    Channel.isNoSay(list.get(i).getAcc(),false);
                    list.get(i).setStatus("正常");
                    return true;
                }
            }
        }
        return false;
    }


}
