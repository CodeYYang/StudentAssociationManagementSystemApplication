package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.User;
import cn.edu.zucc.mapper.UserMapper;
import cn.edu.zucc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据电话查询用户
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(phone != null){
            queryWrapper.eq("user_phone",phone);
        }
        return userMapper.selectOne(queryWrapper);
    }


}
