package cn.edu.zucc.mapper;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityUser;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.entity.vo.AssociationUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户查看已经加入的社团
     * @param page
     * @param userId
     * @param query
     * @return
     */

    IPage<AssociationUser> searchMyAssociation(Page<AssociationUser> page, String userId, String query);

    /**
     * 用户查看已经加入的活动
     * @param page
     * @param userId
     * @param query
     * @return
     */
    IPage<ActivityUser> searchMyActivity(Page<ActivityUser> page, String userId, String query);

    /**
     * 查看我管理的社团
     * @param page
     * @param userId
     * @param query
     * @return
     */
    IPage<Association> searchManagementAssociation(Page<Association> page, String userId, String query);

    /**
     * 查看我管理的社团成员
     * @param page
     * @param assId
     * @param query
     * @return
     */
    IPage<User> searchManagementAssociationMember(Page<User> page, String assId, String query);

    /**
     * 查看指定的社团待审核的成员
     * @param page
     * @param assId
     * @param query
     * @return
     */
    IPage<User> searchAssMemberWaitStatus(Page<User> page, String assId, String query);

    /**
     * 查看我管理的活动成员
     * @param page
     * @param activityId
     * @param query
     * @return
     */
    IPage<User> searchManagementActivityMember(Page<User> page, String activityId, String query);

    /**
     * 查看指定的活动待审核的成员
     * @param page
     * @param activityId
     * @param query
     * @return
     */
    IPage<User> searchActivityMemberWaitStatus(Page<User> page, String activityId, String query);
}
