package cn.com.demo.service.impl;

import cn.com.demo.dao.yun.user.entity.UserRole;
import cn.com.demo.dao.yun.user.mapper.UserRoleMapper;
import cn.com.demo.service.IUserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<UserRole> getRoleList(String userCode)  {
        EntityWrapper ew = new EntityWrapper();
        ew.setEntity(new UserRole());
        ew.eq("user_code", userCode);
        List<UserRole> roleList = selectList(ew);
        return roleList;
    }
}
