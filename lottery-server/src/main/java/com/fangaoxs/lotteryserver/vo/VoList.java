package com.fangaoxs.lotteryserver.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/06/18:20
 * @Description:
 */
public class VoList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<T> items;

}
