package cn.edu.zucc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
    @TableName("association")
@ApiModel(value="Association对象", description="")
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("ass_id")
    private Long assId;

    @TableField("ass_name")
    private String assName;

    @TableField("ass_brief")
    private String assBrief;

    @TableField("ass_total")
    private Integer assTotal;

    @TableField("ass_status")
    private String assStatus;

    @TableField("ass_create_time")
    private Date assCreateTime;


}
