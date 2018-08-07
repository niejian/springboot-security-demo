package cn.com.demo.dao.oa.code.mapper;

import cn.com.demo.dao.oa.code.entity.OaNoCode;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author niejian
 * @since 2018-08-06
 */
public interface OaNoCodeMapper extends BaseMapper<OaNoCode> {
   OaNoCode getAll();
}
