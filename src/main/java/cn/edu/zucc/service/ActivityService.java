package cn.edu.zucc.service;

import cn.edu.zucc.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
