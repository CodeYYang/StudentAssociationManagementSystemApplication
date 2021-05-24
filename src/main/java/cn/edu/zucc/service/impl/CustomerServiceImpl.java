package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Customer;
import cn.edu.zucc.mapper.CustomerMapper;
import cn.edu.zucc.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-21
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;


    @Override
    public Customer findCustomerByEmail(String email) {
        return customerMapper.findCustomerByEmail(email);
    }
}
