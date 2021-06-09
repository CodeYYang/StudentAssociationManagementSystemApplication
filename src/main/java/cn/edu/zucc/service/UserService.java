package cn.edu.zucc.service;

import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityUser;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.entity.vo.AssociationUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 用户查看已经加入的社团
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    IPage<AssociationUser> searchMyAssociation(Integer current, Integer size, String query, String userId);

    /**
     * 用户查看已经加入的活动
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    IPage<ActivityUser> searchMyActivity(Integer current, Integer size, String query, String userId);
}
