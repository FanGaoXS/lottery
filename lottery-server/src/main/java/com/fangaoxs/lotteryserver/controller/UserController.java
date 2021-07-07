package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.response.ResultResponse;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/07/12:54
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResultResponse registerUser(@RequestBody VoUser voUser){
        System.out.println("voUser = " + voUser);
        Boolean result = false;
        //确保每位用户只能登记一次
        if (!userService.isUserExist(voUser)){
            //如果原数据库中不存在该用户（表明该用户是第一次登记）
            result = userService.registerUser(voUser);
        }
        return new ResultResponse()
                .setData(result)
                .setMessage("用户登记");
    }

    @GetMapping("/isRegister")
    public ResultResponse isRegister(VoUser voUser){
        System.out.println("voUser = " + voUser);
        Boolean result = userService.isUserExist(voUser);
        return new ResultResponse()
                .setData(result)
                .setMessage("是否登记");
    }

    @GetMapping("/removeUser")
    public ResultResponse removeUser(Integer id){
        System.out.println("id = " + id);
        Boolean result = userService.removeUser(id);
        return new ResultResponse()
                .setData(result)
                .setMessage("移除已登记用户");
    }

}
