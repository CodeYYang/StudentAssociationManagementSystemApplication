package cn.edu.zucc.mapper;

import cn.edu.zucc.entity.Association;
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
public interface AssociationMapper extends BaseMapper<Association> {

    /**
     * 查询社团所有成员信息
     * @param assId
     * @return
     */
    List<AssociationExt> GetAllAssociationMember(Page<AssociationExt> page, String assId);
}
