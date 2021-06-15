package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityUser;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.entity.vo.AssociationUser;
import cn.edu.zucc.mapper.UserMapper;
import cn.edu.zucc.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.security.PublicKey;
import java.util.List;

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
     * @return User
     */
    @Override
    public User getUserByPhone(String phone) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(phone != null){
            queryWrapper.eq("user_phone",phone);
        }
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 用户查看已经加入的社团
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @Override
    public IPage<AssociationUser> searchMyAssociation(Integer current, Integer size, String query, String userId) {
        Page<AssociationUser> page = new Page<>(current,size);
        IPage<AssociationUser> iPage = this.baseMapper.searchMyAssociation(page,userId,query);
        return iPage;
    }

    /**
     * 用户查看已经加入的活动
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @Override
    public IPage<ActivityUser> searchMyActivity(Integer current, Integer size, String query, String userId) {
        Page<ActivityUser> page = new Page<>(current,size);
        IPage<ActivityUser> iPage = this.baseMapper.searchMyActivity(page,userId,query);
        return iPage;
    }

    /**
     * 查看我管理的社团
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @Override
    public IPage<Association> searchManagementAssociation(Integer current, Integer size, String query, String userId) {
        Page<Association> page = new Page<>(current,size);
        IPage<Association> iPage = this.baseMapper.searchManagementAssociation(page,userId,query);
        return iPage;
    }

    /**
     * 查看我管理的社团成员
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @Override
    public IPage<User> searchManagementAssociationMember(Integer current, Integer size, String query, String assId) {
        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = this.baseMapper.searchManagementAssociationMember(page,assId,query);
        return iPage;
    }

    /**
     * 查看指定社团待审核的社团成员
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @Override
    public IPage<User> searchAssMemberWaitStatus(Integer current, Integer size, String query, String assId) {
        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = this.baseMapper.searchAssMemberWaitStatus(page,assId,query);
        return iPage;
    }

    /**
     * 查看指定社团审核未通过的社团成员
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @Override
    public IPage<User> searchAssMemberNotStatus(Integer current, Integer size, String query, String assId) {
        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = this.baseMapper.searchAssMemberNotStatus(page,assId,query);
        return iPage;
    }

    /**
     * 查看我管理的活动成员
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @Override
    public IPage<User> searchManagementActivityMember(Integer current, Integer size, String query, String activityId) {
        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = this.baseMapper.searchManagementActivityMember(page,activityId,query);
        return iPage;
    }

    /**
     * 查看指定活动待审核的成员
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @Override
    public IPage<User> searchActivityMemberWaitStatus(Integer current, Integer size, String query, String activityId) {
        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = this.baseMapper.searchActivityMemberWaitStatus(page,activityId,query);
        return iPage;
    }


}
