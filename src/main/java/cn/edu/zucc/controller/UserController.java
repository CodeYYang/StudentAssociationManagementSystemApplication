package cn.edu.zucc.controller;


import cn.edu.zucc.config.Token;
import cn.edu.zucc.entity.*;
import cn.edu.zucc.entity.vo.ActivityUser;
import cn.edu.zucc.entity.vo.AssociationUser;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-06-04
 */
@RestController
@RequestMapping("//user")
@Api(value = "系统用户模块", tags = "系统用户接口")
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
     *
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/login")
    @ApiOperation(value = "用户登录", notes = "通过手机和密码登录")
    public Result userLogin(@RequestParam("phone") String phone,
                            @RequestParam("password") String password) {

        User user = userService.getUserByPhone(phone);

        String encryption = Token.encryption("phone", "password");

        if (user == null || !password.equals(user.getUserPwd())) {
            return Result.error().data("提示", "用户或密码错误");
        } else {
            String s = "";
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id",user.getUserId());
            queryWrapper.eq("user_ass_role","社长");
            UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
            if(userAssociation != null){
                s = "社长";
            }
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("user_id",user.getUserId());
            queryWrapper1.eq("user_ass_role","社员");
            UserAssociation userAssociation1 = userAssociationService.getOne(queryWrapper1);
            if (userAssociation1 != null){
                s = "社员";
            }
            if(s.equals("")){
                s = "该成员不存在社团身份";
            }
            return Result.ok().data("user", user).data("token", encryption).data("userAssRole",s);
        }
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @param userName
     * @return
     */
    @GetMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result userRegister(@RequestParam("phone") String phone,
                               @RequestParam("password") String password,
                               @RequestParam("userName") String userName,
                               @RequestParam("userRole") String userRole) {
        User user = new User();
        user.setUserCredit(new BigDecimal(0));
        user.setUserName(userName);
        user.setUserPhone(phone);
        user.setUserPwd(password);
        user.setUserCreateTime(new Date());
        user.setUserRole(userRole);
        User user1 = userService.getUserByPhone(phone);
        if (user1 != null) {
            return Result.error().data("提示", "该手机号码已存在");
        } else {
            userService.save(user);
        }
        return Result.ok().data("user", userService.getUserByPhone(phone));

    }

    /**
     * 用户修改
     *
     * @param phone
     * @param password
     * @param modifyPhone
     * @param userName
     * @return
     */
    @GetMapping("/modify")
    @ApiOperation(value = "用户修改", notes = "用户修改")
    public Result modifyUser(@RequestParam("phone") String phone,
                             @RequestParam("password") String password,
                             @RequestParam("modifyPhone") String modifyPhone,
                             @RequestParam("userName") String userName) {
        if (userService.getUserByPhone(modifyPhone) == null) {
            User user = new User();
            user.setUserPwd(password);
            user.setUserName(userName);
            user.setUserPhone(modifyPhone);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_phone", phone);
            userService.update(user, queryWrapper);
            return Result.ok().data("modifyUser", userService.getUserByPhone(modifyPhone));
        } else {
            return Result.error().data("提示", "该手机号已存在");
        }
    }

    /**
     * 用户报名社团
     *
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/registerAssociation")
    @ApiOperation(value = "用户报名社团", notes = "用户报名社团")
    public Result registerAssociation(@RequestParam("userId") String userId,
                                      @RequestParam("assId") String assId) {
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.setUserId(Long.valueOf(userId));
        userAssociation.setAssId(Long.valueOf(assId));
        userAssociation.setUserAssRole("社员");
        userAssociation.setUserAssStatus("待审核");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ass_id", assId);
        queryWrapper.eq("user_id", userId);
        UserAssociation one = userAssociationService.getOne(queryWrapper);
        if (one != null) {
            if (one.getUserAssStatus().equals("待审核") || one.getUserAssStatus().equals("审核中")) {
                return Result.error().data("提示", "您已提交加入社团申请，等待审核中");
            } else {
                return Result.error().data("提示", "您已是该社团成员，请勿重复申请");
            }
        }
        userAssociationService.save(userAssociation);
        //Association associationServiceById = associationService.getById(assId);
        //associationServiceById.setAssTotal(associationServiceById.getAssTotal()+1);
        //associationService.updateById(associationServiceById);
        return Result.ok().data("userAssociation", userAssociation);
    }

    /**
     * 用户报名活动
     *
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/registerActivity")
    @ApiOperation(value = "用户报名活动", notes = "用户报名活动")
    public Result registerActivity(@RequestParam("userId") String userId,
                                   @RequestParam("activityId") String activityId) {

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(Long.valueOf(userId));
        userActivity.setActivityId(Long.valueOf(activityId));
        userActivity.setUserActivityStatus("待审核");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id", activityId);
        queryWrapper.eq("user_id", userId);
        UserActivity one = userActivityService.getOne(queryWrapper);
        if (one != null) {
            if (one.getUserActivityStatus().equals("待审核") || one.getUserActivityStatus().equals("审核中")) {
                return Result.error().data("提示", "您已提交加入活动申请，等待审核中");
            } else {
                return Result.error().data("提示", "您已加入该活动，请勿重复申请");
            }
        }
        Activity activity = activityService.getById(activityId);
        if (activity.getActivityPeople() == 0) {
            return Result.error().data("提示", "活动人数已满");
        } else {
            userActivityService.save(userActivity);
            return Result.ok().data("UserActivity", userActivity);
        }

    }

    /**
     * 用户查看已经加入的社团
     *
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/searchMyAssociation")
    @ApiOperation(value = "用户查看已经加入的社团", notes = "用户查看已经加入的社团")
    public Result searchMyAssociation(@RequestParam(required = true, defaultValue = "1") Integer current,
                                      @RequestParam(required = true, defaultValue = "8") Integer size,
                                      @RequestParam(required = true, defaultValue = "") String query,
                                      @RequestParam("userId") String userId
    ) {

        Activity activity = new Activity();
        IPage<AssociationUser> iPage = userService.searchMyAssociation(current, size, query, userId);
        long total = iPage.getTotal();
        List<AssociationUser> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "未加入任何社团");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 用户查看已经加入的活动
     *
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/searchMyActivity")
    @ApiOperation(value = "用户查看已经加入的活动", notes = "用户查看已经加入的活动")
    public Result searchMyActivity(@RequestParam(required = true, defaultValue = "1") Integer current,
                                   @RequestParam(required = true, defaultValue = "8") Integer size,
                                   @RequestParam(required = true, defaultValue = "") String query,
                                   @RequestParam("userId") String userId
    ) {

        IPage<ActivityUser> iPage = userService.searchMyActivity(current, size, query, userId);
        long total = iPage.getTotal();
        List<ActivityUser> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "未加入任何活动");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 用户退出已加入活动
     *
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/quitActivity")
    @ApiOperation(value = "用户退出已加入活动", notes = "用户退出已加入活动")
    public Result quitActivity(@RequestParam("userId") String userId,
                               @RequestParam("activityId") String activityId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("activity_id", activityId);
        UserActivity userActivity = userActivityService.getOne(queryWrapper);
        if (userActivity == null || userActivity.getUserActivityStatus().equals("审核未通过")) {
            return Result.error().data("提示", "您未加入此活动");
        } else if (userActivity.getUserActivityStatus().equals("待审核")) {
            userActivityService.remove(queryWrapper);
            return Result.ok().data("提示", "取消加入活动申请成功");
        } else {
            userActivityService.remove(queryWrapper);
            return Result.ok().data("提示", "成功退出该活动");
        }
    }

    /**
     * 用户退出已加入社团
     *
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/quitAssociation")
    @ApiOperation(value = "用户退出已加入社团", notes = "用户退出已加入社团")
    public Result quitAssociation(@RequestParam("userId") String userId,
                                  @RequestParam("assId") String assId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ass_id", assId);
        UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
        if (userAssociation == null || userAssociation.getUserAssStatus().equals("审核未通过")) {
            return Result.error().data("提示", "您未加入此社团");
        } else if (userAssociation.getUserAssStatus().equals("待审核")) {
            userAssociationService.remove(queryWrapper);
            return Result.ok().data("提示", "取消加入社团申请成功");
        } else {
            userAssociationService.remove(queryWrapper);
            return Result.ok().data("提示", "成功退出该社团");
        }
    }

    /**
     * 查看社长管理的社团
     *
     * @param current
     * @param size
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/searchManagementAssociation")
    @ApiOperation(value = "查看社长管理的社团", notes = "查看社长管理的社团")
    public Result searchManagementAssociation(@RequestParam(required = true, defaultValue = "1") Integer current,
                                              @RequestParam(required = true, defaultValue = "8") Integer size,
                                              @RequestParam(required = true, defaultValue = "") String query,
                                              @RequestParam("userId") String userId) {
        IPage<Association> iPage = userService.searchManagementAssociation(current, size, query, userId);
        long total = iPage.getTotal();
        List<Association> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "您未管理任何社团");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 查看社长管理的社团成员
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchManagementAssociationMember")
    @ApiOperation(value = "查看社长管理的社团成员", notes = "查看社长管理的社团成员")
    public Result searchManagementAssociationMember(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                    @RequestParam(required = true, defaultValue = "8") Integer size,
                                                    @RequestParam(required = true, defaultValue = "") String query,
                                                    @RequestParam("assId") String assId) {
        IPage<User> iPage = userService.searchManagementAssociationMember(current, size, query, assId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该社团不存在成员");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 查看待审核的社团成员
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchAssMemberWaitStatue")
    @ApiOperation(value = "查看待审核的社团成员", notes = "查看待审核的社团成员")
    public Result searchAssMemberWaitStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                            @RequestParam(required = true, defaultValue = "8") Integer size,
                                            @RequestParam(required = true, defaultValue = "") String query,
                                            @RequestParam("assId") String assId) {

        IPage<User> iPage = userService.searchAssMemberWaitStatus(current, size, query, assId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该社团不存在待审核");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 查看审核未通过的社团成员
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchAssMemberNotStatue")
    @ApiOperation(value = "查看审核未通过的社团成员", notes = "查看审核未通过的社团成员")
    public Result searchAssMemberNotStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                            @RequestParam(required = true, defaultValue = "8") Integer size,
                                            @RequestParam(required = true, defaultValue = "") String query,
                                            @RequestParam("assId") String assId) {

        IPage<User> iPage = userService.searchAssMemberNotStatus(current, size, query, assId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该社团不存在审核未通过成员");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }
    /**
     * 社长删除社团成员
     *
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/deleteAssMember")
    @ApiOperation(value = "社长删除社团成员", notes = "社长删除社团成员")
    public Result deleteAssMember(@RequestParam("userId") String userId,
                                  @RequestParam("assId") String assId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ass_id", assId);
        UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
        if (userAssociation == null) {
            return Result.error().data("提示", "该社团成员不存在");
        } else {
            userAssociationService.remove(queryWrapper);
            return Result.ok().data("提示", "成功删除该成员");
        }


    }

    /**
     * 社长同意社团成员加入
     *
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/agreeAssMember")
    @ApiOperation(value = "社长同意社团成员加入", notes = "社长同意社团成员加入")
    public Result agreeAssMember(@RequestParam("userId") String userId,
                                 @RequestParam("assId") String assId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ass_id", assId);
        UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
        if (userAssociation.getUserAssStatus().equals("审核通过")) {
            return Result.error().data("提示", "该成员已经是社团成员");
        } else {
            userAssociation.setUserAssStatus("审核通过");
            userAssociationService.update(userAssociation, queryWrapper);
            return Result.ok().data("提示", "社长同意社团成员加入");
        }
    }

    /**
     * 社长不同意社团成员加入
     *
     * @param userId
     * @param assId
     * @return
     */
    @GetMapping("/disAgreeAssMember")
    @ApiOperation(value = "社长不同意社团成员加入", notes = "社长不同意社团成员加入")
    public Result disAgreeAssMember(@RequestParam("userId") String userId,
                                    @RequestParam("assId") String assId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ass_id", assId);
        UserAssociation userAssociation = userAssociationService.getOne(queryWrapper);
        if (userAssociation.getUserAssStatus().equals("审核通过")) {
            return Result.error().data("提示", "该成员已经是社团成员");
        } else {
            userAssociation.setUserAssStatus("审核未通过");
            userAssociationService.update(userAssociation, queryWrapper);
            return Result.ok().data("提示", "社长不同意社团成员加入");
        }
    }

    /**
     * 查看社长管理的活动
     *
     * @param current
     * @param size
     * @param query
     * @param assId
     * @return
     */
    @GetMapping("/searchManagementActivity")
    @ApiOperation(value = "查看管理的活动", notes = "查看管理的活动")
    public Result searchManagementActivity(@RequestParam(required = true, defaultValue = "1") Integer current,
                                           @RequestParam(required = true, defaultValue = "8") Integer size,
                                           @RequestParam(required = true, defaultValue = "") String query,
                                           @RequestParam("assId") String assId) {
        Page<Activity> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("activity_name", query);
        queryWrapper.eq("ass_id", assId);
        queryWrapper.eq("activity_status", "审核通过");
        Page<Activity> activityPage = activityService.page(page, queryWrapper);
        long total = activityPage.getTotal();
        if (total != 0) {
            List<Activity> records = activityPage.getRecords();
            return Result.ok().data("total", total).data("records", records);
        } else {
            return Result.error().data("提示", "该社团不存在活动");
        }
    }

    /**
     * 查看社长管理的社团成员
     *
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @GetMapping("/searchManagementActivityMember")
    @ApiOperation(value = "查看社长管理的活动成员", notes = "查看社长管理的社团成员")
    public Result searchManagementActivityMember(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                 @RequestParam(required = true, defaultValue = "8") Integer size,
                                                 @RequestParam(required = true, defaultValue = "") String query,
                                                 @RequestParam("activityId") String activityId) {
        IPage<User> iPage = userService.searchManagementActivityMember(current, size, query, activityId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该活动不存在成员");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    /**
     * 社长删除活动成员
     *
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/deleteActivityMember")
    @ApiOperation(value = "社长删除活动成员", notes = "社长删除活动成员")
    public Result deleteActivityMember(@RequestParam("userId") String userId,
                                       @RequestParam("activityId") String activityId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("activity_id", activityId);
        UserActivity userActivity = userActivityService.getOne(queryWrapper);
        if (userActivity == null) {
            return Result.error().data("提示", "该活动成员不存在");
        } else {
            userActivityService.remove(queryWrapper);
            return Result.ok().data("提示", "成功删除该成员");
        }
    }

    /**
     * 社长同意活动成员加入
     *
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/agreeActivityMember")
    @ApiOperation(value = "社长同意活动成员加入", notes = "社长同意活动成员加入")
    public Result agreeActivityMember(@RequestParam("userId") String userId,
                                      @RequestParam("activityId") String activityId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("activity_id", activityId);
        UserActivity userActivity = userActivityService.getOne(queryWrapper);
        if (userActivity.getUserActivityStatus().equals("审核通过")) {
            return Result.error().data("提示", "该成员已经是活动成员");
        } else {
            userActivity.setUserActivityStatus("审核通过");
            userActivityService.update(userActivity, queryWrapper);
            return Result.ok().data("提示", "社长同意活动成员加入");
        }
    }

    /**
     * 社长不同意活动成员加入
     *
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/disAgreeActivityMember")
    @ApiOperation(value = "社长不同意活动成员加入", notes = "社长不同意活动成员加入")
    public Result activityId(@RequestParam("userId") String userId,
                             @RequestParam("activityId") String activityId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("activity_id", activityId);
        UserActivity userActivity = userActivityService.getOne(queryWrapper);
        if (userActivity.getUserActivityStatus().equals("审核通过")) {
            return Result.error().data("提示", "该成员已经是活动成员");
        } else {
            userActivity.setUserActivityStatus("审核未通过");
            userActivityService.update(userActivity, queryWrapper);
            return Result.ok().data("提示", "社长不同意活动成员加入");
        }
    }

    /**
     * 查看待审核的活动成员
     *
     * @param current
     * @param size
     * @param query
     * @param activityId
     * @return
     */
    @GetMapping("/searchActivityMemberWaitStatue")
    @ApiOperation(value = "查看待审核的活动成员", notes = "查看待审核的活动成员")
    public Result searchActivityMemberWaitStatue(@RequestParam(required = true, defaultValue = "1") Integer current,
                                                 @RequestParam(required = true, defaultValue = "8") Integer size,
                                                 @RequestParam(required = true, defaultValue = "") String query,
                                                 @RequestParam("activityId") String activityId) {

        IPage<User> iPage = userService.searchActivityMemberWaitStatus(current, size, query, activityId);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        if (total == 0) {
            return Result.error().data("提示", "该活动不存在待审核");
        } else {
            return Result.ok().data("total", total).data("records", records);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

