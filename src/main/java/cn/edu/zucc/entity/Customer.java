package cn.edu.zucc.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Customer对象", description="")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customerId")
    private String customerId;

    private String customerName;

    private String customerSex;

    private String password;

    private String email;

    private Date createDate;


}
