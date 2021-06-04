package cn.edu.zucc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableName("notice")
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("notice_id")
      private Long noticeId;

    @TableField("ass_id")
    private Long assId;

    @TableField("notice_name")
    private String noticeName;

    @TableField("notice_brief")
    private String noticeBrief;

    @TableField("notice_picture")
    private String noticePicture;


}
