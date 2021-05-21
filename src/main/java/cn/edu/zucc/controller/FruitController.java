package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Fruit;
import cn.edu.zucc.handle.BusinessException;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.response.ResultCode;
import cn.edu.zucc.service.FruitService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-20
 */
@RestController
@RequestMapping("//fruit")
@Api(value = "水果管理模块",tags = "水果管理接口")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    /**
     * 分页查询水果列表
     * @return
     */
    @ApiOperation("分页查询用户列表")
    @GetMapping("/findUserList")
    public Result findUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size) {
        //对用户进行分页,泛型中注入的就是用户实体类
        Page<Fruit> page = new Page<>(current, size);
        //单表的时候其实这个方法是非常好用的
        LambdaQueryWrapper<Fruit> queryWrapper = new LambdaQueryWrapper<>();
        Page<Fruit> userPage = fruitService.page(page);
        long total = userPage.getTotal();
        List<Fruit> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个水果",notes = "通过ID查询对应用户信息")
    public Result getFruitById(@PathVariable("id") Long id)
    {
        Fruit fruit = fruitService.getById(id);
       if(fruit != null)
       {
           return  Result.ok().data("fruit",fruit);
       }
       else {
           throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                   ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
       }
    }
}

