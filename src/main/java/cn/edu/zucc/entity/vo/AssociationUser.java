package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Association;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class AssociationUser extends Association {
    @TableField("user_ass_role")
    private String userAssRole;
    @TableField("user_ass_status")
    private String userAssStatus;
}
