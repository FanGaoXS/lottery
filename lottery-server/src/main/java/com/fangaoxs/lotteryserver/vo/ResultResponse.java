package com.fangaoxs.lotteryserver.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/06/18:15
 * @Description:    自定义response返回体
 */
@ApiModel("自定义response响应体 ResultResponse")
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ResultResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义状态码
     */
    @ApiModelProperty("自定义响应状态码")
    private Integer code;

    /**
     * 时间戳
     */
    @ApiModelProperty("响应时间戳")
    private Long timestamp;

    /**
     * 自定义消息体
     */
    @ApiModelProperty("响应消息体")
    private String message;

    /**
     * 自定义返回数据
     */
    @ApiModelProperty("响应数据")
    private Object data;

    public ResultResponse(){
        this.code = 20000;
        this.timestamp = System.currentTimeMillis();
        this.data = null;
        this.message = "响应成功";
    }

}
