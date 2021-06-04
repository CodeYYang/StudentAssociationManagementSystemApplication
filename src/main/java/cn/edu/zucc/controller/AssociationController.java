package cn.edu.zucc.controller;


import cn.edu.zucc.entity.Association;
import cn.edu.zucc.entity.UserActivity;
import cn.edu.zucc.entity.UserAssociation;
import cn.edu.zucc.response.Result;
import cn.edu.zucc.service.AssociationService;
import cn.edu.zucc.service.UserAssociationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@RequestMapping("//association")
@Api(value = "系统社团模块",tags = "系统社团接口")
public class AssociationController {

    @Resource
    private AssociationService associationService;
    @Resource
    private UserAssociationService userAssociationService;
    /**
     * 添加社团
     * @param assName
     * @param assBrief
     * @return
     */
    @GetMapping("/add")
    @ApiOperation(value = "添加社团",notes = "添加社团")
    public Result addAssociation(@RequestParam("assName")String assName,
                                 @RequestParam("assBrief") String assBrief,
                                 @RequestParam("userId") String userId){
        Association association = new Association();
        association.setAssBrief(assBrief);
        association.setAssName(assName);
        association.setAssTotal(0);
        association.setAssCreateTime(new Date());
        association.setAssStatus("待审核");
        if(associationService.getAssociationByName(assName) == null) {
            associationService.save(association);
            Association associationByName = associationService.getAssociationByName(assName);
            Long assId = associationByName.getAssId();
            userAssociationService.addUserAssociation(String.valueOf(assId),userId);
            userAssociationService.ModifyPresident(String.valueOf(assId),String.valueOf(userId),"社长");
            return Result.ok().data("association",association);
        }else{
            return Result.error().data("提示","该社团名称已存在");
        }

    }

    /**
     * 查看所有社团
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "社团列表",notes = "查看所有社团")
    public Result associationList(){
        List<Association> associationList = associationService.list();
        return Result.ok().data("allAssociation",associationList);
    }

    /**
     * 查看社团信息
     * @param assID
     * @return
     */
    @GetMapping("/searchAssociation")
    @ApiOperation(value = "社团信息",notes = "查看社团信息")
    public Result association(@RequestParam("assId") String assID){
        Association association = associationService.getById(assID);
        return Result.ok().data("AssociationInformation",association);
    }

    /**
     * 设置社团社长
     * @param assId
     * @param userId
     * @return
     */
    @GetMapping("/ModifyPresident")
    @ApiOperation(value = "社团社长",notes = "设置社团社长")
    public Result ModifyPresident(@RequestParam("assId") String assId,@RequestParam("userId") String userId){
        UserAssociation userAssociation = userAssociationService.searchUserAssociation(assId, userId);
        if(userAssociation == null){
            return Result.error().data("提示","该社团不存在该成员");
        }
        UserAssociation president = userAssociationService.getPresident(assId);
        UserActivity userActivity = new UserActivity();
        if (president == null){
            userAssociationService.ModifyPresident(assId,userId,"社长");
            return Result.ok().data("提示","已修改为社长");
        }else{
            userAssociationService.ModifyPresident(String.valueOf(president.getAssId()),String.valueOf(president.getUserId()),"社员");
            userAssociationService.ModifyPresident(assId,userId,"社长");
            return Result.ok().data("提示","修改成功，原社长已成为普通成员");
        }
    }
    @GetMapping("/RemoveAssociation")
    @ApiOperation(value = "社团停止",notes = "删除社团")
    public Result RemoverAssociation(@RequestParam("assId") String assId){
        if(associationService.removeById(assId)){
            return Result.ok().data("提示","社团停止成功");
        }else{
            return Result.error().data("提示","社团停止失败");
        }
    }
}

