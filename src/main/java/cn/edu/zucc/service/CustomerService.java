package cn.edu.zucc.service;

import cn.edu.zucc.entity.Customer;
import cn.edu.zucc.mapper.CustomerMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-21
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 根据邮箱查找用户
     * @param email
     * return
     */
    Customer findCustomerByEmail(String email);

}
