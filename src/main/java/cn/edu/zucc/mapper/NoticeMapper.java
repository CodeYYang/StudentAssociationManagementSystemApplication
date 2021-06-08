package cn.edu.zucc.mapper;

import cn.edu.zucc.entity.Notice;
import cn.edu.zucc.entity.vo.NoticeAssVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 返回社团的所有公告
     * @param assId
     * @return
     */
    List<NoticeAssVo> searchAllNotice(String assId);
}
