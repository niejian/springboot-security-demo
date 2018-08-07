package cn.com.demo.test;/**
 * Created by niejian on 2018/8/6.
 */

import cn.com.demo.dao.oa.code.entity.OaNoCode;
import cn.com.demo.dao.oa.code.mapper.OaNoCodeMapper;
import cn.com.demo.dao.yun.user.entity.User;
import cn.com.demo.service.IOaNoCodeService;
import cn.com.demo.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author niejian
 * @date 2018/8/6
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMutiDataSource {

    @Autowired
    private IUserService userService;
    @Autowired
    private IOaNoCodeService oaNoCodeService;
    @Autowired
    private OaNoCodeMapper oaNoCodeMapper;

    @Test
    public void test1() {
        EntityWrapper ew = new EntityWrapper();
        ew.setEntity(new User());
        ew.eq("user_code", "A001");
        System.out.println(ew.getSqlSegment());
        System.out.println(userService.selectList(ew).toString());

        System.out.println("------------------------------>");
        ew = new EntityWrapper();
        ew.setEntity(new OaNoCode());
        ew.eq("codeid", "1");
        System.out.println(ew.getSqlSegment());
        System.out.println(oaNoCodeService.selectList(ew).toString());
        System.out.println("*************************");
        System.out.println(oaNoCodeMapper.getAll());

    }
}
