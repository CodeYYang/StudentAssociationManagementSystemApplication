package cn.edu.zucc.mapper;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityAssociationVo;
import cn.edu.zucc.entity.vo.AssociationExt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 查询活动信息
     * @param page
     * @param query
     * @return
     */
    List<ActivityAssociationVo>  GetAllActivity(Page<ActivityAssociationVo> page, String query);

    /**
     * 查询社团审核未通过成员
     * @param page
     * @param activityId
     * @param query
     * @return
     */
    IPage<User> searchActivityMemberNotStatus(Page<User> page, String activityId, String query);
}
