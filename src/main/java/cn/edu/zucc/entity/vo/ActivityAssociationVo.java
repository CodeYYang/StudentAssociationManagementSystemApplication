package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Activity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActivityAssociationVo  {
    @TableField("ass_name")
    private String assName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("activity_id")
    private Long activityId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
