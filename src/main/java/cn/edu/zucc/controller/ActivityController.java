package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.entity.vo.ActivityAssociationVo;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                              @RequestParam("activitySignBeginTime") Date activitySignBeginTime,
                              @RequestParam("activitySignEndTime") Date activitySignEndTime,
                              @RequestParam("activityBeginTime") Date activityBeginTime,
                              @RequestParam("activityEndTime") Date activityEndTime)
                              {
        System.out.println(activityBeginTime);
        Activity activity = new Activity();
        activity.setAssId(Long.valueOf(assId));
        activity.setActivityName(activityName);
        activity.setActivityBrief(activityBrief);
        activity.setActivityType(activityType);
        activity.setActivityLeave(activityLeave);
        activity.setActivityStatus("待审核");

        activity.setActivityPeople(activityPeople);
        activity.setActivityCredit(new BigDecimal(activityCredit));
        activity.setActivityCreateTime(activitySignBeginTime);
        activity.setActivitySignEndTime(activitySignEndTime);
        activity.setActivityBeginTime(activityBeginTime);
        activity.setActivityEndTime(activityEndTime);
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


    /**
     * 查看审核未通过的活动成员
     *
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @GetMapping("/searchActivityMemberNotStatue")
    @ApiOperation(value = "查看审核未通过的活动成员", notes = "查看审核未通过的社团成员")
    public Result searchActivityMemberNotStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                @RequestParam(required = true, defaultValue = "8") Integer size,
                                                @RequestParam(required = true, defaultValue = "") String query,
                                                @RequestParam("activityId") String activityId) {

        IPage<User> iPage = activityService.searchActivityMemberNotStatus(current, size, query, activityId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该活动不存在审核未通过成员");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 管理员审核活动通过
     * @param activityId
     * @return
     */
    @GetMapping("/passActivityStatus")
    @ApiOperation(value = "管理员审核活动通过",notes = "管理员审核活动通过")
    public Result passActivityStatus(@RequestParam("activityId") String activityId)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id",activityId);
        Activity activity = activityService.getOne(queryWrapper);
        if(activity == null){
            return Result.error().data("提示","活动不存在");
        }else{
            if (activity.getActivityStatus().equals("审核未通过") || activity.getActivityStatus().equals("审核通过")){
                return Result.error().data("提示","该活动已经过管理员审核");
            }else {
                activity.setActivityStatus("审核通过");
                activityService.saveOrUpdate(activity);
                return Result.ok().data("提示","审核完成");
            }
        }
    }
    /**
     * 管理员审核活动未通过
     * @param activityId
     * @return
     */
    @GetMapping("/disPassActivityStatus")
    @ApiOperation(value = "管理员审核活动未通过",notes = "管理员审核活动未通过")
    public Result disPassActivityStatus(@RequestParam("activityId") String activityId)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id",activityId);
        Activity activity = activityService.getOne(queryWrapper);
        if(activity == null){
            return Result.error().data("提示","活动不存在");
        }else{
            if (activity.getActivityStatus().equals("审核未通过") || activity.getActivityStatus().equals("审核通过")){
                return Result.error().data("提示","该活动已经过管理员审核");
            }else {
                activity.setActivityStatus("审核未通过");
                activityService.saveOrUpdate(activity);
                return Result.ok().data("提示","审核完成");
            }
        }
    }

    /**
     * 查看待审核的活动
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchActivityWaitStatue")
    @ApiOperation(value = "查看待审核的活动", notes = "查看待审核的活动")
    public Result searchAssMemberWaitStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                            @RequestParam(required = true, defaultValue = "8") Integer size,
                                            @RequestParam(required = true, defaultValue = "") String query) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_status","待审核");
        queryWrapper.eq("activity_name",query);
        Page<Activity> activityPage = new Page<>(current,size);
        IPage<Activity> activityIPage = activityService.page(activityPage,queryWrapper);
        long total = activityIPage.getTotal();
        List<Activity> records = activityIPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该不存在待审核的活动");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }
    /**
     * 查看审核未通过的活动
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchActivityNoyPassStatue")
    @ApiOperation(value = "查看审核未通过的活动", notes = "查看审核未通过的活动")
    public Result searchActivityNoyPassStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                            @RequestParam(required = true, defaultValue = "8") Integer size,
                                            @RequestParam(required = true, defaultValue = "") String query) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_status","审核未通过");
        queryWrapper.eq("activity_name",query);
        Page<Activity> activityPage = new Page<>(current,size);
        IPage<Activity> activityIPage = activityService.page(activityPage,queryWrapper);
        long total = activityIPage.getTotal();
        List<Activity> records = activityIPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该不存在审核未通过的活动");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

