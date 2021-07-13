package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/16:41
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insertOneUser")
    public ResultResponse insertOneUser(@RequestBody VoUser voUser){
        System.out.println("voUser = " + voUser);
        Boolean data = false;
        VoUser dbUser = userService.selectOneUserByNameWithPlaceId(voUser.getName(), voUser.getPlaceId());
        if (dbUser==null){ //如果用户不存在且不在当前会场则执行插入
            data = userService.insertOneUserWithPlaceId(voUser);
        }
        return new ResultResponse()
                .setData(data)
                .setMessage("用户登记");
    }

    @GetMapping("/deleteOneUser")
    public ResultResponse deleteOneUser(@RequestParam("id")Integer id){
        System.out.println("id = " + id);
        Boolean data = userService.deleteOneUser(id);
        return new ResultResponse()
                .setData(data)
                .setMessage("删除登记的用户");
    }

    @GetMapping("/selectListUserByPlaceId")
    public ResultResponse selectListUserByPlaceId(@RequestParam("placeId")Integer placeId,
                                                  Integer currentPage,
                                                  Integer pageSize){
        System.out.println("placeId = " + placeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoUser voUser = new VoUser();
        voUser.setPlaceId(placeId);
        VoList<VoUser> voList = userService.selectListUserByPlaceId(placeId,currentPage,pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("根据会场查询登记用户");
    }

    @GetMapping("/selectOneUserById")
    public ResultResponse selectOneUserById(@RequestParam("id")Integer id){
        System.out.println("id = " + id);
        VoUser dbUser = userService.selectOneUserById(id);
        return new ResultResponse()
                .setData(dbUser)
                .setMessage("根据编号查询登记用户");
    }

    @GetMapping("/selectOneUserByNameWithPlaceId")
    public ResultResponse selectOneUserByNameWithPlaceId(@RequestParam("name")String name,
                                                         @RequestParam("placeId")Integer placeId){
        System.out.println("name = " + name + ", placeId = " + placeId);
        VoUser dbUser = userService.selectOneUserByNameWithPlaceId(name, placeId);
        return new ResultResponse()
                .setData(dbUser)
                .setMessage("根据会场和昵称查询登记用户");
    }
}
