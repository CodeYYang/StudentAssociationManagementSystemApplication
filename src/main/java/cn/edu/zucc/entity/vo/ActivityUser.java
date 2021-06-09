package cn.edu.zucc.entity.vo;

import cn.edu.zucc.entity.Activity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ActivityUser extends Activity {

    @TableField("ass_name")
    private String assName;
    @TableField("user_activity_status")
    private String userActivityStatus;
}
