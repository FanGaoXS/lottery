package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.Place;
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
 * @Date: 2021/07/13/12:52
 * @Description:
 */
@ApiModel("会场视图对象 VoPlace")
@Data
@NoArgsConstructor
public class VoPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会场编号")
    private Integer id;

    @ApiModelProperty("会场唯一标识")
    private String uuid;

    @ApiModelProperty("会场名")
    private String name;

    @ApiModelProperty("会场地址")
    private String address;

//    private Date time;

    public VoPlace(Place place){
        this.id = place.getId();
        this.uuid = place.getUuid();
        this.name = place.getName();
        this.address = place.getAddress();
//        this.time = place.getTime();
    }

}
