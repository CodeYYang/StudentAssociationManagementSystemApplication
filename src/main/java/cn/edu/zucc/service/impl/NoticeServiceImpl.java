package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Notice;
import cn.edu.zucc.entity.vo.NoticeAssVo;
import cn.edu.zucc.mapper.NoticeMapper;
import cn.edu.zucc.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 返回社团的所有公告
     * @param assId
     * @return
     */
    @Override
    public List<NoticeAssVo> searchAllNotice(String assId){
        return noticeMapper.searchAllNotice(assId);
    }
}
