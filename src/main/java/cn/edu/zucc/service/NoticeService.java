package cn.edu.zucc.service;

import cn.edu.zucc.entity.Notice;
import cn.edu.zucc.entity.vo.NoticeAssVo;
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
public interface NoticeService extends IService<Notice> {
    /**
     * 返回社团的所有公告
     * @param assId
     * @return
     */
    List<NoticeAssVo> searchAllNotice(String assId);

}
