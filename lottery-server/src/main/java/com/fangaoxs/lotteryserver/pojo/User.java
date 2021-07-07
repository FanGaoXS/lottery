package com.fangaoxs.lotteryserver.pojo;

import java.io.Serializable;
import java.util.Date;

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
public class User implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户名
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

    private static final long serialVersionUID = 1L;
}
