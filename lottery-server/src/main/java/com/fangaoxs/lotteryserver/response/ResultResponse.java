package com.fangaoxs.lotteryserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/06/18:15
 * @Description:
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ResultResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义状态码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 自定义消息体
     */
    private String message;

    /**
     * 自定义返回数据
     */
    private Object data;

    public ResultResponse(){
        this.code = 20000;
        this.timestamp = System.currentTimeMillis();
        this.data = null;
        this.message = "响应成功";
    }

}
