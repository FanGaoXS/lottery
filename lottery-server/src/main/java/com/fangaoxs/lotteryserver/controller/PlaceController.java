package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PlaceService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPlace;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
@RestController
@CrossOrigin("*")
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/insertOnePlace")
    public ResultResponse insertOnePlace(@RequestBody VoPlace voPlace){
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

    @GetMapping("/deleteOnePlace")
    public ResultResponse deleteOnePlace(@RequestParam("id")Integer placeId){
        System.out.println("placeId = " + placeId);
        Boolean data = placeService.deleteOnePlace(placeId);
        return new ResultResponse()
                .setData(data)
                .setMessage("删除会场");
    }

    @PostMapping("/updateOnePlace")
    public ResultResponse updateOnePlace(@RequestBody VoPlace voPlace){
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

    @GetMapping("/selectAllPlace")
    public ResultResponse selectAllPlace(){
        VoList<VoPlace> voList = placeService.selectAllPlace();
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询所有会场");
    }

    @GetMapping("/selectListPlace")
    public ResultResponse selectListPlace(@RequestParam("currentPage")Integer currentPage,
                                          @RequestParam("pageSize")Integer pageSize){
        System.out.println("currentPage = " + currentPage + ", pageSize = " + pageSize);
        VoList<VoPlace> voList = placeService.selectListPlace(currentPage, pageSize);
        return new ResultResponse()
                .setData(voList)
                .setMessage("查询会场列表");
    }

    @GetMapping("/selectOnePlaceById")
    public ResultResponse selectOnePlace(@RequestParam("id")Integer id){
        System.out.println("id = " + id);
        VoPlace voPlace = placeService.selectOnePlaceById(id);
        return new ResultResponse()
                .setData(voPlace)
                .setMessage("根据编号查询会场");
    }

}
