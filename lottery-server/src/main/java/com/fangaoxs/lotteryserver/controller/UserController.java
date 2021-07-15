package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/16:41
 * @Description:
 */
@Api(tags = "登记用户接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增一个登记用户")
    @PostMapping("/insertOneUserWithPlaceId")
    public ResultResponse insertOneUserWithPlaceId(@ApiParam("user对象") @RequestBody VoUser voUser){
//        System.out.println("voUser = " + voUser);
        Object data = null;
        StringBuilder message = new StringBuilder("用户登记");
        if (voUser.getPlaceId()==null){ //没有录入会场则登记失败
            message.append("：需要绑定会场");
            return new ResultResponse()
                    .setData(false)
                    .setMessage(message.toString());
        }
        try {
            data = userService.insertOneUserWithPlaceId(voUser);
        } catch (DataAccessException e){
//            e.printStackTrace();
            message.append("：昵称重复");
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message.toString());
    }

    @ApiOperation("删除一个登记用户（data返回Boolean）")
    @GetMapping("/deleteOneUser")
    public ResultResponse deleteOneUser(@ApiParam("user编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        Boolean data = userService.deleteOneUser(id);
        return new ResultResponse()
                .setData(data)
                .setMessage("删除登记的用户");
    }

    @ApiOperation("根据会场编号查询已登记用户（可分页）")
    @GetMapping("/selectListUserByPlaceId")
    public ResultResponse selectListUserByPlaceId(@ApiParam("会场编号") @RequestParam("placeId")Integer placeId,
                                                  @ApiParam("当前页（从1开始）") Integer currentPage,
                                                  @ApiParam("每页记录数") Integer pageSize){
//        System.out.println("placeId = " + placeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoUser voUser = new VoUser();
        voUser.setPlaceId(placeId);
        VoList<VoUser> voList = userService.selectListUserByPlaceId(placeId,currentPage,pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("根据会场查询登记用户");
    }

    @ApiOperation("根据用户编号查询具体登记信息")
    @GetMapping("/selectOneUserById")
    public ResultResponse selectOneUserById(@ApiParam("用户编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        VoUser dbUser = userService.selectOneUserById(id);
        return new ResultResponse()
                .setData(dbUser)
                .setMessage("根据编号查询登记用户");
    }

    @ApiOperation("根据用户昵称和会场编号查询登记用户")
    @GetMapping("/selectOneUserByNameWithPlaceId")
    public ResultResponse selectOneUserByNameWithPlaceId(@ApiParam("用户昵称") @RequestParam("name")String name,
                                                         @ApiParam("会场编号") @RequestParam("placeId")Integer placeId){
//        System.out.println("name = " + name + ", placeId = " + placeId);
        VoUser dbUser = userService.selectOneUserByNameWithPlaceId(name, placeId);
        return new ResultResponse()
                .setData(dbUser)
                .setMessage("根据会场和昵称查询登记用户");
    }
}
