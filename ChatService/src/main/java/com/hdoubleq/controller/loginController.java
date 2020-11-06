package com.hdoubleq.controller;


import com.hdoubleq.backstage.service.ServiceStart;
import com.hdoubleq.bean.OnLineUser;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.UserMapper;
import com.hdoubleq.mapper.list.UserList;
import com.hdoubleq.service.OnLineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdoubleq
 * @Date 2020/10/30-21:44
 */
@Controller
public class loginController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OnLineUserService onLineUserService;
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        User user=null;
        user = userMapper.findByAcc(username);
        String[] select = request.getParameterValues("select");
        String login = null;
//            用户验证
        if(user!=null&&user.getPwd().equals(password)&&user.getWho().equals("管理员")){
//            服务器启动
            if (!ServiceStart.getIsStart()){
                ServiceStart.getStartService();
                new Thread(new RunServer()).start();
                System.out.println("服务器正在启动");
            }else {
                System.out.println("服务器已经启动");
            }
            UserList.setList(userMapper.findAllUser());
            Collection<OnLineUser> all = onLineUserService.getAllOnLineUser();
            request.getSession().setAttribute("loginUser",user.getName());
            request.getSession().setAttribute("onLineUsers",all);

            //        使用cookie来对账号密码进行保存
            Cookie cookie;
            if(select!=null){
                login=username+"#"+password;
                cookie = new Cookie("Chatlogin",login);
            }else {
                cookie = new Cookie("Chatlogin",login);
            }
//        保存时间为1天，如果设置保存时间为非正就无法保存
            cookie.setMaxAge(60*60*24);
//        保存路径不设置将无法取出
            cookie.setPath("/");
//            将cookie添加进去
            response.addCookie(cookie);

            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }

    }
}



//开启服务器
class RunServer implements Runnable {
    @Override
    public void run() {
        try {
            new ServiceStart().Chat_server();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}