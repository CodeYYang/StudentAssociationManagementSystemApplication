package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.UserAssociation;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@Data
public class AssociationExt extends Association {
    @TableField("user_name")
    private String userName;
    @TableField("user_phone")
    private String userPhone;
    @TableField("user_ass_role")
    private String userAssRole;
    @TableField("user_ass_status")
    private String userAssStatus;
}
