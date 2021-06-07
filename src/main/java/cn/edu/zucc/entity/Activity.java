package cn.edu.zucc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableName("activity")
@ApiModel(value="Activity对象", description="")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("activity_id")
      private Long activityId;

    @TableField("ass_id")
    private Long assId;

    @TableField("activity_name")
    private String activityName;

    @TableField("activity_brief")
    private String activityBrief;

    @TableField("activity_type")
    private String activityType;

    @TableField("activity_leave")
    private Boolean activityLeave;

    @TableField("activity_status")
    private String activityStatus;

    @TableField("activity_people")
    private Integer activityPeople;

    @TableField("activity_credit")
    private BigDecimal activityCredit;

    @TableField("activity_score")
    private BigDecimal activityScore;

    @TableField("activity_create_time")
    private Date activityCreateTime;

    @TableField("activity_sign_begin_time")
    private Date activitySignBeginTime;

    @TableField("activity_sign_end_time")
    private Date activitySignEndTime;

    @TableField("activity_begin_time")
    private Date activityBeginTime;

    @TableField("activity_end_time")
    private Date activityEndTime;


}
