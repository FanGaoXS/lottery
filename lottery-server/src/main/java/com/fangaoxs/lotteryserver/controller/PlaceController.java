package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PlaceService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPlace;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/13:53
 * @Description:
 */
@Api(tags = "会场接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @ApiOperation("新增一个会场（data返回Boolean）")
    @PostMapping("/insertOnePlace")
    public ResultResponse insertOnePlace(@ApiParam("place对象") @RequestBody VoPlace voPlace){
        System.out.println("voPlace = " + voPlace);
        Boolean data = false;
        StringBuilder message = new StringBuilder("新增会场");
        try {
            data = placeService.insertOnePlace(voPlace);
        } catch (DataAccessException e){
            //e.printStackTrace();
            message.append("：名称重复");
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message.toString());
    }

    @ApiOperation("删除一个会场（data返回Boolean）")
    @GetMapping("/deleteOnePlace")
    public ResultResponse deleteOnePlace(@ApiParam("会场编号")@RequestParam("id")Integer placeId){
        System.out.println("placeId = " + placeId);
        Boolean data = placeService.deleteOnePlace(placeId);
        return new ResultResponse()
                .setData(data)
                .setMessage("删除会场");
    }

    @ApiOperation("修改一个会场（data返回Boolean）")
    @PostMapping("/updateOnePlace")
    public ResultResponse updateOnePlace(@ApiParam("place对象")@RequestBody VoPlace voPlace){
        System.out.println("voPlace = " + voPlace);
        Boolean data = false;
        StringBuilder message = new StringBuilder("修改会场");
        try {
            data = placeService.updateOnePlace(voPlace);
        } catch (DataAccessException e){
//            e.printStackTrace();
            message.append("：名称重复");
        }
        return new ResultResponse()
                .setData(data)
                .setMessage(message.toString());
    }

    @ApiOperation("查询所有会场")
    @GetMapping("/selectAllPlace")
    public ResultResponse selectAllPlace(){
        VoList<VoPlace> voList = placeService.selectAllPlace();
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询所有会场");
    }

    @ApiOperation("查询会场列表（分页）")
    @GetMapping("/selectListPlace")
    public ResultResponse selectListPlace(@ApiParam(value = "当前页")Integer currentPage,
                                          @ApiParam(value = "每页记录数")Integer pageSize){
        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoList<VoPlace> voList = placeService.selectListPlace(currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询会场列表");
    }

    @ApiOperation("根据会场编号查询具体会场")
    @GetMapping("/selectOnePlaceById")
    public ResultResponse selectOnePlace(@ApiParam("会场编号") @RequestParam("id")Integer id){
        System.out.println("id = " + id);
        VoPlace voPlace = placeService.selectOnePlaceById(id);
        return new ResultResponse()
                .setData(voPlace)
                .setMessage("根据编号查询会场");
    }

}
