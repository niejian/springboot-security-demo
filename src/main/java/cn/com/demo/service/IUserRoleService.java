package cn.com.demo.service;

import cn.com.demo.dao.yun.user.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niejian
 * @since 2018-07-24
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> getRoleList(String userCode) ;

}
