package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/12:29
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/prize")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;

    @PostMapping("/insertOnePrizeWithPlaceId")
    public ResultResponse insertOnePrizeWithPlaceId(@RequestBody VoPrize voPrize){
        System.out.println("voPrize = " + voPrize);
        Boolean data = false;
        StringBuilder message = new StringBuilder("会场新增奖项");
        try{
            data = prizeService.insertOnePrizeWithPlaceId(voPrize);
        } catch (DataAccessException e){
//            e.printStackTrace();
            message.append("：奖项重复");
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message.toString());
    }

    @GetMapping("/deleteOnePrize")
    public ResultResponse deleteOnePrize(@RequestParam("id")Integer id){
        System.out.println("id = " + id);
        Boolean data = prizeService.deleteOnePrize(id);
        return new ResultResponse()
                .setData(data)
                .setMessage("会场移除该奖项（并非中奖）");
    }

    @PostMapping("/updateOnePrize")
    public ResultResponse updateOnePrize(@RequestBody VoPrize voPrize){
        System.out.println("voPrize = " + voPrize);
        Boolean data = false;
        StringBuilder message = new StringBuilder("修改奖项");
        if (voPrize.getBalance()> voPrize.getAmount()){
            message.append("：余量不得大于初始量");
            return new ResultResponse()
                    .setData(false)
                    .setMessage(message.toString());
        }
        try {
            data = prizeService.updateOnePrize(voPrize);
        } catch (DataAccessException e){
//            e.printStackTrace();
            message.append("：奖项重复");
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message.toString());
    }

    @GetMapping("/updateOnePrizeIdx")
    public ResultResponse updateOnePrizeIdx(@RequestParam("id")Integer id,
                                            @RequestParam("idx")Integer idx){
        System.out.println("id = " + id + ", idx = " + idx);
        Boolean data = prizeService.updateOnePrizeIdx(id, idx);
        return new ResultResponse()
                .setData(data)
                .setMessage("修改奖项排序号");
    }

    @GetMapping("/selectListPrizeByPlaceId")
    public ResultResponse selectListPrizeByPlaceId(@RequestParam("placeId")Integer placeId,
                                                   Integer currentPage,
                                                   Integer pageSize){
        System.out.println("placeId = " + placeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoList<VoPrize> voList = prizeService.selectListPrizeByPlaceId(placeId, currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询会场的奖项");
    }

    @GetMapping("/selectOnePrizeById")
    public ResultResponse selectOnePrizeById(@RequestParam("id")Integer id){
        System.out.println("id = " + id);
        VoPrize voPrize = prizeService.selectOnePrizeById(id);
        return new ResultResponse()
                .setData(voPrize)
                .setMessage("根据编号查询具体奖项");
    }
}
