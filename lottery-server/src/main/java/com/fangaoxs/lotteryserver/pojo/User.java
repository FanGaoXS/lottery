package com.fangaoxs.lotteryserver.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fangaoxs.lotteryserver.pojo.base.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Page {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户微信头像
     */
    private String avatar;

    /**
     * 用户联系方式
     */
    private String phone;

    /**
     * 用户登记的时间
     */
    private Date time;

    /**
     * 用户所属会场编号
     */
    private Integer placeId;

    /**
     * 用户所属会场
     */
    private Place place;

}
