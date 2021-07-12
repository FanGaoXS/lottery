package com.fangaoxs.lotteryserver.pojo;

import com.fangaoxs.lotteryserver.pojo.base.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName record
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record extends Page {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 中奖时间
     */
    private Date time;

    /**
     * 所属会场
     */
    private Integer placeId;

    private Place place;

    /**
     * 奖项编号
     */
    private Integer prizeId;

    private Prize prize;

    /**
     * 中奖人编号
     */
    private Integer userId;

    private User user;


    private static final long serialVersionUID = 1L;
}
