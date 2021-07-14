package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.Prize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/12:32
 * @Description:
 */
@ApiModel("奖项视图对象 VoPrize")
@Data
@NoArgsConstructor
public class VoPrize implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("奖项编号")
    private Integer id;

    @ApiModelProperty("奖项唯一标识")
    private String uuid;

    @ApiModelProperty("奖项排序号")
    private Integer idx;

    @ApiModelProperty("奖项名，如特等奖、一等奖、二等奖等")
    private String name;

    @ApiModelProperty("奖项初始数量")
    private Integer amount;

    @ApiModelProperty("奖项剩余数量")
    private Integer balance;

    @ApiModelProperty("奖项具体描述，如switch一台或100元现金")
    private String description;

    /*@ApiModelProperty("奖项录入时间")
    private Date time;*/

    @ApiModelProperty("奖项所属会场编号")
    private Integer placeId;

    @ApiModelProperty("所属会场对象")
    private VoPlace place;

    public VoPrize(Prize prize){
        this.id = prize.getId();
        this.uuid = prize.getUuid();
        this.idx = prize.getIdx();
        this.name = prize.getName();
        this.amount = prize.getAmount();
        this.balance = prize.getBalance();
        this.description = prize.getDescription();
        /*this.time = prize.getTime();*/
        this.placeId = prize.getPlaceId();
        this.place = new VoPlace(prize.getPlace());
    }

}
