package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PlaceService;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/21/16:03
 * @Description:
 */
@Api(tags = "中奖记录接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PrizeService prizeService;

    @ApiOperation("根据记录编号删除中奖记录（返回Boolean）")
    @DeleteMapping("/deleteOneRecord")
    public ResultResponse deleteOneRecordById(@ApiParam("中奖记录编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        Boolean data = recordService.deleteOneRecord(id);
        return new ResultResponse()
                .setData(data)
                .setMessage("根据具体编号删除中奖记录");
    }

    @ApiOperation("根据记录编号查询中奖记录")
    @GetMapping("/selectOneRecordById")
    public ResultResponse selectOneRecordById(@ApiParam("中奖记录编号") @RequestParam("id")Integer id){
//        System.out.println("id = " + id);
        VoRecord dbRecord = recordService.selectOneRecordById(id);
        return new ResultResponse()
                .setData(dbRecord)
                .setMessage("根据具体编号查询中奖记录");
    }

    @ApiOperation("根据会场编号查询中奖记录列表（可分页）")
    @GetMapping("/selectListRecordByPlaceId")
    public ResultResponse selectListRecordByPlaceId(@ApiParam("会场编号") @RequestParam("placeId")Integer placeId,
                                                    @ApiParam("当前页") Integer currentPage,
                                                    @ApiParam("每页记录数") Integer pageSize){
//        System.out.println("placeId = " + placeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        StringBuilder message = new StringBuilder("根据会场编号查询中奖记录");
        Object data = new ArrayList<>();
        VoPlace dbPlace = placeService.selectOnePlaceById(placeId);
        if (dbPlace==null){ //会场不存在
            message.append("：会场不存在");
            return new ResultResponse()
                    .setData(data)
                    .setMessage(message.toString());
        }
        VoList<VoRecord> voList = recordService.selectListRecordByPlaceId(placeId, currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage(message.toString());
    }

    @ApiOperation("根据奖项编号查询中奖记录列表（可分页）")
    @GetMapping("/selectListRecordByPrizeId")
    public ResultResponse selectListRecordByPrizeId(@ApiParam("奖项编号") @RequestParam("prizeId")Integer prizeId,
                                                    @ApiParam("当前页") Integer currentPage,
                                                    @ApiParam("每页记录数") Integer pageSize){
//        System.out.println("prizeId = " + prizeId + ", currentPage = " + currentPage + ", pageSize = " + pageSize);
        StringBuilder message = new StringBuilder("根据会场编号查询中奖记录");
        Object data = new ArrayList<>();
        VoPrize dbPrize = prizeService.selectOnePrizeById(prizeId);
        if (dbPrize==null){ //奖项不存在
            message.append("：奖项不存在");
            return new ResultResponse()
                    .setMessage(message.toString())
                    .setData(data);
        }
        VoList<VoRecord> voList = recordService.selectListRecordByPrizeId(prizeId, currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage(message.toString());
    }

}
