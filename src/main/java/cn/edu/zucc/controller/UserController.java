package cn.edu.zucc.controller;


import cn.edu.zucc.config.Token;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.response.ResultCode;
import cn.edu.zucc.service.UserService;
import cn.edu.zucc.utils.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@RestController
@RequestMapping("//user")
@Api(value = "系统用户模块",tags = "系统用户接口")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 通过手机和密码登录
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/login")
    @ApiOperation(value = "用户登录",notes = "通过手机和密码登录")
    public Result userLogin(@RequestParam("phone")String phone,
                            @RequestParam("password") String password){

        User user = userService.getUserByPhone(phone);

        String encryption = Token.encryption("phone", "password");

        if(user == null || !password.equals(user.getUserPwd())){
            return Result.error().data("提示","用户或密码错误");
        }

        else{
            //session.setAttribute("user_information",user);
            //session.setMaxInactiveInterval(60*30);
            return Result.ok().data("user",user).data("token",encryption);
        }
    }

    /**
     * 用户注册
     * @param phone
     * @param password
     * @param userName
     * @return
     */
    @GetMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public Result userRegister(@RequestParam("phone")String phone,
                               @RequestParam("password") String password,
                               @RequestParam("userName") String userName,
                               @RequestParam("userRole") String userRole){
        User user = new User();
        user.setUserCredit(new BigDecimal(0));
        user.setUserName(userName);
        user.setUserPhone(phone);
        user.setUserPwd(password);
        user.setUserCreateTime(new Date());
        user.setUserRole(userRole);
        User user1 = userService.getUserByPhone(phone);
        if(user1 != null){
            return Result.error().data("提示","该手机号码已存在");
        }else {
            userService.save(user);
        }
        return Result.ok().data("user",userService.getUserByPhone(phone));

    }
}

