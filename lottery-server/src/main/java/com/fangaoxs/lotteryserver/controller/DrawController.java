package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import com.fangaoxs.lotteryserver.vo.VoUser;
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
@RestController
@RequestMapping("/draw")
@CrossOrigin("*")
public class DrawController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrizeService prizeService;

    @GetMapping("/makeDraw")
    public ResultResponse makeDraw(@RequestParam("id")Integer id,
                                   @RequestParam("number")Integer number){
        System.out.println("id = " + id + ", number = " + number);
        VoPrize dbPrize = prizeService.selectOnePrizeById(id);
        Integer placeId = dbPrize.getPlaceId(); //奖项所属会场编号
        Integer balance = dbPrize.getBalance(); //奖项余量
        List<User> userList = userService.selectListUserByPlaceId(placeId);//根据会场编号查询出该会场的已登记用户
        return null;
    }

}
