package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Activity;
import com.baomidou.mybatisplus.annotation.TableField;

public class ActivityAssociationVo extends Activity {
    @TableField("ass_name")
    private String assName;
}
