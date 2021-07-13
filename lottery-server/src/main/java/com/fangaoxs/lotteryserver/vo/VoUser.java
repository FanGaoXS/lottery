package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.User;
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
@Data
@NoArgsConstructor
public class VoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String name;

    private String avatar;

//    private String phone;

    private Date time;

    private Integer placeId;

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
