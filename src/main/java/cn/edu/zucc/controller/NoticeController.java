package cn.edu.zucc.controller;


import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.NoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@RestController
@RequestMapping("//notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /**
     * 查看所有公告
     * @param assId
     * @return
     */
    @GetMapping("/searchAllNotice")
    @ApiOperation(value = "查看所有公告",notes = "查看所有公告")
    public Result searchAllNotice(@RequestParam("assId") String assId){
        return Result.ok().data("notices",noticeService.searchAllNotice(assId));
    }
}

