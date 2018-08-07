package cn.com.demo.config;/**
 * Created by niejian on 2018/7/30.
 */

import cn.com.demo.bean.JWTUser;
import cn.com.demo.dao.yun.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author niejian
 * @date 2018/7/30
 */

public class JWTUserFactory {

    private JWTUserFactory() {
    }

    public static JWTUser create(User user, List<String> authorities) {
        return new JWTUser(
                user.getId(),
                user.getUserName(),
                new BCryptPasswordEncoder().encode( user.getUserPwd()),
                //user.(),
                mapToGrantedAuthorities(authorities)
                //user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
