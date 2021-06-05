package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.mapper.AssociationMapper;
import cn.edu.zucc.mapper.UserMapper;
import cn.edu.zucc.service.AssociationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class AssociationServiceImpl extends ServiceImpl<AssociationMapper, Association> implements AssociationService {

    @Resource
    private AssociationMapper associationMapper;
    @Override
    public Association getAssociationByName(String assName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(assName != null){
            queryWrapper.eq("ass_name",assName);
        }
        return associationMapper.selectOne(queryWrapper);
    }

    @Override
    public List<AssociationExt> GetAllAssociationMember(String assId) {
        return associationMapper.GetAllAssociationMember(assId);
    }
}
