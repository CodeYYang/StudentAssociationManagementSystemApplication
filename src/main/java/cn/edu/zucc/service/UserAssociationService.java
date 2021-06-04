package cn.edu.zucc.service;

import cn.edu.zucc.entity.UserAssociation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
public interface UserAssociationService extends IService<UserAssociation> {

    /**
     * 根据社团id查找社长
     * @param assId
     * @return
     */
    UserAssociation getPresident(String assId);


    /**
     * 修改社长信息
     * @param assId
     * @param userId
     * @param status
     */
    void ModifyPresident(String assId,String userId,String status);

    /**
     * 添加社团成员信息
     * @param assId
     * @param userId
     * @return
     */
    UserAssociation addUserAssociation(String assId,String userId);

    /**
     * 查找社团中是否存在该成员
     * @param assId
     * @param userID
     * @return
     */
    UserAssociation searchUserAssociation(String assId,String userID);
}
