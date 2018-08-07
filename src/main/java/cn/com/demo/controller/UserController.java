package cn.com.demo.controller;/**
 * Created by niejian on 2018/7/25.
 */

import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author niejian
 * @date 2018/7/25
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping(value = "/getUser")
    @PreAuthorize("hasRole('admin')")
    public User getUser(HttpServletRequest request) {
        String userCode = request.getParameter("userCode");
        User user = this.userService.getUserByUserCode(userCode);
        return user;
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("12345");
        //$2a$10$1JTi68brocgq1R9HGULGUeYC9ZDmMKm3PPvmEjScDHdyNenJEQXGW
        System.out.println(pwd);
        boolean matches = encoder.matches("12345", pwd);
        System.out.println(matches);
    }

}
