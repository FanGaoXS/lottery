package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.DrawService;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.utils.NumberUtils;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import com.fangaoxs.lotteryserver.vo.VoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/15:55
 * @Description:    抽奖
 */
@Api(tags = "抽奖接口")
@RestController
@RequestMapping("/draw")
@CrossOrigin("*")
public class DrawController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private DrawService drawService;

    @ApiOperation("进行一次抽奖（返回中奖的用户集合）")
    @GetMapping("/makeDraw")
    public ResultResponse makeDraw(@ApiParam("奖项编号") @RequestParam("id")Integer id,
                                   @ApiParam("抽取数量") @RequestParam("number")int number){
//        System.out.println("id = " + id + ", number = " + number);
        StringBuilder message = new StringBuilder("开奖");
        VoPrize dbPrize = prizeService.selectOnePrizeById(id);
        Integer placeId = dbPrize.getPlaceId(); //奖项所属会场编号
        int balance = dbPrize.getBalance(); //奖项余量
        if (dbPrize==null){ //奖项不存在
            message.append("：错误，奖项不存在");
            return new ResultResponse()
                    .setMessage(message.toString())
                    .setData(new ArrayList<>());
        } else if (placeId==null){ //会场不存在
            message.append("：错误，会场不存在");
            return new ResultResponse()
                    .setMessage(message.toString())
                    .setData(new ArrayList<>());
        } else if (balance==0){ //奖项剩余数量为0
            message.append("：错误，奖项剩余数量为0");
            return new ResultResponse()
                    .setMessage(message.toString())
                    .setData(new ArrayList<>());
        }
        List<User> userList = userService.selectListUserByPlaceId(placeId);//根据会场编号查询出该会场的已登记用户
        int min = Math.min(number,balance); //余量、抽奖数量取min
        List<VoUser> drawVoUserList = drawService.makeDraw(userList, min, dbPrize);
        return new ResultResponse()
                .setMessage(message.toString())
                .setData(drawVoUserList);
    }


}
