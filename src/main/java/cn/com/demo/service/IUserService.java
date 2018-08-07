package cn.com.demo.service;

import cn.com.demo.dao.yun.user.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niejian
 * @since 2018-07-24
 */
public interface IUserService extends IService<User> {

    public User getUserByUserCode(String userName);

    @Transactional
    void addTestTransc();

}
