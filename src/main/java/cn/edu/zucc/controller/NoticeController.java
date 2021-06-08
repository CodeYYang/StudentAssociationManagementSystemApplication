package cn.edu.zucc.controller;


import cn.edu.zucc.entity.vo.NoticeAssVo;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@Api(value = "系统公告模块",tags = "系统公告接口")
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
        List<NoticeAssVo> noticeAssVos = noticeService.searchAllNotice(assId);
        if (noticeAssVos.size() == 0){
            return Result.error().data("提示","该社团不存在公告");
        }
        else {
            return Result.ok().data("notices", noticeAssVos);
        }
    }
}

