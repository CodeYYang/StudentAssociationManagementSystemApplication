package cn.edu.zucc.controller;


import cn.edu.zucc.config.Token;
import cn.edu.zucc.entity.Customer;
import cn.edu.zucc.entity.Fruit;
import cn.edu.zucc.handle.BusinessException;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.response.ResultCode;
import cn.edu.zucc.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-21
 */
@RestController
@RequestMapping("//customer")
@Api(value = "顾客管理模块",tags = "顾客管理接口")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /**
     * 登录顾客
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/login")
    @ApiOperation(value = "登录顾客",notes = "通过邮箱和密码登录")
    public Result getFruitById(HttpSession session,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password)
    {
        String token = Token.encryption("email","password");
        Customer customer = customerService.findCustomerByEmail(email);
        if(customer == null){
            return Result.error();
        }else {
            if(customer.getPassword().equals(password)){
                /*将用户对象放入session中*/
                session.setAttribute("customer_information",customer);
                /*用户无操作30分钟需重新登录*/
                session.setMaxInactiveInterval(60*30);
                return Result.ok().data("customer",customer).data("token",token);
            }else {
                return Result.error();
            }
        }
    }

    @RequestMapping("/judgeLogin")
    public Result judgeLogin(String token,HttpSession session){

        if (Token.verify(token)){
            Customer customer =(Customer)session.getAttribute("customer_information");
            return Result.ok().data("用户登陆成功",customer);
        }else {
            return Result.error();
        }
    }


}

