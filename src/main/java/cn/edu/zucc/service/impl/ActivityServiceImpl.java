package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityAssociationVo;
import cn.edu.zucc.mapper.ActivityMapper;
import cn.edu.zucc.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;
    /**
     * 根据活动名称发现活动
     * @param activityName
     * @return
     */
    @Override
    public Activity findActivityByName(String activityName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_name",activityName);
        return activityMapper.selectOne(queryWrapper);
    }

    /**
     * 查询活动信息
     * @param current
     * @param size
     * @param query
     * @return
     */
    @Override
    public Page<ActivityAssociationVo> GetAllActivity(Integer current, Integer size, String query) {
        Page<ActivityAssociationVo> page = new Page<>(current,size);
        List<ActivityAssociationVo> list = activityMapper.GetAllActivity(page,query);
        page.setRecords(list);
        return page;
    }

    /**
     * 查看指定活动未审核的成员
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @Override
    public IPage<User> searchActivityMemberNotStatus(Integer current, Integer size, String query, String activityId) {
        Page<User> page = new Page<>();
        IPage<User> iPage = activityMapper.searchActivityMemberNotStatus(page,activityId,query);
        return iPage;
    }
}
