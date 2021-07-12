package com.fangaoxs.lotteryserver.pojo;

import com.fangaoxs.lotteryserver.pojo.base.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName prize
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prize extends Page {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 奖项唯一标识
     */
    private String uuid;

    /**
     * 奖项排序号
     */
    private Integer idx;

    /**
     * 奖项名（特等奖、一等奖、二等奖）
     */
    private String name;

    /**
     * 奖项初始数量
     */
    private Integer amount;

    /**
     * 奖项剩余数量
     */
    private Integer balance;

    /**
     * 奖项描述
     */
    private String description;

    /**
     * 录入时间
     */
    private Date time;

    /**
     * 奖项所属会场编号
     */
    private Integer placeId;

    /**
     * 所属会场对象
     */
    private Place place;

    private static final long serialVersionUID = 1L;
}
