package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // 告诉 Spring 这是一个接口类
@RequestMapping("/test") // 浏览器访问的前缀
public class UserController {

    @Autowired // 自动连接上面的 UserService
    private UserService userService;

    @GetMapping("/hello") // 完整路径：localhost:8080/test/hello?id=1
    public User sayHello(@RequestParam Integer id) {
        return userService.findUserById(id);
    }
}