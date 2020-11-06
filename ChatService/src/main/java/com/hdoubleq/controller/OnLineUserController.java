package com.hdoubleq.controller;

import com.hdoubleq.bean.OnLineUser;
import com.hdoubleq.service.OnLineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author hdoubleq
 * @Date 2020/11/3-11:21
 */
@Controller
public class OnLineUserController {

    @Autowired
    OnLineUserService onLineUserService;
    @GetMapping("/onLineUser")
    public String list(Model model){
        Collection<OnLineUser> all = onLineUserService.getAllOnLineUser();
        System.out.println(all);
        model.addAttribute("onLineUsers",all);
        return "onLineUser";
    }
//    @GetMapping("/onLineOnLineUser")
//    public String toAddPage(Model model){
//        Collection<String> Who = onLineUserService.getOnLineUserWho();
//        List<Identity> whos = new ArrayList<>();
//        for (int i=1;i<=Who.size();i++){
//            whos.add(new Identity(i,((List<String>) Who).get(i-1)));
//        }
//        System.out.println(whos);
//        model.addAttribute("whos",whos);
//        return "OnLineUser/add";
//    }

//    @PostMapping("/onLineOnLineUser")
//    public String addOnLineUser(OnLineUser OnLineUser){
//        System.out.println(OnLineUser);
//        onLineUserService.saveOnLineUser(OnLineUser);
//        return "redirect:/OnLineUsers";
//    }
//    @GetMapping("/onLineOnLineUser/{id}")
//    public String toEditPage(@PathVariable("id") Integer id,Model model){
//        OnLineUser OnLineUser = onLineUserService.getById(id);
//        model.addAttribute("OnLineUser",OnLineUser);
//        Collection<String> Who = onLineUserService.getOnLineUserWho();
//        List<Identity> whos = new ArrayList<>();
//        for (int i=1;i<=Who.size();i++){
//            whos.add(new Identity(i,((List<String>) Who).get(i-1)));
//        }
//        model.addAttribute("whos",whos);
//        return "OnLineUser/add";
//
//    }
//
    //禁言，获取用户id进行修改状态
    @GetMapping("/onLineUser/{id}")
    public String updateOnLineUser(@PathVariable("id") Integer id){
        System.out.println(id);
        onLineUserService.say(id);
        return "redirect:/onLineUser";
    }

    //强制用户下线
    @DeleteMapping("/onLineUser/{id}")
    public String deletePage(@PathVariable("id") Integer id){
        onLineUserService.logoff(id);
        return "redirect:/onLineUser";
    }


}
