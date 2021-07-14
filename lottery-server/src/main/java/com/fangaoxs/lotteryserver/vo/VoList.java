package com.fangaoxs.lotteryserver.vo;

import io.swagger.annotations.ApiModel;
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

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<T> items;

}
