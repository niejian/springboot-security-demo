package cn.com.demo.controller;/**
 * Created by niejian on 2018/7/25.
 */

import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.service.IUserService;
import cn.com.demo.service.impl.JWTUserDetailService;
import cn.com.demo.util.JwtTokenUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niejian
 * @date 2018/7/25
 */

@Controller
public class loginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUserDetailService userDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private Logger logger = LoggerFactory.getLogger(loginController.class);

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody JSONObject json) {
        Map<String, Object> map = new HashMap<>();
        String userCode = json.getString("username");
        String pwd = json.getString("password");
        EntityWrapper ew = new EntityWrapper();
        ew.setEntity(new User());
        ew.eq("user_code", userCode)
                .andNew("user_pwd={0}", pwd);
        logger.info("------>登录验证输出语句：{}", ew.getSqlSegment());
        int count = this.userService.selectCount(ew);
        String msg = "登录失败";
        boolean isSuccess = false;
        if (count > 0) {
            msg = "登录成功";
            isSuccess = true;
            //将角色信息交由springsecurity管理
            //custUserDetailService.loadUserByUsername(userCode);
        }
        map.put("isSuccess", isSuccess);
        map.put("msg", msg);
        return map;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> auth(@RequestBody JSONObject json) {
        Map<String, Object> map = new HashMap<>();
        String userCode = json.getString("username");
        String pwd = json.getString("password");
        //
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userCode, pwd);
         final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailService.loadUserByUsername(userCode);
        final String token = jwtTokenUtil.generateToken(userDetails);
        map.put("token", token);
        return map;


    }

//    @GetMapping(value = "/login")
//    public String login() {
//        return "login";
//    }

}
