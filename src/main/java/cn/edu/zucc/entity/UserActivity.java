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
    @TableName("user_activity")
@ApiModel(value="UserActivity对象", description="")
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
      @TableField("user_id")
      private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("activity_id")
    private Long activityId;

    @TableField("user_activity_status")
    private String userActivityStatus;


}
