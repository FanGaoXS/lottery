package com.fangaoxs.lotteryserver.pojo;

import com.fangaoxs.lotteryserver.pojo.base.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName place
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place extends Page {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 会场唯一标识符
     */
    private String uuid;

    /**
     * 会场名
     */
    private String name;

    /**
     * 会场地址
     */
    private String address;

    /**
     * 录入时间
     */
    private Date time;

    private static final long serialVersionUID = 1L;
}
