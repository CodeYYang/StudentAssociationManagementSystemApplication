package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.UserActivity;
import cn.edu.zucc.entity.UserAssociation;
import cn.edu.zucc.mapper.UserAssociationMapper;
import cn.edu.zucc.service.UserAssociationService;
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
public class UserAssociationServiceImpl extends ServiceImpl<UserAssociationMapper, UserAssociation> implements UserAssociationService {

    @Resource
    private UserAssociationMapper userAssociationMapper;

    /**
     * 根据社团id查找社长
     * @param assId
     * @return
     */
    @Override
    public UserAssociation getPresident(String assId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(assId != null){
            queryWrapper.eq("ass_id",assId);
            queryWrapper.eq("user_ass_role","社长");
        }
        return userAssociationMapper.selectOne(queryWrapper);
    }

    /**
     * 修改社长信息
     * @param assId
     * @param userId
     * @param status
     */
    @Override
    public void ModifyPresident(String assId, String userId, String status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ass_id",assId);
        queryWrapper.eq("user_id",userId);
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.setUserAssRole(status);
        userAssociationMapper.update(userAssociation,queryWrapper);
    }

    /**
     * 添加社团用户
     * @param assId
     * @param userId
     * @return
     */
    @Override
    public UserAssociation addUserAssociation(String assId, String userId) {
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.setAssId(Long.valueOf(assId));
        userAssociation.setUserId(Long.valueOf(userId));
        userAssociation.setUserAssStatus("审核通过");
        userAssociation.setUserAssRole("社员");
        userAssociationMapper.insert(userAssociation);
        return userAssociation;
    }

    /**
     * 查找社团中是否存在该成员
     * @param assId
     * @param userID
     * @return
     */
    @Override
    public UserAssociation searchUserAssociation(String assId, String userID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ass_id",assId);
        queryWrapper.eq("user_id",userID);
        UserAssociation userAssociation = userAssociationMapper.selectOne(queryWrapper);
        return userAssociation;
    }



}
