package cn.com.demo.service.impl;

import cn.com.demo.bean.AuthUser;
import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.dao.yun.user.entity.UserRole;
import cn.com.demo.service.IUserRoleService;
import cn.com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 重写springsecurity的userDetailService
 * @author niejian
 * @date 2018/7/25
 */
@Service
public class CustUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByUserCode(s);
        Assert.isTrue(null != user, "用户不存在");
        //查询该用户的对应权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> roleList = this.userRoleService.getRoleList(s);
        if (!CollectionUtils.isEmpty(roleList)) {
            roleList.forEach(userRole -> authorities.add(
                    new SimpleGrantedAuthority(userRole.getRoleCode())
            ));
        }

        AuthUser authUser = new AuthUser(s, user.getUserPwd(), authorities);
        authUser.setId(user.getId());
        authUser.setNickname(user.getUserName());
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                new BCryptPasswordEncoder().encode(user.getUserPwd()), authorities);
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),
//                user.getUserPwd(), authorities);
    }
}
