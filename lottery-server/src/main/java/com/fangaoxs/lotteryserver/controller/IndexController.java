package com.fangaoxs.lotteryserver.controller;

import com.fangaoxs.lotteryserver.vo.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/08/12:45
 * @Description:
 */
@Api(tags = "测试接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class IndexController {

    @ApiOperation("测试连接")
    @GetMapping("/")
    public ResultResponse index(){
        return new ResultResponse()
                .setMessage("这是lottery-server服务器")
                .setData(true);
    }
}
