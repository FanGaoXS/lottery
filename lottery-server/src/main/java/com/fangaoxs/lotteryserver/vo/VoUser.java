package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.User;
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
@ApiModel("登记用户视图对象 VoUser")
@Data
@NoArgsConstructor
public class VoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    private Integer id;

    @ApiModelProperty("用户唯一标识")
    private String uuid;

    @ApiModelProperty("用户微信昵称")
    private String name;

    @ApiModelProperty("用户微信头像")
    private String avatar;

//    private String phone;

    @ApiModelProperty("用户登记时间")
    private Date time;

    @ApiModelProperty("用户登记会场编号")
    private Integer placeId;

    @ApiModelProperty("用户登记会场对象")
    private VoPlace place;

    public VoUser(User user){
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.avatar = user.getAvatar();
//            this.phone = user.getPhone();
        this.time = user.getTime();
        this.placeId = user.getPlaceId();
        this.place = new VoPlace(user.getPlace());
    }
}
