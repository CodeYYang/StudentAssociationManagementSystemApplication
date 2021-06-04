package cn.edu.zucc.service;

import cn.edu.zucc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
public interface UserService extends IService<User> {
    /**
     * 根据电话查询用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

}
