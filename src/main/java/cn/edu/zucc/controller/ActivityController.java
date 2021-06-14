package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.vo.ActivityAssociationVo;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.ActivityService;
import cn.edu.zucc.utils.DealDateFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
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
@RequestMapping("//activity")
@Api(value = "系统活动模块",tags = "系统活动接口")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * 创建活动
     * @param activityName
     * @param activityBrief
     * @param activityType
     * @param activityLeave
     * @param activityPeople
     * @param activityCredit
     * @return
     */
    @GetMapping("/addActivity")
    @ApiOperation(value = "创建活动",notes = "创建活动")
    public Result addActivity(@RequestParam(value = "assId",defaultValue = "1") String assId,
                              @RequestParam("activityName") String activityName,
                              @RequestParam("activityBrief") String activityBrief,
                              @RequestParam("activityType") String activityType,
                              @RequestParam("activityLeave") boolean activityLeave,
                              @RequestParam("activityPeople")  Integer activityPeople,
                              @RequestParam("activityCredit") Integer activityCredit,
                              @RequestParam("activitySignBeginTime") String activitySignBeginTime,
                              @RequestParam("activitySignEndTime") String activitySignEndTime,
                              @RequestParam("activityBeginTime") String activityBeginTime,
                              @RequestParam("activityEndTime") String activityEndTime)
                              {
        System.out.println(activityBeginTime);
        Activity activity = new Activity();
        activity.setAssId(Long.valueOf(assId));
        activity.setActivityName(activityName);
        activity.setActivityBrief(activityBrief);
        activity.setActivityType(activityType);
        activity.setActivityLeave(activityLeave);
        activity.setActivityStatus("审批中");
        activity.setActivityPeople(activityPeople);
        activity.setActivityCredit(new BigDecimal(activityCredit));
        activity.setActivityCreateTime(new Date());
        activity.setActivitySignBeginTime(new Date(DealDateFormatUtil.dealDateToLong(activitySignBeginTime)));
        activity.setActivitySignEndTime(new Date(DealDateFormatUtil.dealDateToLong(activitySignEndTime)));
        activity.setActivityBeginTime(new Date(DealDateFormatUtil.dealDateToLong(activityBeginTime)));
        activity.setActivityEndTime(new Date(DealDateFormatUtil.dealDateToLong(activityEndTime)));
        if(activityService.findActivityByName(activityName) != null)
        {
            return Result.error().data("提示","活动已存在");
        }
        else{
            activityService.save(activity);
            return Result.ok().data("提示","活动创建成功");
        }
    }

    /**
     * 删除活动
     * @param activityId
     * @return
     */
    @GetMapping("/deleteActivity")
    @ApiOperation(value = "删除活动",notes = "删除活动")
    public Result deleteActivity(@RequestParam("activityId") String activityId)
    {
        Activity activity = activityService.getById(activityId);
        if(activity == null){
            return Result.error().data("提示","不存在该活动");
        }else {
            activityService.removeById(activityId);
            return Result.ok().data("提示","删除活动成功");
        }
    }
    /**
     * 查找活动信息
     * @param activityId
     * @return
     */
    @GetMapping("/searchActivity")
    @ApiOperation(value = "查找活动信息",notes = "查找活动信息")
    public Result searchActivity(String activityId){
        Activity activity = activityService.getById(activityId);
        if(activity != null){
            return Result.ok().data("activity",activity);
        }else {
            return Result.error().data("提示","活动不存在");
        }
    }

    /**
     * 查看所有活动内容
     * @param current
     * @param size
     * @param query
     * @return
     */
    @GetMapping("/searchAllActivity")
    @ApiOperation(value = "查看所有活动内容",notes = "查看所有活动内容")
    public Result searchAllActivity(@RequestParam(required = true,defaultValue = "1") Integer current,
                                    @RequestParam(required = true,defaultValue = "8") Integer size,
                                    @RequestParam(required = true,defaultValue = "") String query){
        Page<ActivityAssociationVo> page = activityService.GetAllActivity(current,size,query);
        long total = page.getTotal();
        List<ActivityAssociationVo> records = page.getRecords();
        return Result.ok().data("total",total).data("records",records);
    }

}

