package cn.edu.zucc.controller;


import cn.edu.zucc.config.Token;
import cn.edu.zucc.entity.*;
import cn.edu.zucc.entity.vo.ActivityUser;
import cn.edu.zucc.entity.vo.AssociationExt;
import cn.edu.zucc.entity.vo.AssociationUser;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.response.ResultCode;
import cn.edu.zucc.service.*;
import cn.edu.zucc.utils.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
@RequestMapping("//user")
@Api(value = "系统用户模块",tags = "系统用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserAssociationService userAssociationService;

    @Resource
    private AssociationService associationService;

    @Resource
    private UserActivityService userActivityService;
    @Resource
    private ActivityService activityService;
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

    /**
     * 用户修改
     * @param phone
     * @param password
     * @param modifyPhone
     * @param userName
     * @return
     */
    @GetMapping("/modify")
    @ApiOperation(value = "用户修改",notes = "用户修改")
    public Result modifyUser(@RequestParam("phone")String phone,
                             @RequestParam("password") String password,
                             @RequestParam("modifyPhone") String modifyPhone,
                             @RequestParam("userName") String userName)
    {
        if(userService.getUserByPhone(modifyPhone) == null){
            User user = new User();
            user.setUserPwd(password);
            user.setUserName(userName);
            user.setUserPhone(modifyPhone);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_phone",phone);
            userService.update(user,queryWrapper);
            return Result.ok().data("modifyUser",userService.getUserByPhone(modifyPhone));
        }
        else {
            return Result.error().data("提示","该手机号已存在");
        }
    }

    /**
     * 用户报名社团
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/registerAssociation")
    @ApiOperation(value = "用户报名社团",notes = "用户报名社团")
    public Result registerAssociation(@RequestParam("userId") String userId,
                                      @RequestParam("assId") String assId){
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.setUserId(Long.valueOf(userId));
        userAssociation.setAssId(Long.valueOf(assId));
        userAssociation.setUserAssRole("社员");
        userAssociation.setUserAssStatus("待审核");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ass_id",assId);
        queryWrapper.eq("user_id",userId);
        UserAssociation one = userAssociationService.getOne(queryWrapper);
        if( one != null){
            if(one.getUserAssStatus().equals("待审核")||one.getUserAssStatus().equals("审核中"))
                return Result.error().data("提示","您已提交加入社团申请，等待审核中");
            else
                return Result.error().data("提示","您已是该社团成员，请勿重复申请");
        }
        userAssociationService.save(userAssociation);
        //Association associationServiceById = associationService.getById(assId);
        //associationServiceById.setAssTotal(associationServiceById.getAssTotal()+1);
        //associationService.updateById(associationServiceById);
        return Result.ok().data("userAssociation",userAssociation);
    }

    /**
     * 用户报名活动
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/registerActivity")
    @ApiOperation(value = "用户报名活动",notes = "用户报名活动")
    public Result registerActivity(@RequestParam("userId") String userId,
                                      @RequestParam("activityId") String activityId){

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(Long.valueOf(userId));
        userActivity.setActivityId(Long.valueOf(activityId));
        userActivity.setUserActivityStatus("待审核");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id",activityId);
        queryWrapper.eq("user_id",userId);
        UserActivity one = userActivityService.getOne(queryWrapper);
        if( one != null){
            if(one.getUserActivityStatus().equals("待审核")||one.getUserActivityStatus().equals("审核中"))
                return Result.error().data("提示","您已提交加入活动申请，等待审核中");
            else
                return Result.error().data("提示","您已加入该活动，请勿重复申请");
        }
        Activity activity = activityService.getById(activityId);
        if(activity.getActivityPeople() == 0){
            return Result.error().data("提示","活动人数已满");
        }
        else {
            userActivityService.save(userActivity);
            return Result.ok().data("UserActivity",userActivity);
        }

    }

    /**
     * 用户查看已经加入的社团
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/searchMyAssociation")
    @ApiOperation(value = "用户查看已经加入的社团",notes = "用户查看已经加入的社团")
    public Result searchMyAssociation(@RequestParam(required = true,defaultValue = "1") Integer current,
                                      @RequestParam(required = true,defaultValue = "8") Integer size,
                                      @RequestParam(required = true,defaultValue = "") String query,
                                      @RequestParam("userId") String userId
                                      )
    {

        IPage<AssociationUser> iPage = userService.searchMyAssociation(current, size, query, userId);
        long total = iPage.getTotal();
        List<AssociationUser> records = iPage.getRecords();
        if(total == 0){
            return Result.error().data("提示","未加入任何社团");
        }else {
            return Result.ok().data("total", total).data("records", records);
        }
    }
    /**
     * 用户查看已经加入的活动
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/searchMyActivity")
    @ApiOperation(value = "用户查看已经加入的活动",notes = "用户查看已经加入的活动")
    public Result searchMyActivity(@RequestParam(required = true,defaultValue = "1") Integer current,
                                      @RequestParam(required = true,defaultValue = "8") Integer size,
                                      @RequestParam(required = true,defaultValue = "") String query,
                                      @RequestParam("userId") String userId
    )
    {

        IPage<ActivityUser> iPage = userService.searchMyActivity(current, size, query, userId);
        long total = iPage.getTotal();
        List<ActivityUser> records = iPage.getRecords();
        if(total == 0){
            return Result.error().data("提示","未加入任何活动");
        }else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 用户退出已加入活动
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/quitActivity")
    @ApiOperation(value = "用户退出已加入活动",notes = "用户退出已加入活动")
    public Result quitActivity(@RequestParam("userId") String userId,
                                  @RequestParam("activityId") String activityId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("activity_id",activityId);
        UserActivity userActivity = userActivityService.getOne(queryWrapper);
        if(userActivity == null){
            return Result.error().data("提示","您未加入此活动");
        }else if (userActivity.getUserActivityStatus().equals("待审核")){
            userActivityService.remove(queryWrapper);
            return Result.ok().data("提示","取消加入活动申请成功");
        }else {
            userActivityService.remove(queryWrapper);
            return Result.ok().data("提示","成功退出该活动");
        }
    }
    /**
     * 用户退出已加入社团
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/quitAssociation")
    @ApiOperation(value = "用户退出已加入社团",notes = "用户退出已加入社团")
    public Result quitAssociation(@RequestParam("userId") String userId,
                                  @RequestParam("assId") String assId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("ass_id",assId);
        UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
        if(userAssociation == null){
            return Result.error().data("提示","您未加入此社团");
        }else if (userAssociation.getUserAssStatus().equals("待审核")){
            userAssociationService.remove(queryWrapper);
            return Result.ok().data("提示","取消加入社团申请成功");
        }else {
            userAssociationService.remove(queryWrapper);
            return Result.ok().data("提示","成功退出该社团");
        }
    }

}

