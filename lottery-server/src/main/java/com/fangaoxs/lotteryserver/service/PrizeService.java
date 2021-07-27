package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.pojo.Prize;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/12:50
 * @Description:
 */
@Service
public interface PrizeService {

    VoPrize insertOnePrizeWithPlaceId(VoPrize voPrize);

    Boolean deleteOnePrize(Integer id);

    VoPrize updateOnePrize(VoPrize voPrize);

    Boolean updateOnePrizeBalance(Integer id,int balance);

    VoList<VoPrize> selectAllPrize();

    VoList<VoPrize> selectListPrize(Integer currentPage,Integer pageSize);

    VoList<VoPrize> selectListPrizeByPlaceId(Integer placeId,
                                             Integer currentPage,
                                             Integer pageSize);

    VoPrize selectOnePrizeById(Integer id);

    VoPrize selectOnePrizeByNameWithPlaceId(String name,Integer placeId);



}
