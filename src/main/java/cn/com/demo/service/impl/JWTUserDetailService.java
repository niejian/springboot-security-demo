package cn.com.demo.service.impl;/**
 * Created by niejian on 2018/7/30.
 */

import cn.com.demo.config.JWTUserFactory;
import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.dao.yun.user.entity.UserRole;
import cn.com.demo.service.IUserRoleService;
import cn.com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niejian
 * @date 2018/7/30
 */

@Service
public class JWTUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserCode(username);
        Assert.isTrue(null != user, "用户不存在");
        //查询该用户的对应权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> roleList = this.userRoleService.getRoleList(username);
        List<String> roleCodeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleList)) {
//            roleList.forEach(userRole -> authorities.add(
//                    new SimpleGrantedAuthority(userRole.getRoleCode())
//            ));
            roleList.forEach(userRole -> roleCodeList.add(
                    userRole.getRoleCode()
            ));
        }

        if (null != user) {
            return JWTUserFactory.create(user, roleCodeList);
        }

        return null;
    }
}
