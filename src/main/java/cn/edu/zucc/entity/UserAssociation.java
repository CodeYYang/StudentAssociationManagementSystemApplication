package cn.edu.zucc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("user_association")
@ApiModel(value="UserAssociation对象", description="")
public class UserAssociation implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("user_id")
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("ass_id")
    private Long assId;

    @TableField("user_ass_role")
    private String userAssRole;

    @TableField("user_ass_status")
    private String userAssStatus;


}
