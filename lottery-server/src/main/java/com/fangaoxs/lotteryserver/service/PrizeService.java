package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/12:50
 * @Description:
 */
@Service
public interface PrizeService {

    Boolean insertOnePrizeWithPlaceId(VoPrize voPrize);

    Boolean deleteOnePrize(Integer id);

    Boolean updateOnePrize(VoPrize voPrize);

    Boolean updateOnePrizeIdx(Integer id,Integer idx);

    VoList<VoPrize> selectAllPrize();

    VoList<VoPrize> selectListPrize(Integer currentPage,Integer pageSize);

    VoList<VoPrize> selectListPrizeByPlaceId(Integer placeId,
                                             Integer currentPage,
                                             Integer pageSize);

    VoPrize selectOnePrizeById(Integer id);

    VoPrize selectOnePrizeByNameWithPlaceId(String name,Integer placeId);



}
