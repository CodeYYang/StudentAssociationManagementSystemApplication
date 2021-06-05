package cn.edu.zucc.service;

import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.vo.AssociationExt;
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
public interface AssociationService extends IService<Association> {
    /**
     * 根据社团名称查询社团
     * @param assName
     * @return
     */
    Association getAssociationByName(String assName);

    /**
     * 查询社团所有成员信息
     * @param assId
     * @return
     */
    Page<AssociationExt> GetAllAssociationMember(Integer current, Integer size, String assId);
}
