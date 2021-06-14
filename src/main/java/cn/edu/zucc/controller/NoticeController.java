package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Notice;
import cn.edu.zucc.entity.vo.NoticeAssVo;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.AssociationService;
import cn.edu.zucc.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private AssociationService associationService;

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

    /**
     * 社长发布公告
     * @param assId
     * @param noticeName
     * @param noticeBrief
     * @param noticePicture
     * @return
     */
    @GetMapping("/addNotice")
    @ApiOperation(value = "社长发布公告",notes = "社长发布公告")
    public Result addNotice(@RequestParam("assId") String assId,
                            @RequestParam("noticeName") String noticeName,
                            @RequestParam("noticeBrief") String noticeBrief,
                            @RequestParam("noticePicture") String noticePicture)
    {
        Notice notice = new Notice();
        notice.setAssId(Long.valueOf(assId));
        notice.setNoticeName(noticeName);
        notice.setNoticeBrief(noticeBrief);
        notice.setNoticePicture(noticePicture);
        noticeService.save(notice);
        String assName = associationService.getById(assId).getAssName();
        return Result.ok().data("notice",notice).data("assName",assName);
    }

    /**
     * 社长修改公告
     * @param noticeId
     * @param noticeName
     * @param noticeBrief
     * @param noticePicture
     * @return
     */
    @GetMapping("/modifyNotice")
    @ApiOperation(value = "社长修改公告",notes = "社长修改公告")
    public Result modifyNotice(@RequestParam("noticeId") String noticeId,
                               @RequestParam("noticeName") String noticeName,
                               @RequestParam("noticeBrief") String noticeBrief,
                               @RequestParam("noticePicture") String noticePicture){
        Notice notice = new Notice();
        notice.setNoticeId(Long.valueOf(noticeId));
        notice.setNoticeName(noticeName);
        notice.setNoticeBrief(noticeBrief);
        notice.setNoticePicture(noticePicture);
        noticeService.updateById(notice);
        notice = noticeService.getById(Long.valueOf(noticeId));
        String assName = associationService.getById(notice.getAssId()).getAssName();
        return Result.ok().data("notice",notice).data("assName",assName);
    }

    /**
     * 社长删除公告
     * @param noticeId
     * @return
     */
    @GetMapping("/deleteNotice")
    @ApiOperation(value = "社长删除公告",notes = "社长删除公告")
    public Result deleteNotice(@RequestParam("noticeId") String noticeId){
        if(noticeService.removeById(noticeId)) {
            return Result.ok().data("提示", "删除社团成功");
        }
        else {
            return Result.error().data("提示","不存在该公告");
        }

    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}

