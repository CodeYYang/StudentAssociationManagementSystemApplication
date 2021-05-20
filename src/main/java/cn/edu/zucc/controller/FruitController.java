package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Fruit;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.FruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "水果管理")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    @ApiOperation(value = "查询所有用户信息")
    public Result findFruits(){
        List<Fruit> list = fruitService.list();
        return Result.ok().data("fruit",list);
    }
}

