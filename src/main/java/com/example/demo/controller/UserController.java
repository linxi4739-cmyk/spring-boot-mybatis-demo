package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 告诉 Spring 这是一个接口类
@RequestMapping("/test") // 浏览器访问的前缀
@CrossOrigin(origins = "*") // 加上这一行，允许所有来源访问
public class UserController {

    @Autowired // 自动连接上面的 UserService
    private UserService userService;

    @GetMapping("/hello") // 完整路径：localhost:8080/test/hello?id=1
    public User sayHello(@RequestParam Integer id) {
        return userService.findUserById(id);
    }

    // 访问地址：http://localhost:8080/test/add?name=张三
    @GetMapping("/add")
    public String addUser(@RequestParam String name) {
        int result = userService.addUser(name);
        if (result > 0) {
            return "用户 " + name + " 添加成功！";
        } else {
            return "添加失败，请检查数据库连接。";
        }
    }
    // 访问路径：http://localhost:8080/test/all
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Integer id) {
        int result = userService.deleteUser(id);
        return result > 0 ? "删除成功！" : "删除失败，ID 不存在。";
    }


}