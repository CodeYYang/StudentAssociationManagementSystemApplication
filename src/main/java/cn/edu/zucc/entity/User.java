package cn.edu.zucc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    @TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("user_id")
      private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_pwd")
    private String userPwd;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_credit")
    private BigDecimal userCredit;

    @TableField("user_role")
    private String userRole;

    @TableField("user_create_time")
    private Date userCreateTime;


}
