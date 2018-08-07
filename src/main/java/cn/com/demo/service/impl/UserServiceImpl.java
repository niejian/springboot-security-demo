package cn.com.demo.service.impl;

import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.dao.yun.user.entity.UserRole;
import cn.com.demo.dao.yun.user.mapper.UserMapper;
import cn.com.demo.service.IUserRoleService;
import cn.com.demo.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niejian
 * @since 2018-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public User getUserByUserCode(String userName) {
        EntityWrapper ew = new EntityWrapper();
        ew.setEntity(new User());
        User user = null;

        ew.eq("user_code", userName);
        List<User> userList = selectList(ew);
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        }
        return user;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTestTransc() {
        User user = new User();
        user.setId(System.currentTimeMillis() + "");
        user.setUserCode("80468295");
        user.setUserPwd("12345");
        user.setUserName("test");
        insert(user);

        UserRole userRole = new UserRole();
        userRole.setId(System.currentTimeMillis() + "");
        userRole.setRoleCode("test");
        userRole.setRoleName("测试");
        userRole.setUserCode("80468295");
        userRoleService.insert(userRole);
        //throw new RuntimeException("运行时异常");
    }
}
