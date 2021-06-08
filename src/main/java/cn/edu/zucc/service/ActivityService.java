package cn.edu.zucc.service;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.vo.ActivityAssociationVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface ActivityService extends IService<Activity> {

    /**
     * 根据活动名称发现活动
     * @param activityName
     * @return
     */
    Activity findActivityByName(String activityName);

    /**
     * 查询活动信息
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<ActivityAssociationVo> GetAllActivity(Integer current, Integer size, String query);
}
