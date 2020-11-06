package com.hdoubleq.controller;

import com.hdoubleq.backstage.service.Channel;
import com.hdoubleq.bean.Identity;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.UserMapper;
import com.hdoubleq.mapper.list.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/10/31-8:29
 */
@Controller
public class UserController {


     @Autowired
      UserMapper userMapper;

    @GetMapping("/users")
    public String list(Model model){
        List<User> all = userMapper.findAllUser();
        UserList.setList(all);
        System.out.println(all);
        model.addAttribute("users",all);
        return "user/list";
    }
    @GetMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "login";
    }
    @GetMapping("/user")
    public String toAddPage(Model model){
        Collection<String> Who = userMapper.findUserWho();
        List<Identity> whos = new ArrayList<>();
        for (int i=1;i<=Who.size();i++){
            whos.add(new Identity(i,((List<String>) Who).get(i-1)));
        }
        System.out.println(whos);
        model.addAttribute("whos",whos);
        return "user/add";
    }

    @PostMapping("/user")
    public String adduser(User user){
        System.out.println(user);
        userMapper.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        User user = userMapper.findById(id);
        model.addAttribute("user",user);
        Collection<String> Who = userMapper.findUserWho();
        List<Identity> whos = new ArrayList<>();
        for (int i=1;i<=Who.size();i++){
            whos.add(new Identity(i,((List<String>) Who).get(i-1)));
        }
        model.addAttribute("whos",whos);
        return "user/add";

    }

    @PutMapping("/user")
    public String updateUser(User user){
        System.out.println(user);
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String deletePage(@PathVariable("id") Integer id){
        String acc = userMapper.findById(id).getAcc();
        userMapper.deleteByAcc(id);
        Channel.Dele(acc);
        return "redirect:/users";
    }

    @GetMapping("/user/search")
    public String Search(@PathVariable("search") String search){
        System.out.println(search);
        return null;
    }


}


