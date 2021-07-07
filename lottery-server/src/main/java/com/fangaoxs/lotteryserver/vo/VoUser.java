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
 * @Date: 2021/07/06/18:20
 * @Description:
 */
@Data
@NoArgsConstructor
public class VoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String avatar;

    private Date time;

    public VoUser(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.time = user.getTime();
    }
}
