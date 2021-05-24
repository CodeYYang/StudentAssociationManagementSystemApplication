package cn.edu.zucc.mapper;

import cn.edu.zucc.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-21
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 通过手机号查询用户
     */
    Customer findCustomerByEmail(String email);
}
