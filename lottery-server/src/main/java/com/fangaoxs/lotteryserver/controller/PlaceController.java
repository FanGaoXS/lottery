package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.service.PlaceService;
import com.fangaoxs.lotteryserver.vo.ResultResponse;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPlace;
import org.springframework.beans.factory.annotation.Autowired;
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
        VoPlace dbPlace = placeService.selectOnePlaceByName(voPlace.getName());
        //查询数据库中是否已经存在该记录
        if (dbPlace==null){ //数据库中不存在
            data = placeService.insertOnePlace(voPlace);
        }
        return new ResultResponse()
                .setData(data)
                .setMessage("新增会场");
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
        VoPlace dbPlace = placeService.selectOnePlaceByName(voPlace.getName());
        //查询数据库中是否已经存在该会场名
        if (dbPlace==null){ //数据库中不存在
            data = placeService.updateOnePlace(voPlace);
        }
        return new ResultResponse()
                .setData(data)
                .setMessage("修改会场");
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
