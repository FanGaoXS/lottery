package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
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
 * @Date: 2021/07/14/12:29
 * @Description:
 */
@Api(tags = "奖项接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/prize")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;

    @ApiOperation("新增一个奖项并且携带会场编号")
    @PostMapping("/insertOnePrizeWithPlaceId")
    public ResultResponse insertOnePrizeWithPlaceId(@ApiParam("prize对象") @RequestBody VoPrize voPrize){
//        System.out.println("voPrize = " + voPrize);
        Object data = null;
        StringBuilder message = new StringBuilder("会场新增奖项");
        if (voPrize.getPlaceId()==null){
            message.append("：需要绑定会场");
            return new ResultResponse()
                    .setData(false)
                    .setMessage(message.toString());
        }
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

    @ApiOperation("删除一个奖项（data返回Boolean）")
    @DeleteMapping("/deleteOnePrize")
    public ResultResponse deleteOnePrize(@ApiParam("奖项编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        Boolean data = prizeService.deleteOnePrize(id);
        return new ResultResponse()
                .setData(data)
                .setMessage("会场移除该奖项（并非中奖）");
    }

    @ApiOperation("修改一个奖项")
    @PostMapping("/updateOnePrize")
    public ResultResponse updateOnePrize(@ApiParam("prize对象") @RequestBody VoPrize voPrize){
//        System.out.println("voPrize = " + voPrize);
        Object data = null;
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

    @ApiOperation("根据会场编号查询该会场的所有奖项（可分页）")
    @GetMapping("/selectListPrizeByPlaceId")
    public ResultResponse selectListPrizeByPlaceId(@ApiParam("会场编号") @RequestParam("placeId")Integer placeId,
                                                   @ApiParam("当前页（从1开始）") Integer currentPage,
                                                   @ApiParam("每页记录数") Integer pageSize){
//        System.out.println("placeId = " + placeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoList<VoPrize> voList = prizeService.selectListPrizeByPlaceId(placeId, currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询会场的奖项");
    }

    @ApiOperation("根据奖项编号查询具体奖项")
    @GetMapping("/selectOnePrizeById")
    public ResultResponse selectOnePrizeById(@ApiParam("奖项编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        VoPrize voPrize = prizeService.selectOnePrizeById(id);
        return new ResultResponse()
                .setData(voPrize)
                .setMessage("根据编号查询具体奖项");
    }
}
