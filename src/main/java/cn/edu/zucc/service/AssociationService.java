package cn.edu.zucc.service;

import cn.edu.zucc.entity.Association;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
