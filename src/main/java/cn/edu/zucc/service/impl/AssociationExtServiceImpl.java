package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.mapper.ActivityMapper;
import cn.edu.zucc.mapper.AssociationExtMapper;
import cn.edu.zucc.service.ActivityService;
import cn.edu.zucc.service.AssociationExtService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@Service
public class AssociationExtServiceImpl extends ServiceImpl<AssociationExtMapper, AssociationExt> implements AssociationExtService {
}
