package com.wen.controller;

import com.wen.bean.TblUserRecord;
import com.wen.result.Permission;
import com.wen.result.Permissions;
import com.wen.result.R;
import com.wen.result.UserInfo;
import com.wen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    // 解决前端报错
    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public R login(String username, String password,HttpSession session){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username,password);
        // 将用户信息写入
        session.setAttribute("userRecord",tblUserRecord);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        R r = new R(200,"登录成功",tblUserRecord);
        System.out.println(r);
        return r;
    }

    @RequestMapping("/user/info")
    public R getInfo(HttpSession session){
        TblUserRecord userRecord = (TblUserRecord)session.getAttribute("userRecord");
        // 获取用户的功能信息
        String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        // 拼接需要返回的数据对象格式
        Permissions permissions = new Permissions();
        List<Permission> permissionsList = new ArrayList<>();
        for(String s:rolePrivileges){
            permissionsList.add(new Permission(s));
        }
        permissions.setPermissions(permissionsList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(),permissions);
        return new R(200,"ok",userInfo);
    }
}
