package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Notice;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.annotation.security.DenyAll;

@Data
public class NoticeAssVo extends Notice {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId("ass_id")
    private Long assId;

    @TableField("ass_name")
    private String assName;
}
