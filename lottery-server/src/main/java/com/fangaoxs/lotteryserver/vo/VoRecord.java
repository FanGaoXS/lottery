package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.Record;
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
 * @Date: 2021/07/21/15:09
 * @Description:
 */
@ApiModel("中奖记录视图对象 VoRecord")
@Data
@NoArgsConstructor
public class VoRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("唯一标识")
    private String uuid;

    @ApiModelProperty("中奖时间")
    private Date time;

    /*private Integer userId;

    private Integer prizeId;

    private Integer placeId;*/

    @ApiModelProperty("该记录的中奖的用户对象")
    private VoUser user;

    @ApiModelProperty("该记录中奖的奖项对象")
    private VoPrize prize;

    @ApiModelProperty("该记录中奖的会场对象")
    private VoPlace place;

    public VoRecord(Record record){
        this.id = record.getId();
        this.uuid = record.getUuid();
        this.time = record.getTime();
        /*this.userId = record.getUserId();
        this.prizeId = record.getPrizeId();
        this.placeId = record.getPlaceId();*/

        this.user = new VoUser();
        this.user.setId(record.getUser().getId());
        this.user.setName(record.getUser().getName());
        this.user.setAvatar(record.getUser().getAvatar());

        this.prize = new VoPrize();
        this.prize.setId(record.getPrize().getId());
        this.prize.setName(record.getPrize().getName());
        this.prize.setDescription(record.getPrize().getDescription());

        this.place = new VoPlace();
        this.place.setId(record.getPlace().getId());
        this.place.setName(record.getPlace().getName());
        this.place.setAddress(record.getPlace().getAddress());
    }

}
