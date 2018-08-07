package cn.com.demo.bean;/**
 * Created by niejian on 2018/7/25.
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 此 User 类不是我们的数据库里的用户类,是用来安全服务的
 * @author niejian
 * @date 2018/7/25
 */

public class AuthUser extends User {
    private String id;

    private String nickname;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param username
     * @param password
     * @param authorities
     */
    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
