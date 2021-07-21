package com.fangaoxs.lotteryserver.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/06/18:20
 * @Description:
 */
@ApiModel("list视图集合对象 VoList")
@Data
@NoArgsConstructor
public class VoList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("当前页（从1开始）")
    private Integer currentPage;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    @ApiModelProperty("该条件下的总记录数")
    private Long totalSize;

    @ApiModelProperty("集合对象")
    private List<T> items;

}
